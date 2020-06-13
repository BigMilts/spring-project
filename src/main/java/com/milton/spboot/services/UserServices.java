package com.milton.spboot.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.milton.spboot.security.UserSS;

public class UserServices {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		}catch(Exception e) {
			return null;
		}
	}
}