package com.wallet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.DTO.UserDTO;
import com.wallet.Entity.User;
import com.wallet.Response.Response;
import com.wallet.Service.UserService;
import com.wallet.Util.Bcrypt;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<Response<UserDTO>> create(@Validated @RequestBody UserDTO dto, BindingResult result){
		
		Response<UserDTO> response = new Response<UserDTO>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors1().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		User user = service.save(this.convertDtoToEntity(dto));
		
		response.setData(this.convertEntityToDto(user));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	//O que será gravado no Banco de Dados
	private User convertDtoToEntity(UserDTO dto) {
		User u = new User();
		u.setId(dto.getId());
		u.setEmail(dto.getEmail());
		u.setName(dto.getName());
		u.setPassword(Bcrypt.getHash(dto.getPassword()));
		
		return u;
	}
	//O que será mostrado na interface do usuário após o cadastro
	private UserDTO convertEntityToDto(User u) {
		UserDTO dto = new UserDTO();
		dto.setId(u.getId());
		dto.setEmail(u.getEmail());
		dto.setName(u.getName());
		
		return dto;
	}
}
