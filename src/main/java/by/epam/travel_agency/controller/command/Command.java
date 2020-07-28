package by.epam.travel_agency.controller.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Execute.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    /**
     * Forward to page.
     *
     * @param request  the request
     * @param response the response
     * @param path     the path
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    default void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        } else {
            throw new ServletException("Exception in forwardToPage() default method -> requestDispatcher = null.");
        }
    }
}
