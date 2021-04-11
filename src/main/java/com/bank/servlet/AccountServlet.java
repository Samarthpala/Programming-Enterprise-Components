package com.bank.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.AccountDao;
import com.bank.dao.UserDao;
import com.bank.entity.Account;
import com.bank.entity.Branch;
import com.bank.entity.User;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDao accountDao;
	private UserDao userDao;

	public void init() {
		accountDao = new AccountDao();
		userDao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action") == null ? "list" : request.getParameter("action");

		try {
			switch (action) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertAccount(request, response);
				break;
			case "delete":
				deleteAccount(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateAccount(request, response);
				break;
			default:
				listAccount(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Account> listAccount = accountDao.getAllAccount();
		request.setAttribute("listAccount", listAccount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("account-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("account-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Account existingAccount = accountDao.getAccount(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("account-form.jsp");
		request.setAttribute("account", existingAccount);
		dispatcher.forward(request, response);

	}

	private void insertAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String accountName = request.getParameter("accountName");
		Random rand = new Random();
		String number = "";
		for (int i = 0; i < 14; i++) {
			int n = rand.nextInt(10) + 0;
			number += Integer.toString(n);
		}
		String accountNumber = number;
		String bank_code = request.getParameter("bank_code");
		String name = request.getParameter("name");
		String iban = request.getParameter("iban");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		Branch branch = new Branch(bank_code, name, iban, address);

		User user = userDao.getUser(1);
		Account newAccount = new Account(accountName, accountNumber, 0.0, type, branch);
//		user.getAccounts().add(newAccount);
		newAccount.setUser(user);
//		userDao.saveUser(user);
		accountDao.saveAccount(newAccount);
		response.sendRedirect("account");
	}

	private void updateAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String accountName = request.getParameter("accountName");
		Account account = new Account();
		account.setId(id);
		account.setAccountName(accountName);
		accountDao.updateAccount(account);
		response.sendRedirect("account");
	}

	private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		accountDao.deleteAccount(id);
		response.sendRedirect("account");
	}
}
