package com.bank.emailsender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Class
public class EmailDetails {

	// Class data members
	private String recipient;
	private String msgBody;
	private String subject;
}