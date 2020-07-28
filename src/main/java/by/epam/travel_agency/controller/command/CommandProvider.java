package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.controller.command.ajax.AjaxCommand;
import by.epam.travel_agency.controller.command.ajax.AjaxCommandName;
import by.epam.travel_agency.controller.command.ajax.impl.*;
import by.epam.travel_agency.controller.command.locale.ChangeLocaleCommand;
import by.epam.travel_agency.controller.command.navigation.GoToControlPageCommand;
import by.epam.travel_agency.controller.command.navigation.GoToCreateNewTourCommand;
import by.epam.travel_agency.controller.command.navigation.GoToPageCommand;
import by.epam.travel_agency.controller.command.navigation.WrongCommand;
import by.epam.travel_agency.controller.command.tour.*;
import by.epam.travel_agency.controller.command.user.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Command provider.
 */
public final class CommandProvider {

    private static final Logger logger = Logger.getLogger(CommandProvider.class);


    private final static CommandProvider instance = new CommandProvider();

    private final Map<CommandName, Command> frontRepository = new HashMap<>();
    private final Map<AjaxCommandName, AjaxCommand> ajaxRepository = new HashMap<>();

    private CommandProvider() {
        frontRepository.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        frontRepository.put(CommandName.GO_TO_PAGE, new GoToPageCommand());
        frontRepository.put(CommandName.LOGIN, new LogInCommand());
        frontRepository.put(CommandName.LOGOUT, new LogOutCommand());
        frontRepository.put(CommandName.REGISTRATION, new RegisterCommand());
        frontRepository.put(CommandName.SHOW_ACCOUNT, new GetPersonalInfoCommand());
        frontRepository.put(CommandName.SHOW_TOURS, new GetAllTourCommand());
        frontRepository.put(CommandName.SHOW_CONCRETE_TOUR, new GetConcreteTourCommand());
        frontRepository.put(CommandName.SHOW_ALL_HOTELS, new GetAllHotelCommand());
        frontRepository.put(CommandName.SHOW_LEVEL_ACCESS, new GetUsersLevelAccessInfoCommand());
        frontRepository.put(CommandName.CHANGE_LEVEL_ACCESS, new ChangeLevelAccessCommand());
        frontRepository.put(CommandName.CONTROL, new GoToControlPageCommand());
        frontRepository.put(CommandName.GET_PAYMENT_HISTORY, new GetAllDefrayalCommand());
        frontRepository.put(CommandName.GET_PAYMENT_DEBT, new GetPaymentDebtCommand());
        frontRepository.put(CommandName.GET_INFO_ABOUT_USER, new GetAccountInfoCommand());
        frontRepository.put(CommandName.CREATE_TOUR_PAGE, new GoToCreateNewTourCommand());
        frontRepository.put(CommandName.CREATE_TOUR, new CreateNewTourCommand());
        frontRepository.put(CommandName.WRONG_COMMAND, new WrongCommand());


        ajaxRepository.put(AjaxCommandName.UPDATE_USER_PROFILE, new UpdateUserProfile());
        ajaxRepository.put(AjaxCommandName.SHOW_SINGLE_TOUR, new GetSingleTour());
        ajaxRepository.put(AjaxCommandName.CREATE_NEW_DEFRAYAL_FROM_ANONIM, new CreateNewDefrayalFromAnonim());
        ajaxRepository.put(AjaxCommandName.CREATE_NEW_DEFRAYAL_FROM_USER, new CreateNewDefrayalFromUser());
        ajaxRepository.put(AjaxCommandName.CONFIRM_THE_PAYMENT, new ConfirmThePayment());
        ajaxRepository.put(AjaxCommandName.CHECK_AVAILABLE_LOGIN, new CheckAvailableLogin());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandProvider getInstance() {
        return instance;
    }

    /**
     * Gets front command.
     *
     * @param name the name
     * @return the front command
     */
    public Command getFrontCommand(String name) {
        logger.info("getFrontCommand start. Name of action = " + name);

        CommandName commandName;
        Command command;

        if (name == null) {
            command = frontRepository.get(CommandName.WRONG_COMMAND);
        } else {

            commandName = CommandName.valueOf(name.toUpperCase());
            command = frontRepository.get(commandName);

            if (command == null) {
                command = frontRepository.get(CommandName.WRONG_COMMAND);
            }
        }

        logger.info("getFrontCommand end  =  " + command.getClass());
        return command;
    }

    /**
     * Gets ajax command.
     *
     * @param name the name
     * @return the ajax command
     */
    public AjaxCommand getAjaxCommand(String name) {
        AjaxCommandName ajaxCommandName;
        AjaxCommand ajaxCommand;

        if (name != null) {
            ajaxCommandName = AjaxCommandName.valueOf(name.toUpperCase());
            ajaxCommand = ajaxRepository.get(ajaxCommandName);

            if (ajaxCommand == null) {
                ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_COMMAND);
            }
        } else {
            ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_COMMAND);

        }
        return ajaxCommand;
    }
}
