package cafe.jjdev.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cafe.jjdev.mall.service.EmailService;
import cafe.jjdev.mall.vo.Email;

@Controller
class EmailController{
	@Autowired
	EmailService emailService;
	
	@GetMapping(value="/sendEmail")
	public String sendEmail() {
		return "sendEmail";
	}
	
	@PostMapping(value="/sendMail")
	public String sendEmail(Email email){
		EmailService es=new EmailService();
		es.sendSimpleMessage(email.getTo(), email.getSubject(), email.getText());		
		return "index";
	}
}