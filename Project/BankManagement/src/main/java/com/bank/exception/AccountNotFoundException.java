package com.bank.exception;

public class AccountNotFoundException extends RuntimeException {
	
	//creating instance vairable 
	private static final long serialVersionUID =1L;
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	// using contructor with fields and psssing message to display 
	public AccountNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not Found  with %s : %s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

    // getter & setter
	public String getResourceName() {
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


	public String getFieldName() {
		return fieldName;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public Object getFieldValue() {
		return fieldValue;
	}


	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	
	
}
