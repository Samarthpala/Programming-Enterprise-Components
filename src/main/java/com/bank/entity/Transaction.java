package com.bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction extends Base {

	@Column(name = "date")
	private Date date;

	@Column(name = "deposit")
	private Double deposit;

	@Column(name = "withdraw")
	private Double withdraw;

	@Column(name = "balance")
	private Double balance;

	@OneToOne(fetch = FetchType.EAGER)
	private Account account;

	public Transaction() {

	}

	public Transaction(Date date, Double deposit, Double withdraw, Double balance, Account account) {
		super();
		this.date = date;
		this.deposit = deposit;
		this.withdraw = withdraw;
		this.balance = balance;
		this.account = account;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Double withdraw) {
		this.withdraw = withdraw;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [date=" + date + ", deposit=" + deposit + ", withdraw=" + withdraw + ", balance=" + balance + "]";
	}

}
