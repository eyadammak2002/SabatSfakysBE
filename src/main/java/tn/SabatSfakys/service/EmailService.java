package tn.SabatSfakys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import tn.SabatSfakys.repository.EmailSender;


@Service


public class EmailService implements EmailSender{
	
    private final JavaMailSender mailSender;
    
  
	
	public EmailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}


	@Autowired
    private TemplateEngine templateEngine;


	
	@Override
    @Async
    public void Send(String to, String email) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "Utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confim Your Email");
            helper.setFrom("eya.dammak@escs.u-sfax.tn");
            mailSender.send(message);
        } catch (MessagingException e) {
        	throw new RuntimeException(e);
        }

    }
        
	
	public void sendAuthenticationEmail(String to, String firstname) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Create email content using Thymeleaf
        Context context = new Context();
        context.setVariable("firstname", firstname);
        String htmlContent = templateEngine.process("registeremailTemplate", context);

        helper.setTo(to);
        helper.setSubject("Confim Your Email");
        helper.setFrom("eya.dammak@escs.u-sfax.tn");
        helper.setText(htmlContent, true);  // true for HTML content
        mailSender.send(message);
    }


}
	
	

