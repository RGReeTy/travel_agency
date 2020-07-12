package by.epam.travel_agency.controller.command.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Ajax command.
 */
public interface AjaxCommand {

    /**
     * Execute string.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     */
    String execute(HttpServletRequest request, HttpServletResponse response);


}