package com.bank.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends Base {

	@Column(name = "name")
	private String accountName;

	@Column(name = "number")
	private String accountNumber;

	@Column(name = "balance")
	private Double balance;

	@Column(name = "type")
	private String type;

	@OneToOne(cascade = CascadeType.ALL)
	private Branch branch;

	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Account() {

	}

	public Account(String accountName, String accountNumber, Double balance, String type, Branch branch) {
		super();
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.type = type;
		this.branch = branch;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [accountName=" + accountName + ", accountNumber=" + accountNumber + ", balance=" + balance
				+ ", type=" + type + ", branch=" + branch + "]";
	}

}
