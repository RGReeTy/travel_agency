package by.epam.travel_agency.controller;

import by.epam.travel_agency.controller.command.ActionFactory;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        ActionFactory factory = ActionFactory.getInstance();
        Command command = factory.defineCommand(request);
        page = command.execute(request);

        logger.info(page);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.main");
            request.getSession().setAttribute("nullPage", command + MessageKey.NULL_PAGE);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}