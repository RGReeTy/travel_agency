package by.epam.travel_agency.controller.command.ajax.impl;

import by.epam.travel_agency.bean.User;
import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Update user profile.
 */
public class UpdateUserProfile implements AjaxCommand {

    private static final Logger logger = Logger.getLogger(UpdateUserProfile.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String answer = RequestParameterName.OK;
        HttpSession session = request.getSession();

        UserService userService = ServiceFactory.getInstance().getUserService();


        String firstname = request.getParameter(RequestParameterName.FIRSTNAME);
        String lastname = request.getParameter(RequestParameterName.LASTNAME);
        String email = request.getParameter(RequestParameterName.EMAIL);
        String phone = request.getParameter(RequestParameterName.PHONE);

        User user = (User) session.getAttribute(RequestParameterName.USER);

        if (firstname == null & lastname == null & email == null & phone == null) {
            answer = RequestParameterName.EMPTY_FIELDS;
        } else {
            if (user != null) {
                try {
                    userService.updateUserInfo(user, firstname, lastname, email, phone);
                } catch (ReceiverException e) {
                    logger.error(e);
                    response.setStatus(500);
                }
            }
        }
        return answer;
    }
}
