package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoValidation implements Validator {

	/*
	 * A implementação desse método indica a qual classe a validação dará suporte. 
	 * */
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	/*
	 * Usando o Spring Framework para validar o form Produto...
	 * Perceba que o método Validate é onde realmente acontecerá a validação. 
	 * Object é o objeto que queremos validar, no caso Produto
	 * Errors é um objeto que contém todos os erros de validação 
	 * 
	 * */
	
	@Override
	public void validate(Object target, Errors errors) {
		// validando se os campos titulo e descrição estão nulos ou vazios...
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");

		Produto produto = (Produto) target;
		
		// validando o campo página...
		if (produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required");
		}
	}

}
