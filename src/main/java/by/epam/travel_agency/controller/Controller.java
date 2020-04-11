package by.epam.travel_agency.controller;

import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.ActionFactory;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.dao.connection_pool.ConnectionPoolFactory;
import by.epam.travel_agency.service.manager.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        ActionFactory factory = ActionFactory.getInstance();
        Command command = factory.defineCommand(request);
        page = command.execute(request);

        //DELETE
        System.out.println("Controller message");

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", command + MessageKey.NULL_PAGE);
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
        connectionPoolFactory.getConnectionPool().dispose();
    }
}