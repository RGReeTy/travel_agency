package by.epam.travel_agency.controller;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.command.CommandProvider;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/Controller")
@WebServlet(urlPatterns = "/Controller", name = "Controller")
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
        String commandName = request.getParameter(RequestParameterName.ACTION);
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getFrontCommand(commandName);
        command.execute(request, response);
    }
}