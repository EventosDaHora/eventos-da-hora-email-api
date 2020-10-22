package br.com.eventosdahora.api.controller;

import br.com.eventosdahora.api.controller.request.EmailSenderRequest;
import br.com.eventosdahora.api.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
@Api(value = "user", description = "User API")
public class EmailController {
	
	@Autowired
	public EmailService emailService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Envia um email")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Email enviado"),
			@ApiResponse(code = 400, message = "Erro na requisição")
	})
	public void save(@RequestBody @Validated EmailSenderRequest emailSenderRequest) {
		emailService.sendEmail(emailSenderRequest);
	}
	
}
