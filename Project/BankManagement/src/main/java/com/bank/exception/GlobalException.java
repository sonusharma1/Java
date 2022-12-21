package com.bank.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<?> accountNotFoundHandling(AccountNotFoundException accountException, WebRequest request) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), accountException.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MaxDepositException.class)
	public ResponseEntity<?> maxDepositLimitHandling(MaxDepositException maxDepositLimit, WebRequest request)

	{
		ErrorDetail maxDeposit = new ErrorDetail(new Date(), maxDepositLimit.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(maxDeposit, HttpStatus.PAYLOAD_TOO_LARGE);
	}

	@ExceptionHandler(MaxWithdrawException.class)
	public ResponseEntity<?> maxWithdrawLimitHandling(MaxWithdrawException maxWithdrawLimit, WebRequest request) {
		ErrorDetail maxWithdraw = new ErrorDetail(new Date(), maxWithdrawLimit.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(maxWithdraw, HttpStatus.PAYLOAD_TOO_LARGE);

	}
}
