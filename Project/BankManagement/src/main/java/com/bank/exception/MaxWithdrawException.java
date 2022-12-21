package com.bank.exception;

public class MaxWithdrawException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;

	public MaxWithdrawException(String resourceName, String fieldName) {
		super(String.format("%s Max Limit for %s is reached.Daily Withdraw limit is RS.25000.00 ONLY", resourceName,
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
