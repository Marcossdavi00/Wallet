package com.wallet.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.Entity.Wallet;
import com.wallet.Entity.WalletItem;
import com.wallet.Repository.WalletItemRepository;
import com.wallet.Util.Enums.TypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemRepositoryTest {

	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta de luz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
	private Long savedWalletItemId = null;
	private Long savedWalletId = null;
	
	@Autowired
	WalletItemRepository repository;
	@Autowired
	WalletRepository walletrepository;
	
	@Before
	public void setup() {
		Wallet w = new Wallet();
		w.setName("Carteira 1");
		w.setValue(BigDecimal.valueOf(500));
		walletrepository.save(w);
		
		WalletItem wi = new WalletItem();
		wi.setId(1);
		wi.setWallet(w);
		wi.setDate(DATE);
		wi.setDescription(DESCRIPTION);
		wi.setType(TYPE);
		wi.setValue(VALUE);
		
		repository.save(wi);
		
		savedWalletItemId = wi.getId();
		savedWalletId = w.getId();
		
	}
	
	@After
	public void tearDown() {
		repository.deleteAll();;
		walletrepository.deleteAll();
	}
	
	@Test
	public void testSave() {
		
		Wallet w = new Wallet();
		w.setName("Carteira 1");
		w.setValue(BigDecimal.valueOf(500));
		walletrepository.save(w);
		
		WalletItem wi = new WalletItem();
		wi.setId(1);
		wi.setWallet(w);
		wi.setDate(DATE);
		wi.setDescription(DESCRIPTION);
		wi.setType(TYPE);
		wi.setValue(VALUE);
		
		WalletItem response = repository.save(wi);
		
		//Retorna se a função foi verdadeira e não deu erro
		assertNotNull(response);
		//Realiza comparação
		assertEquals(response.getDescription(), DESCRIPTION);
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getValue(), VALUE);
		assertEquals(response.getWallet().getId(), w.getId());
		
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testSaveInvalidWalletItem() {
		WalletItem wi = new WalletItem();
		wi.setDescription(DESCRIPTION);
		wi.setValue(VALUE);
		
		repository.save(wi);
	}
	
	@Test
	public void testUpdate() {
		Optional<WalletItem> wi = repository.findById(savedWalletItemId);
		
		String description = "Descrição alterada";
		WalletItem changed = wi.get();
		changed.setDescription(description);
		
		repository.save(changed);
		
		Optional<WalletItem> newWalletItem = repository.findById(savedWalletItemId);
		
		assertEquals(description, newWalletItem.get().getDescription());
	}
	
	@Test
	public void testDelete() {
		Optional<Wallet> wallet = walletrepository.findById(savedWalletId);
		WalletItem wi = new WalletItem();
		wi.setWallet(wallet.get());
		wi.setDate(DATE);
		wi.setDescription(DESCRIPTION);
		wi.setType(TYPE);
		wi.setValue(VALUE);
		
		repository.save(wi);
		
		repository.deleteById(wi.getId());
		
		Optional<WalletItem> response = repository.findById(wi.getId());
		
		assertFalse(response.isPresent());
	}
	
	@Test
	public void testFindBetweenDates() {
		Optional<Wallet> w = walletrepository.findById(savedWalletId);
		
		LocalDateTime localDateTime = DATE.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		Date currentDatePlusFiveDays = Date.from(localDateTime.plusDays(5).atZone(ZoneId.systemDefault()).toInstant());
		Date currentDatePlusSevenDays = Date.from(localDateTime.plusDays(7).atZone(ZoneId.systemDefault()).toInstant());
		
		WalletItem wi = new WalletItem();
		wi.setWallet(w.get());
		wi.setDate(currentDatePlusFiveDays);
		wi.setDescription(DESCRIPTION);
		wi.setType(TYPE);
		wi.setValue(VALUE);
		
		repository.save(wi);
		
		WalletItem wi1 = new WalletItem();
		wi1.setWallet(w.get());
		wi1.setDate(currentDatePlusSevenDays);
		wi1.setDescription(DESCRIPTION);
		wi1.setType(TYPE);
		wi1.setValue(VALUE);
		
		repository.save(wi1);
		
		PageRequest pg = PageRequest.of(0, 10);
		Page<WalletItem> response = repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(savedWalletId, DATE, currentDatePlusFiveDays, pg);
		
		assertEquals(response.getContent().size(), 2);
		assertEquals(response.getTotalElements(), 2);
		
	}
	
	@Test
	public void testFindByType() {
		List<WalletItem> response = repository.findByWalletIdAndType(savedWalletId, TYPE);
		
		assertEquals(response.size(), 1);
		assertEquals(response.get(0).getType(), TYPE);
	}
	
	@Test
	public void testFindByTypeSd() {
		Optional<Wallet> w = walletrepository.findById(savedWalletId);
		
		WalletItem wi = new WalletItem();
		wi.setWallet(w.get());
		wi.setDate(DATE);
		wi.setDescription(DESCRIPTION);
		wi.setType(TypeEnum.SD);
		wi.setValue(VALUE);
		
		repository.save(wi);
	}
	
	@Test
	public void testSumByWallet() {
		Optional<Wallet> w = walletrepository.findById(savedWalletId);
		
		WalletItem wi = new WalletItem();
		wi.setWallet(w.get());
		wi.setDate(DATE);
		wi.setDescription(DESCRIPTION);
		wi.setType(TypeEnum.SD);
		wi.setValue(BigDecimal.valueOf(150.80));
		
		repository.save(wi);
		
		BigDecimal response = repository.sumByWalletId(savedWalletId);
		
		assertEquals(response.compareTo(BigDecimal.valueOf(215.8)), 0);
		
	}
	
	
	
	
	
	
}
