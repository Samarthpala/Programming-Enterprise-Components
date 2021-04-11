package com.bank.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.UserDao;
import com.bank.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userDao.getUserByEmailAndPassword(email, password);

		if (user == null) {
			request.setAttribute("login_msg", "Invalid Credential");
			response.setStatus(401);
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("home.jsp");
		}
	}

}
