package br.com.itau.desafioitau.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.itau.desafioitau.exceptions.ChavePixNaoEncontradaException;
import br.com.itau.desafioitau.exceptions.ErroNegocioException;
import br.com.itau.desafioitau.exceptions.ErroValidacaoChaveException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(ErroValidacaoChaveException.class)
	public List<ErroDto> handle(ErroValidacaoChaveException exception) {
		List<ErroDto> dto = new ArrayList<>();
		
		ErroDto erro = new ErroDto(exception.getMessage());
		dto.add(erro);
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ChavePixNaoEncontradaException.class)
	public List<ErroDto> handle(ChavePixNaoEncontradaException exception) {
		List<ErroDto> dto = new ArrayList<>();
		
		ErroDto erro = new ErroDto(exception.getMessage());
		dto.add(erro);
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(ErroNegocioException.class)
	public List<ErroDto> handle(ErroNegocioException exception) {
		List<ErroDto> dto = new ArrayList<>();
		
		ErroDto erro = new ErroDto(exception.getMessage());
		dto.add(erro);
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public List<ErroDto> handle(Exception exception) {
		List<ErroDto> dto = new ArrayList<>();
		
		ErroDto erro = new ErroDto(exception.getMessage());
		dto.add(erro);
		
		return dto;
	}

}