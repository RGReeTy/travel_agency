package by.epam.pharmacy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.by.epam.travel_agency.controller.command.ActionFactory;
import main.java.by.epam.travel_agency.controller.command.Command;
import main.java.by.epam.travel_agency.dao.connectionpool.ConnectionPool;
import by.epam.pharmacy.constant.MessageKey;
import main.java.by.epam.travel_agency.service.manager.ConfigurationManager;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	/**
	 * The class controls the operation of the application, makes a redirect or
	 * forward to the jsp page
	 */

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		// define the command that came from the JSP
		ActionFactory factory = ActionFactory.getInstance();
		Command command = factory.defineCommand(request);
		/*
		 * call the implemented execute () method and pass parameters to class
		 * of a specific command
		 */
		page = command.execute(request);
		// method returns a response page
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			// call a response page to the request
			dispatcher.forward(request, response);
		} else {
			// setting up a page with an error message
			page = ConfigurationManager.getProperty("path.page.index");
			request.getSession().setAttribute("nullPage", command + MessageKey.NULL_PAGE);
			response.sendRedirect(request.getContextPath() + page);
		}
	}

	@Override
	public void destroy() {
		ConnectionPool.getInstance().closeConnectionsInPool();
	}
}