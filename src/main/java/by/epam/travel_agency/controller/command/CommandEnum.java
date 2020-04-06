package main.java.by.epam.travel_agency.controller.command;

import main.java.by.epam.travel_agency.controller.command.locale.ChangeLocaleCommand;
import main.java.by.epam.travel_agency.controller.command.page.*;
import main.java.by.epam.travel_agency.controller.command.user.*;

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
    USERS_PAGE {
        {
            this.command = new UserPageCommand();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}