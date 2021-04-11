package com.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "branch")
public class Branch extends Base {

	@Column(name = "code")
	private String branchCode;

	@Column(name = "name")
	private String name;

	@Column(name = "IBAN")
	private String iban;

	@Column(name = "address")
	private String address;

	public Branch() {
	}

	public Branch(String bank_code, String name, String iban, String address) {
		super();
		this.branchCode = bank_code;
		this.name = name;
		this.iban = iban;
		this.address = address;
	}

	public String getBank_code() {
		return branchCode;
	}

	public void setBank_code(String bank_code) {
		this.branchCode = bank_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
