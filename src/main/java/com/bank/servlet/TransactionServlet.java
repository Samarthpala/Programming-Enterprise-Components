package com.bank.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.TransactionDao;
import com.bank.entity.Transaction;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TransactionDao transactionDao;

	public void init() {
		transactionDao = new TransactionDao();
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
				insertTransaction(request, response);
				break;
			case "delete":
				deleteTransaction(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateTransaction(request, response);
				break;
			default:
				listTransaction(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listTransaction(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Transaction> listTransaction = transactionDao.getAllTransaction();
		request.setAttribute("listTransaction", listTransaction);
		RequestDispatcher dispatcher = request.getRequestDispatcher("transaction-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("transaction-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Transaction existingTransaction = transactionDao.getTransaction(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("transaction-form.jsp");
		request.setAttribute("transaction", existingTransaction);
		dispatcher.forward(request, response);

	}

	private void insertTransaction(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		Double deposit = Double.valueOf(request.getParameter("deposit"));
		Double withdraw = Double.valueOf(request.getParameter("withdraw"));
		Double balance = Double.valueOf(request.getParameter("balance"));
		Transaction newTransaction = new Transaction(new Date(), deposit, withdraw, balance, null);
		transactionDao.saveTransaction(newTransaction);
		response.sendRedirect("list");
	}

	private void updateTransaction(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Double deposit = Double.valueOf(request.getParameter("deposit"));
		Double withdraw = Double.valueOf(request.getParameter("withdraw"));
		Double balance = Double.valueOf(request.getParameter("balance"));

		Transaction transaction = new Transaction(new Date(), deposit, withdraw, balance, null);
		transactionDao.updateTransaction(transaction);
		response.sendRedirect("list");
	}

	private void deleteTransaction(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		transactionDao.deleteTransaction(id);
		response.sendRedirect("list");
	}
}
