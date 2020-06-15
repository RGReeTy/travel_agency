package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.service.receiver.TourReceiver;
import by.epam.travel_agency.service.receiver.UserReceiver;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);
}
