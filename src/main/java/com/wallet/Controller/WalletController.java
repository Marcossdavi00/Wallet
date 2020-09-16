package com.wallet.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.DTO.WalletDTO;
import com.wallet.Entity.Wallet;
import com.wallet.Response.Response;
import com.wallet.Service.WalletService;

@RestController
@RequestMapping("wallet")
public class WalletController {
	
	@Autowired
	private WalletService service;

	@PostMapping
	public ResponseEntity<Response<WalletDTO>> create(@Validated @RequestBody WalletDTO dto, BindingResult result){
		
		Response<WalletDTO> response = new Response<WalletDTO>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors1().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		Wallet wallet = service.save(this.convertDtoToEntityW(dto));
		
		response.setData(this.convertEntityToDtoW(wallet));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

		
	}
	
	private Wallet convertDtoToEntityW(WalletDTO dto) {
		Wallet w = new Wallet();
		w.setId(dto.getId());
		w.setName(dto.getName());
		w.setValue(dto.getValue());
		
		return w;
		
	}
	private WalletDTO convertEntityToDtoW(Wallet w) {
		WalletDTO dto = new WalletDTO();
		dto.setId(w.getId());
		dto.setName(w.getName());
		dto.setValue(w.getValue());
		
		return dto;
	}
	
	
	
	
	
	
}
