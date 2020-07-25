package by.epam.travel_agency.controller;


import by.epam.travel_agency.controller.command.CommandProvider;
import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Ajax controller.
 */
@WebServlet(urlPatterns = "/ajax", name = "AjaxController")
@MultipartConfig
public class AjaxController extends HttpServlet {

    private static Logger logger = LogManager.getLogger(AjaxController.class);

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ajaxCommandName = request.getParameter(RequestParameterName.COMMAND);
        CommandProvider ajaxCommandProvider = CommandProvider.getInstance();
        AjaxCommand ajaxCommand = ajaxCommandProvider.getAjaxCommand(ajaxCommandName.toUpperCase());

        String jsonAnswer = ajaxCommand.execute(request, response);

        PrintWriter out = response.getWriter();
        out.print(jsonAnswer);
        out.flush();

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}