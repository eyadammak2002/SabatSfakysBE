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
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
	        helper.setText(email, true);
	        helper.setTo(to);
	        helper.setSubject("Confirmez votre email");
	        helper.setFrom("eyadammak.ig@gmail.com");
	        mailSender.send(message);
	        System.out.println("Email envoyé avec succès à " + to);
	    } catch (MessagingException e) {
	        System.err.println("ERREUR SMTP : " + e.getMessage());
	        e.printStackTrace();  
	    }
	}
 
	
	public void sendAuthenticationEmail(String to, String firstname) {
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        // Vérifier si le template est bien trouvé
	        Context context = new Context();
	        context.setVariable("firstname", firstname);
	        String htmlContent = templateEngine.process("registeremailTemplate", context);
	        
	        if (htmlContent == null || htmlContent.isEmpty()) {
	            System.err.println("ERREUR : Le template email est vide ou introuvable !");
	        } else {
	            System.out.println("emplate Thymeleaf chargé avec succès !");
	        }
	        helper.setTo(to);
	        helper.setSubject("Confirmez votre email");
	        helper.setFrom("eyadammak.ig@gmail.com");
	        helper.setText(htmlContent, true);
	        mailSender.send(message);
	        System.out.println("Email envoyé avec succès à " + to);
	    } catch (Exception e) {
	        System.err.println("ERREUR : " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	public void sendRefusedAuthenticationEmail(String to, String firstname) {
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        // Vérifier si le template est bien trouvé
	        Context context = new Context();
	        context.setVariable("firstname", firstname);
	        String htmlContent = templateEngine.process("registeremailTemplateREFUSEE", context);

	        if (htmlContent == null || htmlContent.isEmpty()) {
	            System.err.println("ERREUR : Le template de refus est vide ou introuvable !");
	        } else {
	            System.out.println("Template Thymeleaf de refus chargé avec succès !");
	        }

	        helper.setTo(to);
	        helper.setSubject("Votre demande a été refusée");
	        helper.setFrom("eyadammak.ig@gmail.com");
	        helper.setText(htmlContent, true);

	        mailSender.send(message);
	        System.out.println("Email de refus envoyé avec succès à " + to);
	    } catch (Exception e) {
	        System.err.println("ERREUR : " + e.getMessage());
	        e.printStackTrace();
	    }
	}




}
	
	

