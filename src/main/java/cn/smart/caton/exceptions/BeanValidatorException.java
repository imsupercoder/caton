package cn.smart.caton.exceptions;
 	
@SuppressWarnings("serial")
public class BeanValidatorException extends RuntimeException {

	public BeanValidatorException(){
		super();
	}
	
	public BeanValidatorException(String message){
		super(message);
	}
}
