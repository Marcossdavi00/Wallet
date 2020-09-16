package com.wallet.DTO;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class WalletDTO {

	private long Id;
	@Length(min = 3, message = "Nome deve possuir mais de 3 caracteres")
	@NotNull(message = "Nome é obrigatório")
	private String name;
	@NotNull(message = "Valor é obrigatório")
	private BigDecimal value;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
