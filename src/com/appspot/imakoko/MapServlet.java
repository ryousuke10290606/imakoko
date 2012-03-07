package com.appspot.imakoko;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MapServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("tag", req.getParameter("tag"));

		RequestDispatcher dispatcher = getServletContext()
			.getRequestDispatcher("/WEB-INF/map.jsp");
		dispatcher.forward(req, resp);
	}
}
