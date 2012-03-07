package com.appspot.imakoko;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RequestDispatcher dispacher = getServletContext()
			.getRequestDispatcher("/WEB-INF/index.jsp");
		dispacher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String nickname = req.getParameter("nickname");
		String tag = req.getParameter("tag");
		String message = req.getParameter("message");
		double lat = Double.parseDouble(req.getParameter("lat"));
		double lng = Double.parseDouble(req.getParameter("lng"));

        /* nicknameが入力されていることをチェックする */
        boolean hasErrors = false;
        if (nickname == null || "".equals(nickname)) {
            req.setAttribute("error_nickname", true);
            hasErrors = true;
        }
        if (hasErrors) {
            req.setAttribute("nickname", nickname);
            req.setAttribute("tag", tag);
            req.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/WEB-INF/index.jsp");
            rd.forward(req, resp);
            return;
        }

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Place place = new Place(nickname, tag, message, lat, lng);
			pm.makePersistent(place);
		} finally {
			pm.close();
		}

		req.setAttribute("nickname", nickname);
		req.setAttribute("tag", tag);
		RequestDispatcher dispatcher = getServletContext()
			.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(req, resp);

	}

}
