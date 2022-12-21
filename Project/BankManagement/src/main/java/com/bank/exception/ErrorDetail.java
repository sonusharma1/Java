
package com.bank.exception;

import java.util.Date;

public class ErrorDetail {
	
	// creating instance variable
	private Date timestamp;
	 
	private String message;
	
	private  String details;

	//getter & setter
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	//Constructor using fields
	public ErrorDetail(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	//Constructor using superclass
	public ErrorDetail() {
		super();
		// TODO Auto-generated constructor stub
	}



}
