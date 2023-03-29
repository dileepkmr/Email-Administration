package com.email.customException;

public class InvalidChoiceException extends RuntimeException
{
	private String mess;
	public InvalidChoiceException(String mess) {
		this.mess = mess;
	}


	@Override
	public String getMessage()
	{
		return mess;
	}

}
