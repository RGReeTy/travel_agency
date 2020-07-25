package by.epam.travel_agency.controller.command.locale;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String language = request.getParameter(RequestParameterName.LOCALE);
        String page = request.getParameter(RequestParameterName.PAGE);

        request.getSession().setAttribute(RequestParameterName.LOCALE, language);

        logger.info("language = " + language + ", page = " + page);
        logger.info(request.getContextPath());
        logger.info(request.getContextPath() + ConfigurationManager.getProperty(RequestParameterName.PAGE_MAIN));

        if (page != null) {
            forwardToPage(request, response, ConfigurationManager.getProperty(page));
           // response.sendRedirect(request.getContextPath() + ConfigurationManager.getProperty(RequestParameterName.PAGE_MAIN));
        } else {
            response.sendRedirect(request.getContextPath());
        }



        //TODO del
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String page = null;
//        ActionFactory factory = ActionFactory.getInstance();
//        Command command = factory.defineCommand(request);
//        page = command.execute(request);
//
//        logger.info(page);
//
//        if (page != null) {
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
//            dispatcher.forward(request, response);
//        } else {
//            page = ConfigurationManager.getProperty("path.page.main");
//            request.getSession().setAttribute("nullPage", command + MessageKey.NULL_PAGE);
//            response.sendRedirect(request.getContextPath() + page);
//        }

//        default void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
//            if (requestDispatcher != null) {
//                requestDispatcher.forward(request, response);
//            } else {
//                throw new ServletException("Exception in forwardToPage() default method -> requestDispatcher = null.");
//            }
//        }
//    }
    }
}
