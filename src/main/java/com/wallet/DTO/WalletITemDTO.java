package com.wallet.DTO;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class WalletITemDTO {

	private Long Id;
	@NotNull(message = "Carteira é obrigatória")
	private Long wallet;
	@NotNull(message = "Data é obrigatória")
	private Date data;
	@NotNull(message = "Tipo é obrigatória")
	@Pattern(regexp="^(ENTRADA|SAÍDA)^", message="Para o ipo somente são aceitos os valores ENTRADA ou SAÍDA")
	private String type;
	@NotNull(message = "Descrição é obrigatória")
	@Length(min = 5, message = "Descrição deve possuir mais de 5 caracteres")
	private String description;
	@NotNull
	private BigDecimal value;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getWallet() {
		return wallet;
	}
	public void setWallet(Long wallet) {
		this.wallet = wallet;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
