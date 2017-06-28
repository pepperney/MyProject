package cn.com.pepper.exception;

import java.sql.SQLException;

 
public class MyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String message;
	
	public MyException(RuntimeException e){
		this.message=e.getMessage();
	}
	public MyException(SQLException e){
		this.message=e.getMessage();
	}
	public MyException(String message){
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
