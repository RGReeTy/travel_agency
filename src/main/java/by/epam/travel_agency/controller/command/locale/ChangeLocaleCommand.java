package by.epam.travel_agency.controller.command.locale;

import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter("locale");
        String page = request.getParameter("page");
        request.getSession().setAttribute("locale", language);

        logger.info("language = " + language + ", page = " + page);

        if (page == null) {
            return ConfigurationManager.getProperty("path.page.main");
        }
        return ConfigurationManager.getProperty(page);
    }
}
