package com.wallet.Controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.DTO.UserWalletDTO;
import com.wallet.Entity.User;
import com.wallet.Entity.UserWallet;
import com.wallet.Entity.Wallet;
import com.wallet.Response.Response;
import com.wallet.Service.UserWalletService;

@RestController
@RequestMapping(path = "user-wallet")
public class UserWalletController {
	
	@Autowired
	private UserWalletService service;
	
	@PostMapping
	public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result){
		
		Response<UserWalletDTO> response = new Response<UserWalletDTO>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors1().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		UserWallet uw = service.save(this.convertDtoToEntity(dto));
		
		response.setData(this.convertEntityToDto(uw));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	public UserWallet convertDtoToEntity(UserWalletDTO dto) {
		
		User u = new User();
		u.setId(dto.getUsers());
		Wallet w = new Wallet();
		w.setId(dto.getWallet());
		
		UserWallet uw = new UserWallet();
		uw.setId(dto.getId());
		uw.setUsers(u);
		uw.setWallet(w);
		
		return uw;
	}
	
	public UserWalletDTO convertEntityToDto(UserWallet uw) {
		
		UserWalletDTO dto = new UserWalletDTO();
		dto.setId(uw.getId());
		dto.setUsers(uw.getUsers().getId());
		dto.setWallet(uw.getWallet().getId());
		
		return dto;
		
	}
	
	
}
