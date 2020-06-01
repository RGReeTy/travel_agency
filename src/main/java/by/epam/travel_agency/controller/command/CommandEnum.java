package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.controller.command.locale.ChangeLocaleCommand;
import by.epam.travel_agency.controller.command.page.GoToControlPageCommand;
import by.epam.travel_agency.controller.command.page.GoToPageCommand;
import by.epam.travel_agency.controller.command.tour.GetAllHotelCommand;
import by.epam.travel_agency.controller.command.tour.GetAllTourCommand;
import by.epam.travel_agency.controller.command.tour.GetConcreteTourCommand;
import by.epam.travel_agency.controller.command.user.*;

public enum CommandEnum {

    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    GO_TO_PAGE {
        {
            this.command = new GoToPageCommand();
        }
    },
    LOGIN {
        {
            this.command = new LogInCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegisterCommand();
        }
    },
    SHOW_ACCOUNT {
        {
            this.command = new GetAccountInfoCommand();
        }
    },
    SHOW_TOURS {
        {
            this.command = new GetAllTourCommand();
        }
    },
    SHOW_CONCRETE_TOUR {
        {
            this.command = new GetConcreteTourCommand();
        }
    },
    SHOW_ALL_HOTELS {
        {
            this.command = new GetAllHotelCommand();
        }
    },
    SHOW_LEVEL_ACCESS {
        {
            this.command = new GetUsersLevelAccessInfoCommand();
        }
    },
    CHANGE_LEVEL_ACCESS {
        {
            this.command = new ChangeLevelAccessCommand();
        }
    },
    CONTROL {
        {
            this.command = new GoToControlPageCommand();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}