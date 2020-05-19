package by.epam.travel_agency.controller.command.page;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.constant.MessageKey;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.service.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

import static by.epam.travel_agency.service.validation.UserValidator.checkUserIsAdmin;

public class ShowAdminPage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter("page");
        User user = (User) request.getSession().getAttribute("user");
        if (checkUserIsAdmin(user)) {
            return ConfigurationManager.getProperty("path.page.admin");
        } else {
            request.setAttribute("message", MessageKey.ILLEGAL_LEVEL_ACCESS);
            return ConfigurationManager.getProperty("path.page.error");
        }
    }
}
