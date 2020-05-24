package com.milton.spboot.services;

import org.springframework.mail.SimpleMailMessage;

import com.milton.spboot.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
