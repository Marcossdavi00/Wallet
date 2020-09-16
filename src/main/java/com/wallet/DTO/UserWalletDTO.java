package com.wallet.DTO;

import javax.validation.constraints.NotNull;

public class UserWalletDTO {

	private long Id;
	@NotNull(message = "Informe o Id do Usuário")
	private long users;
	@NotNull(message = "Informe o Id da Carteira")
	private long wallet;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getUsers() {
		return users;
	}
	public void setUsers(Long users) {
		this.users = users;
	}
	public Long getWallet() {
		return wallet;
	}
	public void setWallet(Long wallet) {
		this.wallet = wallet;
	}
	
}
