package br.com.alura.forum.conf.Validacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//pegando todos erro do formulario

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	//pegar msg do erro injertar de umas das classes Spring
	@Autowired
	private MessageSource messageSource;
	
	//pra continuar devolvendo codigo 400 mesmo tratando a exepction, iremos tratar msg de erro e nao o codigo, padrao pode ser codigo 400 de erro
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) //execao que lança em erro de formulario e MethodArgumentNotValidException
	public List<ErroDeFormularioDto>handle(MethodArgumentNotValidException expection) { //toda vez que dar uma exption de formulario ira cai aqui
		
		List<ErroDeFormularioDto> dto = new ArrayList<>();
	
		List<FieldError> fieldErrors = expection.getBindingResult().getFieldErrors(); //pegando todos erro do formulario
		
		for(FieldError fieldError : fieldErrors) {
			
			String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());//pega msg de msg dos erros, LocaleContextHolder.getLocale() pega localidade para qual idioma ira retornar
			ErroDeFormularioDto erro = new ErroDeFormularioDto(fieldError.getField(), mensagem); //add meus dto a msg de erro gerada
			dto.add(erro);
		}
		return dto;

	}
}
