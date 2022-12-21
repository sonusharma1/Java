package com.bank.model;

import javax.validation.constraints.NotNull;

import com.bank.entity.AccountDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDTO {

	private int id;

	private String userName;

	private String password;
}
