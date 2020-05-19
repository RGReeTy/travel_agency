package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.controller.command.locale.ChangeLocaleCommand;
import by.epam.travel_agency.controller.command.page.GoToPageCommand;
import by.epam.travel_agency.controller.command.page.ShowAdminPage;
import by.epam.travel_agency.controller.command.tour.ShowAllHotelCommand;
import by.epam.travel_agency.controller.command.tour.ShowAllTourCommand;
import by.epam.travel_agency.controller.command.tour.ShowConcreteTourCommand;
import by.epam.travel_agency.controller.command.user.LogInCommand;
import by.epam.travel_agency.controller.command.user.LogOutCommand;
import by.epam.travel_agency.controller.command.user.RegisterCommand;
import by.epam.travel_agency.controller.command.user.ShowAccountCommand;

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
            this.command = new ShowAccountCommand();
        }
    },
    SHOW_TOURS {
        {
            this.command = new ShowAllTourCommand();
        }
    },
    SHOW_CONCRETE_TOUR {
        {
            this.command = new ShowConcreteTourCommand();
        }
    },
    SHOW_ALL_HOTELS {
        {
            this.command = new ShowAllHotelCommand();
        }
    },
    ADMIN {
        {
            this.command = new ShowAdminPage();
        }
//    },
//    MANAGEMENT {
//        {
//            this.command = new ShowManagerPage();
//        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}