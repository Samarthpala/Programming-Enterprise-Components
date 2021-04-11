package com.bank.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.AccountDao;
import com.bank.dao.TransactionDao;
import com.bank.entity.Account;
import com.bank.entity.Transaction;

/**
 * Servlet implementation class TransferServlet
 */
@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TransactionDao transactionDao;
	AccountDao accountDao;

	public TransferServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		transactionDao = new TransactionDao();
		accountDao = new AccountDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("accounts", accountDao.getAllAccount());
		RequestDispatcher dispatcher = request.getRequestDispatcher("transfer.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer accountToDebit = !request.getParameter("debitAccount").isEmpty()
				? Integer.valueOf(request.getParameter("debitAccount"))
				: null;
		Integer accountToCredit = !request.getParameter("creditAccount").isEmpty()
				? Integer.valueOf(request.getParameter("creditAccount"))
				: null;
		Double amount = Double.valueOf(request.getParameter("amount"));
		if (accountToCredit != null && accountToDebit != null) {
			Account creditAccount = accountDao.getAccount(accountToCredit);
			Account debitAccount = accountDao.getAccount(accountToDebit);
			creditAccount.setBalance(creditAccount.getBalance() + amount);
			debitAccount.setBalance(debitAccount.getBalance() - amount);
			Transaction transaction = new Transaction(new Date(), amount, 0.0, creditAccount.getBalance(),
					creditAccount);
			transactionDao.saveTransaction(transaction);
			accountDao.updateAccount(creditAccount);
			transaction = new Transaction(new Date(), 0.0, amount, debitAccount.getBalance(), debitAccount);
			transactionDao.saveTransaction(transaction);
			accountDao.updateAccount(debitAccount);
		} else if (accountToCredit != null && accountToDebit == null) {
			Account creditAccount = accountDao.getAccount(accountToCredit);
			creditAccount.setBalance(creditAccount.getBalance() + amount);
			Transaction transaction = new Transaction(new Date(), amount, 0.0, creditAccount.getBalance(),
					creditAccount);
			transactionDao.saveTransaction(transaction);
			accountDao.updateAccount(creditAccount);
		} else if (accountToCredit == null && accountToDebit != null) {
			Account debitAccount = accountDao.getAccount(accountToDebit);
			debitAccount.setBalance(debitAccount.getBalance() - amount);
			Transaction transaction = new Transaction(new Date(), 0.0, amount, debitAccount.getBalance(), debitAccount);
			transactionDao.saveTransaction(transaction);
			accountDao.updateAccount(debitAccount);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("transfer.jsp");
		dispatcher.forward(request, response);
	}

}
