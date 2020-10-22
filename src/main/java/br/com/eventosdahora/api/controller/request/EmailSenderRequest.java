package br.com.eventosdahora.api.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailSenderRequest {
	
	public String textBody;
	public String email;
	
	public String getTextBody() {
		return textBody;
	}
	
	public void setTextBody(final String textBody) {
		this.textBody = textBody;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(final String email) {
		this.email = email;
	}
}
