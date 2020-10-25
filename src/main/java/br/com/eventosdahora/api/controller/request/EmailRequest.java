package br.com.eventosdahora.api.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailRequest {
	
	public String textBody;
	public String email;
	
	public EmailRequest(final String textBody, final String email) {
		this.textBody = textBody;
		this.email = email;
	}
	
	public EmailRequest() {

	}
	
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
	
	@Override
	public String toString() {
		return "EmailRequest{" +
		       "textBody='" + textBody + '\'' +
		       ", email='" + email + '\'' +
		       '}';
	}
}
