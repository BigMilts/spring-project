package com.milton.spboot.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milton.spboot.dto.EmailDTO;
import com.milton.spboot.security.JWTUtil;
import com.milton.spboot.security.UserSS;
import com.milton.spboot.services.AuthService;
import com.milton.spboot.services.UserServices;

@RestController
@RequestMapping("auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserServices.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid  @RequestBody EmailDTO objtDTO) {
		service.sendNewPassword(objtDTO.getEmail());
		return ResponseEntity.noContent().build();
	}

}
