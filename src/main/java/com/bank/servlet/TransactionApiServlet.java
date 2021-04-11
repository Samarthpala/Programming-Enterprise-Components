package com.bank.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.AccountDao;
import com.bank.dao.TransactionDao;
import com.bank.dao.UserDao;
import com.bank.entity.APIResult;
import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.entity.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class TransactionApiServlet
 */
@WebServlet("/transaction/api")
public class TransactionApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransactionApiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private TransactionDao transactionDao;
	private AccountDao accountDao;
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		transactionDao = new TransactionDao();
		accountDao = new AccountDao();
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String authorization = request.getHeader("Authorization");
		User user = null;
		if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
		    // Authorization: Basic base64credentials
		    String base64Credentials = authorization.substring("Basic".length()).trim();
		    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		    // credentials = username:password
		    final String[] values = credentials.split(":", 2);
		    user = userDao.getUserByEmailAndPassword(values[0], values[1]);
		}
		if(user != null) {
			String date = request.getParameter("date");
			String accountNumber = request.getParameter("accountNumber");
			Account account = accountDao.getAccountByAccountNumber(accountNumber);
			List<Transaction> transactions = new ArrayList<Transaction>();
			if (account != null) {
				DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				Date transactionDate = new Date();
				try {
					transactionDate = formatter.parse(date);
				} catch (ParseException e) {
				}
				transactions = transactionDao.getTransactionsByDate(transactionDate, account.getId());
			}
			List<APIResult> apiResults = new ArrayList<APIResult>();
			for (Transaction transaction : transactions) {
				apiResults.add(new APIResult(transaction.getDate(), transaction.getDeposit(), transaction.getWithdraw(), transaction.getBalance(), transaction.getAccount().getAccountName(), transaction.getAccount().getAccountNumber(), transaction.getAccount().getType()));
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Total Transaction", transactions.size());
			map.put("result", apiResults);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(map));
		} else {
			response.getWriter().write("Invalid Credential");
		}

	}
}
