package com.qa.bank.domain;

import java.sql.Date;

public class Transaction
{
	
	private int Amount;
	private Date Date;
	
	public int getAmount() {
		return Amount;
	}

	public Date getDate() {
		return Date;
	}

	public Transaction(int Amount, Date Date)
	{
		this.Amount = Amount;
		this.Date = Date;
	}

}
