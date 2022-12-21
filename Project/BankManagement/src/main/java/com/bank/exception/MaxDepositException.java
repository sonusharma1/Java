package com.bank.exception;

public class MaxDepositException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;

	public MaxDepositException(String resourceName, String fieldName) {
		super(String.format("%s Max Limit for %s is reached. Deposit limit at a time is Rs.50000.00 Only", resourceName,
				fieldName));
		this.resourceName = resourceName;
		this.fieldName = fieldName;

	}

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

}