package com.bank.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends Base {

	@Column(name = "name")
	private String name;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "number")
	private String phoneNumber;

	@Column(name = "salution")
	private String salution;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Account> accounts;

	public User() {
	}

	public User(String name, String username, String password, String email, String address, String number,
			String salution) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = number;
		this.salution = salution;
	}

	public User(int id, String name, String email, String address, String number, String salution) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNumber = number;
		this.salution = salution;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSalution() {
		return salution;
	}

	public void setSalution(String salution) {
		this.salution = salution;
	}

}
