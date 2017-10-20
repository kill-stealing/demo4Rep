package com.lmy.exception;


//@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Not found")
//@ResponseStatus(value =HttpStatus.)
public class CustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String message;
	public int code;
	
	public CustomException(String message,int code){
		super(message);
		this.message=message;
		this.code=code;
	}
	
	public CustomException(String message){
		super(message);
		this.message=message;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
