package by.epam.travel_agency.controller.command.ajax.impl;

import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.paramName.RequestParameterName;
import by.epam.travel_agency.service.factory.ServiceFactory;
import by.epam.travel_agency.service.receiver.ReceiverException;
import by.epam.travel_agency.service.receiver.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Check available login.
 */
public class CheckAvailableLogin implements AjaxCommand {

    private static final Logger logger = Logger.getLogger(CheckAvailableLogin.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String answer = RequestParameterName.OK;

        UserService userService = ServiceFactory.getInstance().getUserService();

        String login = request.getParameter(RequestParameterName.PARAM_NAME_LOGIN);

        if (login == null) {

            answer = RequestParameterName.LOOSE_LOGIN;

        } else {

            try {
                if (userService.isThisLoginBusy(login)) {
                    answer = RequestParameterName.LOGIN_ALREADY_EXIST;
                    response.setStatus(409);
                }
            } catch (ReceiverException e) {
                logger.error(e);
                response.setStatus(406);
            }

        }
        logger.info(answer);
        return answer;
    }
}
