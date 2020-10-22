package br.com.eventosdahora.api.service;

import br.com.eventosdahora.api.controller.request.EmailSenderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

@Component
public class EmailService {

	@Autowired
	TemplateEngine templateEngine;

	@Autowired
	public JavaMailSender emailSender;
	
	@Value("${url.front.user}")
	private String urlFrontUser;
	
	@Value("${url.microservice.image}")
	private String urlImage;
	
	@Value("${id.logo}")
	private String idLogo;
	
	@Async
	public void sendEmail(EmailSenderRequest emailSenderRequest) {
		String template = "email";
		String subject = "Eventos da Hora - notificação";
		
		Context context = new Context(new Locale("pt", "BR"));
		context.setVariable("textBody", emailSenderRequest.textBody);
		context.setVariable("url", urlFrontUser);
		context.setVariable("urlImage", urlImage + idLogo);
		
		try {
			String corpoEmail = templateEngine.process(template, context);
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom(new InternetAddress("no-reply@eventosdahora.com.br", "no-reply@eventosdahora.com.br"));
			helper.setTo(emailSenderRequest.email);
			helper.setSubject(String.format(subject));
			helper.setText(corpoEmail, true);
			
			emailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
