package com.bhel.project.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bhel.project.entityImpl.BhelLogin;
import com.bhel.project.service.impl.BhelLoginService;
import com.bhel.project.service.impl.EmailService;

@Controller
public class ForgetController {

	Random random = new Random(1000);

	@Autowired
	private EmailService emailService;

	@Autowired
	private BhelLoginService bhelLoginService;

	@RequestMapping("/forget")
	public String openEmailForm(Model model) {
		return "forgetEmailForm";
	}

	@RequestMapping("/resetPassword/{email_id}/{id}/{expirationTime}" )
	public String resetPassword(Model model, @PathVariable("email_id") String email_id, @PathVariable("id") String userId, @PathVariable("expirationTime") long expirationTime) {
		System.out.println("email "+ email_id);
		model.addAttribute("email", email_id);
		model.addAttribute("id", userId);
		return "changepassword";
	}
	
	
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam("email") String email, @RequestParam("id") int id,
	                             @RequestParam("newPassword") String newPassword,
	                             @RequestParam("confirmPassword") String confirmPassword,
	                             Model model) {

	    if (!newPassword.equals(confirmPassword)) {
	        
	        model.addAttribute("message", "New password and confirm password do not match.");
	        return "changepasswordPage";
	    }

	   
	    String encryptedBCryptPasswordEncoder = new BCryptPasswordEncoder().encode(newPassword);
	    
	    BhelLogin updateLogin = new BhelLogin();
	    updateLogin.setId(id);
	    updateLogin.setEmail_id(email);
	    updateLogin.setPassword(encryptedBCryptPasswordEncoder);
	    BhelLogin status = bhelLoginService.updateResetPassword(updateLogin);

	    if (status.getEmail_id().equalsIgnoreCase(email)) {
	        model.addAttribute("message", "Your password has been updated successfully");
	        return "bhelLogin";
	    } else {
	        model.addAttribute("message", "Check your email id!!!");
	    }

	    return "bhelLogin";
	}

	
	
	

}