package com.wallet.Entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.wallet.Entity.Wallet;

@Entity
@Table(name = "users_wallet")
public class UserWallet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@JoinColumn(name = "users", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY) //Definindo o tipo de relacionamento e configurando para retornar apenas o ID, para economizar processador
	private User users;
	@JoinColumn(name = "Wallet", referencedColumnName = "Id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Wallet wallet;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
