package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.receiver.UserReceiver;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    UserReceiver USER_RECEIVER = UserReceiver.getInstance();
    //OrderReceiver ORDER_RECEIVER = OrderReceiver.getInstance();

    String execute(HttpServletRequest request);
}
