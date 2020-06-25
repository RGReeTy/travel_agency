package by.epam.travel_agency.controller.command;

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

public final class CommandProvider {

    private static final Logger logger = Logger.getLogger(CommandProvider.class);


    private final static CommandProvider instance = new CommandProvider();

    private final Map<CommandName, Command> frontRepository = new HashMap<>();
    //private final Map<AjaxCommandName, AjaxCommand> ajaxRepository = new HashMap<>();

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

//        ajaxRepository.put(AjaxCommandName.EDIT_USER, new EditUser());
//        ajaxRepository.put(AjaxCommandName.SHOW_QUESTION, new ShowQuestion());
//        ajaxRepository.put(AjaxCommandName.SAVE_ANSWER, new SaveAnswer());
//        ajaxRepository.put(AjaxCommandName.GET_TESTS, new GetTests());
//        ajaxRepository.put(AjaxCommandName.ASSIGN_TEST, new AssignTest());
//        ajaxRepository.put(AjaxCommandName.GET_ASSIGNED_USERS, new GetAssignedUsers());
//        ajaxRepository.put(AjaxCommandName.DELETE_ASSIGNMENT, new DeleteAssignment());
//        ajaxRepository.put(AjaxCommandName.SHOW_RESULT_DATA, new ShowResultData());
//        ajaxRepository.put(AjaxCommandName.DELETE_TEST, new DeleteTest());
//        ajaxRepository.put(AjaxCommandName.CREATE_TEST, new CreateTest());
//        ajaxRepository.put(AjaxCommandName.CREATE_QUESTION_ANSWER, new CreateQuestionAnswer());
//        ajaxRepository.put(AjaxCommandName.UPDATE_QUESTION, new UpdateQuestion());
//        ajaxRepository.put(AjaxCommandName.COMPLETE_TEST, new CompleteTestCreation());
//        ajaxRepository.put(AjaxCommandName.DELETE_QUESTION, new DeleteQuestion());
//        ajaxRepository.put(AjaxCommandName.UPDATE_TEST_INFO, new UpdateTestInfo());


    }

    public static CommandProvider getInstance() {
        return instance;
    }

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

//    public AjaxCommand getAjaxCommand(String name) {
//        AjaxCommandName ajaxCommandName;
//        AjaxCommand ajaxCommand;
//
//        if (name != null) {
//            ajaxCommandName = AjaxCommandName.valueOf(name.toUpperCase());
//            ajaxCommand = ajaxRepository.get(ajaxCommandName);
//
//            if (ajaxCommand == null) {
//                ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_COMMAND);
//            }
//        } else {
//            ajaxCommand = ajaxRepository.get(AjaxCommandName.NO_COMMAND);
//
//        }
//        return ajaxCommand;
//    }
}
