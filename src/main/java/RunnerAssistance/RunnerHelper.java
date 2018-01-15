package RunnerAssistance;

import Person.Guest;
import Person.ProtoGuest;
import WebSite.Site;

import java.util.ArrayList;
import java.util.Arrays;

public class RunnerHelper {


    private Site site;
    private ArrayList<String> keywords;


    public RunnerHelper(Site site) {

        this.site = site;

        this.keywords = new ArrayList<>(Arrays.asList("yes", "no", "y", "n", "user", "last", "first", "balance", "user name", "username", "first name", "last name", "starting balance", "login", "finish"));

    }


    public void welcomeScreen() {


        TerminalHelper.flushMacScreen();


        System.out.println(String.format("Welcome to %s, the hotel booking site.\n", this.site.getSiteName()));
        System.out.println("This site uses '' around keywords to signify that they can be used as input.");
        System.out.println("Please do not include the '' when typing.\n\n");
        System.out.println("At any time you may type 'quit' to leave the site\n\n");

        userLoginQuery();

    }

    
    private void userLoginQuery() {


        String userName;


        System.out.println("Please enter your username to continue, or type 'new' to create a new account\n\n");

        System.out.println(String.format("\n[Accounts already on System: %s]", site.getUserNamesAsString()));


        userName = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (TerminalHelper.stringIsEmpty(userName)) {

            System.out.println("Please enter a user name, or type 'new' to create a new account\n\n");

            userLoginQuery();

        } else if (this.keywords.contains(userName)) {

            System.out.println("You have entered a reserved word, please try another user name\n\n");

            userLoginQuery();

        } else if (!TerminalHelper.stringIsAlphaNumeric(userName)) {

            System.out.println("Please use only letters of the English alphabet, and whole numbers.\n\n");

            userLoginQuery();

        } else if (userName.equals("new")) {

            ProtoGuest newAccount = new ProtoGuest();

            getAcceptableUserName(newAccount);

        }

        checkActiveUser(userName);

    }

    private void checkActiveUser(String userName) {

        Guest activeAccount;
        boolean isActiveUser = this.site.checkIfHaveAccount(userName);

        if (!isActiveUser) {

            System.out.println(String.format("The user name '%s' has not been found\n", userName));
            System.out.println("Would you like to create a 'new' account, or try to 'login' again?\n");

            String userChoice = TerminalHelper.getInput();

            if (userChoice.equals("new")) {

                TerminalHelper.flushMacScreen();

                ProtoGuest newAccount = new ProtoGuest();

                newAccount.setUserName(userName);

                getAcceptableUserNameCheck(newAccount);

            } else if (userChoice.equals("login")) {

                TerminalHelper.flushMacScreen();

                userLoginQuery();

            } else {

                TerminalHelper.flushMacScreen();

                System.out.println("Please enter 'new' to create a new account, or 'login' to try again\n\n");

                checkActiveUser(userName);
            }
        } else {

            activeAccount = this.site.findUserAccount(userName);

            this.site.enterCurrentUser(activeAccount);

            TerminalHelper.flushMacScreen();

            mainMenu();

        }
    }

    private void mainMenu() {


        Guest user = this.site.getCurrentUser();

        ArrayList<String> allowedOptions = new ArrayList<>(Arrays.asList("1", "account", "2", "booking", "3", "new", "logout"));


        System.out.println("Main Menu");
        System.out.println("---------\n\n");
        System.out.println(String.format("Welcome %s - please select one of the following options:\n", user.getFirstName()));
        System.out.println("'1' - View 'Account' Details");
        System.out.println("'2' - View 'Booking' History");
        System.out.println("'3' - Make 'New' Booking");
        System.out.println("\n\n'Logout' ");
        System.out.println("\n'Quit'\n--------\n\n");


        String userChoice = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (!allowedOptions.contains(userChoice)) {

            System.out.println("Please select one of the given options\n\n");

            mainMenu();

        } else if (userChoice.equals("1") || userChoice.equals("account")) {

            viewActiveAccountDetails();

        } else if (userChoice.equals("2") || userChoice.equals("booking")) {

            viewActiveAccountBookings();

        } else if (userChoice.equals("3") || userChoice.equals("new")) {

//            makeNewBooking();

        } else if (userChoice.equals("logout")) {

            this.site.clearCurrentUser();

            userLoginQuery();

        }
    }

    private void viewActiveAccountBookings() {


        Guest user = this.site.getCurrentUser();

        ArrayList<String> allowedOptions = new ArrayList<>(Arrays.asList("1", "historic", "2", "active", "3", "return", "logout"));


        System.out.println(String.format("Booking Details - %s\n\n", user.getName()));
        System.out.println("Please select one of the following options:\n");
        System.out.println("'1' - View 'Active' Bookings");
        System.out.println("'2' - View 'Historic' Bookings");
        System.out.println("'3' - 'Return' to Main Menu");
        System.out.println("\n\n'Logout' ");
        System.out.println("\n'Quit'\n--------\n\n");


        String userChoice = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (!allowedOptions.contains(userChoice)) {

            System.out.println("Please select one of the given options\n\n");

            viewActiveAccountBookings();

        } else if (userChoice.equals("1") || userChoice.equals("active")) {

//            viewActiveAccountActiveBookings();

        } else if (userChoice.equals("2") || userChoice.equals("historic")) {

//            viewActiveAccountHistoricBookings();

        } else if (userChoice.equals("3") || userChoice.equals("return")) {

            mainMenu();

        } else if (userChoice.equals("logout")) {

            this.site.clearCurrentUser();

            userLoginQuery();

        }
    }

    private void viewActiveAccountDetails() {


        Guest user = this.site.getCurrentUser();

        ArrayList<String> allowedOptions = new ArrayList<>(Arrays.asList("1", "edit", "2", "transfer", "3", "return", "logout"));


        System.out.println(String.format("Account Details - %s\n\n", user.getName()));
        System.out.println(String.format("User Name: %s", user.getUserName()));
        System.out.println(String.format("First Name: %s", user.getFirstName()));
        System.out.println(String.format("Last Name: %s", user.getLastName()));
        System.out.println(String.format("Wallet Balance: %d", user.getWallet()));
        System.out.println("\n\n");
        System.out.println("Please select one of the following options:\n");
        System.out.println("'1' - 'Edit' First or Last Name");
        System.out.println("'2' - 'Transfer' Additional Funds to Wallet Balance");
        System.out.println("'3' - 'Return' to Main Menu");
        System.out.println("\n\n'Logout' ");
        System.out.println("\n'Quit'\n--------\n\n");


        String userChoice = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (!allowedOptions.contains(userChoice)) {

            System.out.println("Please select one of the given options\n\n");

            viewActiveAccountDetails();

        } else if (userChoice.equals("1") || userChoice.equals("edit")) {

            editActiveAccountDetails();

        } else if (userChoice.equals("2") || userChoice.equals("transfer")) {

            addFundsToActiveAccountWallet();

        } else if (userChoice.equals("3") || userChoice.equals("return")) {

            mainMenu();

        } else if (userChoice.equals("logout")) {

            this.site.clearCurrentUser();

            userLoginQuery();

        }
    }

    private void addFundsToActiveAccountWallet() {


        Guest user = this.site.getCurrentUser();


        System.out.println(String.format("Transfer Fund To Wallet - %s\n\n", user.getName()));
        System.out.println(String.format("Wallet Balance: %d", user.getWallet()));
        System.out.println("\n\n");
        System.out.println("'1' - 'Return' to Account Details");
        System.out.println("\n\n'Logout' ");
        System.out.println("\n'Quit'\n--------\n\n");
        System.out.print("Please Enter Amount to Transfer: £");


        String userChoice = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (userChoice.equals("1") || userChoice.equals("return")) {

            viewActiveAccountDetails();

        } else if (userChoice.equals("logout")) {

            this.site.clearCurrentUser();

            userLoginQuery();

        } else if (!TerminalHelper.stringIsNumeric(userChoice)) {

            System.out.println("Please use only whole numbers");
            System.out.println("-----------------------------\n\n");

            addFundsToActiveAccountWallet();

        } else {

            Long userChoiceLong = Long.parseLong(userChoice);
            user.addMoney(userChoiceLong);

            System.out.println(String.format("Wallet Successfully credited by: £%d\n\n", userChoiceLong));

            viewActiveAccountDetails();

        }

    }

    private void editActiveAccountDetails() {


        Guest user = this.site.getCurrentUser();


        ArrayList<String> allowedOptions = new ArrayList<>(Arrays.asList("1", "first", "2", "last", "3", "return", "logout"));


        System.out.println(String.format("Edit Details - %s\n\n", user.getName()));
        System.out.println(String.format("First Name: %s", user.getFirstName()));
        System.out.println(String.format("Last Name: %s", user.getLastName()));
        System.out.println("\n\n");
        System.out.println("Please select one of the following options:\n");
        System.out.println("'1' - Edit 'First' Name");
        System.out.println("'2' - Edit 'Last' Name");
        System.out.println("'3' - 'Return' to Account Details");
        System.out.println("\n\n'Logout' ");
        System.out.println("\n'Quit'\n--------\n\n");


        String userChoice = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (!allowedOptions.contains(userChoice)) {

            System.out.println("Please select one of the given options\n\n");

            viewActiveAccountDetails();

        } else if (userChoice.equals("1") || userChoice.equals("first")) {

            editActiveAccountFirstName();

        } else if (userChoice.equals("2") || userChoice.equals("last")) {

            editActiveAccountLastName();

        } else if (userChoice.equals("3") || userChoice.equals("return")) {

            viewActiveAccountDetails();

        } else if (userChoice.equals("logout")) {

            this.site.clearCurrentUser();

            userLoginQuery();

        }
    }

    private void editActiveAccountLastName() {


        Guest user = this.site.getCurrentUser();


        System.out.println(String.format("Edit Last Name - %s\n\n", user.getName()));
        System.out.println(String.format("Last Name: %s", user.getLastName()));
        System.out.println("\n\n");
        System.out.println("'1' - 'Return' to Edit Account Details");
        System.out.println("\n\n'Logout' ");
        System.out.println("\n'Quit'\n--------\n\n");
        System.out.print("Please Enter New Last Name: ");


        String userChoice = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (userChoice.equals("1") || userChoice.equals("return")) {

            editActiveAccountDetails();

        } else if (userChoice.equals("logout")) {

            this.site.clearCurrentUser();

            userLoginQuery();

        } else if (!TerminalHelper.stringIsAlpha(userChoice)) {

            System.out.println("Please only use letters of the English alphabet");
            System.out.println("-----------------------------------------------\n\n");

            editActiveAccountLastName();

        } else {

            user.setLastName(userChoice);

            System.out.println(String.format("Last name successfully changed to '%s'\n\n", user.getLastName()));

            editActiveAccountDetails();

        }
    }

    private void editActiveAccountFirstName() {


        Guest user = this.site.getCurrentUser();


        System.out.println(String.format("Edit First Name - %s\n\n", user.getName()));
        System.out.println(String.format("First Name: %s", user.getFirstName()));
        System.out.println("\n\n");
        System.out.println("'1' - 'Return' to Edit Account Details");
        System.out.println("\n\n'Logout' ");
        System.out.println("\n'Quit'\n--------\n\n");
        System.out.print("Please Enter New First Name: ");


        String userChoice = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (userChoice.equals("1") || userChoice.equals("return")) {

            editActiveAccountDetails();

        } else if (userChoice.equals("logout")) {

            this.site.clearCurrentUser();

            userLoginQuery();

        } else if (!TerminalHelper.stringIsAlpha(userChoice)) {

            System.out.println("Please only use letters of the English alphabet");
            System.out.println("-----------------------------------------------\n\n");

            editActiveAccountFirstName();

        } else {

            user.setFirstName(userChoice);

            System.out.println(String.format("First name successfully changed to '%s'\n\n", user.getFirstName()));

            editActiveAccountDetails();

        }
    }

    private void getAcceptableUserName(ProtoGuest newAccount) {


        String firstName = newAccount.getFirstName();
        String lastName = newAccount.getLastName();
        Integer startingBalance = newAccount.getStartingBalance();


        if (!firstName.isEmpty()) {
            System.out.println(String.format("Chosen First Name: %s", firstName));
        }
        if (!lastName.isEmpty()) {
            System.out.println(String.format("Chosen Last Name: %s", lastName));
        } else if (!lastName.isEmpty() && startingBalance == 0) {
            System.out.println(String.format("Chosen Last Name: %s\n", lastName));
        }
        if (startingBalance != 0) {
            System.out.println(String.format("Chosen Starting Balance: %d\n", startingBalance));
        }


        System.out.print("Please enter your desired user name, or enter 'login' to cancel new account creation and return to login screen: ");


        String userChoice = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (userChoice.equals("login")) {

            userLoginQuery();

        } else if (!TerminalHelper.stringIsAlphaNumeric(userChoice)) {

            System.out.println("Apologies, but due to current system constraints please use only numbers and letters of the English alphabet\n\n");

            getAcceptableUserName(newAccount);

        } else if (site.checkIfHaveAccount(userChoice)) {

            System.out.println("The user name entered already exists on our system\n\n");

            getAcceptableUserName(newAccount);

        } else if (this.keywords.contains(userChoice) || userChoice.equals("new")) {

            System.out.println("You have chosen a reserved word, please enter another user name\n\n");

            getAcceptableUserName(newAccount);

        }

        newAccount.setUserName(userChoice);

        getAcceptableUserNameCheck(newAccount);

    }

    private void getAcceptableUserNameCheck(ProtoGuest newAccount) {


        String userName = newAccount.getUserName();

        FinalCheckUserCreationResult finalCheck = newAccount.getFinalCheck();


        System.out.print(String.format("You have entered '%s' as your user name.\nIs this correct? 'Yes' / 'No': ", userName));
        String response;
        response = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();

        if (response.equals("login")) {

            userLoginQuery();

        } else if (response.equals("yes") || response.equals("y")) {

            if (!finalCheck.equals(FinalCheckUserCreationResult.NOISSUE)) {

                makeFinalCheckEdit(newAccount);
            }

            getAcceptableFirstName(newAccount);

        } else if (response.equals("no") || response.equals("n")) {

            newAccount.setUserName("");

            getAcceptableUserName(newAccount);

        } else {

            System.out.println("Please enter 'Yes' or 'No', or enter 'login' to return to the login screen\n");

        }
    }

    private void getAcceptableFirstName(ProtoGuest newAccount) {


        String userName = newAccount.getUserName();
        String firstName;
        String lastName = newAccount.getLastName();
        Integer startingBalance = newAccount.getStartingBalance();


        if (!userName.isEmpty()) {
            System.out.println(String.format("Chosen User Name: %s", userName));
        }
        if (!lastName.isEmpty()) {
            System.out.println(String.format("Chosen Last Name: %s", lastName));
        }
        if (startingBalance != 0) {
            System.out.println(String.format("Chosen Starting Balance: %d", startingBalance));
        }


        System.out.print("\nPlease enter your first name, or enter 'login' to cancel new account creation and return to login screen: ");

        firstName = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (firstName.equals("login")) {

            userLoginQuery();

        } else if (!TerminalHelper.stringIsAlpha(firstName)) {

            System.out.println("Due to current system constraints please only use letters of the English alphabet.\n");
            System.out.println("This will be updated soon, our apologies for any inconvenience caused.\n\n");

            getAcceptableFirstName(newAccount);

        } else if (this.keywords.contains(firstName)) {

            System.out.println("You have chosen a reserved word, please try again\n\n");

            getAcceptableFirstName(newAccount);

        }

        newAccount.setFirstName(firstName);

        getAcceptableFirstNameCheck(newAccount);

    }

    private void getAcceptableFirstNameCheck(ProtoGuest newAccount) {


        String firstName = newAccount.getFirstName();

        FinalCheckUserCreationResult finalCheck = newAccount.getFinalCheck();


        System.out.print(String.format("You have entered '%s' as your first name.\nIs this correct? 'Yes' / 'No': ", firstName));

        String response = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (response.equals("login")) {

            userLoginQuery();

        } else if (response.equals("yes") || response.equals("y")) {

            if (!finalCheck.equals(FinalCheckUserCreationResult.NOISSUE)) {

                makeFinalCheckEdit(newAccount);
            }

            getAcceptableLastName(newAccount);

        } else if (response.equals("no") || response.equals("n")) {

            getAcceptableFirstName(newAccount);

        } else {

            System.out.println("Please enter 'Yes' or 'No', or enter 'login' to return to the login screen\n");

            getAcceptableFirstNameCheck(newAccount);

        }
    }

    private void getAcceptableLastName(ProtoGuest newAccount) {


        String userName = newAccount.getUserName();
        String firstName = newAccount.getFirstName();
        String lastName;
        Integer startingBalance = newAccount.getStartingBalance();


        if (!userName.isEmpty()) {
            System.out.println(String.format("Chosen User Name: %s", userName));
        }
        if (!firstName.isEmpty()) {
            System.out.println(String.format("Chosen First Name: %s", firstName));
        }
        if (startingBalance != 0) {
            System.out.println(String.format("Chosen Starting Balance: %d", startingBalance));
        }


        System.out.print("\nPlease enter your last name, or enter 'login' to return to login screen: ");

        lastName = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();

        if (lastName.equals("login")) {

            userLoginQuery();

        } else if (!TerminalHelper.stringIsAlpha(lastName)) {

            System.out.println("Due to current system constraints please only use letters of the English alphabet.\n");
            System.out.println("This will be updated soon, our apologies for any inconvenience caused.\n\n");

            getAcceptableLastName(newAccount);

        } else if (this.keywords.contains(lastName)) {

            System.out.println("You have chosen a reserved word, please try again\n\n");

            getAcceptableLastName(newAccount);

        }

        newAccount.setLastName(lastName);

        getAcceptableLastNameCheck(newAccount);

    }

    private void getAcceptableLastNameCheck(ProtoGuest newAccount) {


        String lastName = newAccount.getLastName();

        FinalCheckUserCreationResult finalCheck = newAccount.getFinalCheck();


        System.out.print(String.format("You have entered '%s' as your last name.\nIs this correct? 'Yes' / 'No': ", lastName));

        String response = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (response.equals("login")) {

            userLoginQuery();

        } else if (response.equals("yes") || response.equals("y")) {

            if (!finalCheck.equals(FinalCheckUserCreationResult.NOISSUE)) {

                makeFinalCheckEdit(newAccount);
            }

            getStartingBalance(newAccount);

        }

        if (response.equals("no") || response.equals("n")) {

            newAccount.setLastName("");

            getAcceptableLastName(newAccount);

        } else {

            System.out.println("Please enter 'Yes' or 'No', or enter 'login' to cancel new account creation and return to the login screen\n");

            getAcceptableLastNameCheck(newAccount);

        }
    }

    private void getStartingBalance(ProtoGuest newAccount) {


        String userName = newAccount.getUserName();
        String firstName = newAccount.getFirstName();
        String lastName = newAccount.getLastName();

        String startingBalanceAsString;
        Integer startingBalance;


        if (!userName.isEmpty()) {
            System.out.println(String.format("Chosen User Name: %s", userName));
        }
        if (!firstName.isEmpty()) {
            System.out.println(String.format("Chosen First Name: %s", firstName));
        }
        if (!lastName.isEmpty()) {
            System.out.println(String.format("Chosen Last Name: %s", lastName));
        }


        System.out.println("\nPlease enter a starting balance of full pounds for your account, using only numbers.\n");
        System.out.print("Or enter 'login' to cancel the new account creation and return to login screen: ");

        startingBalanceAsString = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (!TerminalHelper.stringIsNumeric(startingBalanceAsString)) {

            System.out.println("Please use only whole numbers\n\n");

            getStartingBalance(newAccount);

        } else if (startingBalanceAsString.equals("login")) {

            userLoginQuery();

        }

        startingBalance = Integer.valueOf(startingBalanceAsString);

        newAccount.setStartingBalance(startingBalance);

        getStartingBalanceCheck(newAccount);

    }

    private void getStartingBalanceCheck(ProtoGuest newAccount) {


        Integer startingBalance = newAccount.getStartingBalance();

        FinalCheckUserCreationResult finalCheck = newAccount.getFinalCheck();


        System.out.print(String.format("You have entered £%d as your starting balance.\nIs this correct? 'Yes' / 'No': ", startingBalance));

        String response = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (response.equals("login")) {

            userLoginQuery();

        } else if (response.equals("yes") || response.equals("y")) {

            if (!finalCheck.equals(FinalCheckUserCreationResult.NOISSUE)) {

                makeFinalCheckEdit(newAccount);
            }

            makeFinalCheck(newAccount);

        } else if (response.equals("no") || response.equals("n")) {

            newAccount.setStartingBalance(0);

            getStartingBalance(newAccount);

        } else {

            System.out.println("Please enter 'Yes' or 'No', or enter 'login' to cancel new account creation and return to the login screen\n");

            getStartingBalanceCheck(newAccount);

        }
    }

    private void makeFinalCheck(ProtoGuest newAccount) {


        String userName = newAccount.getUserName();
        String firstName = newAccount.getFirstName();
        String lastName = newAccount.getLastName();
        Integer startingBalance = newAccount.getStartingBalance();


        System.out.println(String.format("Chosen User Name: %s", userName));
        System.out.println(String.format("Chosen First Name: %s", firstName));
        System.out.println(String.format("Chosen Last Name: %s", lastName));
        System.out.println(String.format("Chosen Starting Balance: %d", startingBalance));


        System.out.println("\nAre all the above fields correct? 'Yes' / 'No', or 'login' to restart from login screen: ");

        String response = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();

        if (response.equals("yes") || response.equals("y")) {

            System.out.println(String.format("Payment received from bank account for starting Balance, account wallet credited: $%d.\n", startingBalance));

            System.out.println("Account Created\n");

            this.site.addUser(userName, firstName, lastName, startingBalance);

            Guest activeAccount = this.site.findUserAccount(userName);

            this.site.enterCurrentUser(activeAccount);

            mainMenu();

        } else if (response.equals("no") || response.equals("n")) {

            makeFinalCheckEdit(newAccount);

        } else if (response.contains("login")) {

            userLoginQuery();

        } else {

            System.out.println("Please enter only 'yes', 'no', or 'login'");

            makeFinalCheck(newAccount);

        }
    }

    private void makeFinalCheckEdit(ProtoGuest newAccount) {


        newAccount.setFinalCheck(FinalCheckUserCreationResult.NOISSUE);


        System.out.println("Please enter the field you wish to change first, or 'login' to restart\n");

        System.out.println("Enter 'finish' to return to the final step, or 'login' to cancel and return to the login screen\n");

        System.out.print("['User' name, 'First' name, 'Last' name, Starting 'Balance']: ");


        String changeRequired = TerminalHelper.getInput();

        TerminalHelper.flushMacScreen();


        if (changeRequired.equals("finish")) {

            makeFinalCheck(newAccount);

        } else if (changeRequired.equals("login")) {

            userLoginQuery();

        } else if (changeRequired.equals("user") || changeRequired.equals("user name")) {

            newAccount.setFinalCheck(FinalCheckUserCreationResult.USER);

            getAcceptableUserName(newAccount);

        } else if (changeRequired.equals("first") || changeRequired.equals("first name")) {

            newAccount.setFinalCheck(FinalCheckUserCreationResult.FIRST);

            getAcceptableFirstName(newAccount);

        } else if (changeRequired.equals("last") || changeRequired.equals("last name")) {

            newAccount.setFinalCheck(FinalCheckUserCreationResult.LAST);

            getAcceptableLastName(newAccount);

        } else if (changeRequired.equals("balance") || changeRequired.equals("starting balance")) {

            newAccount.setFinalCheck(FinalCheckUserCreationResult.BALANCE);

            getStartingBalance(newAccount);

        } else {

            System.out.println("Please enter only the given options\n");

            makeFinalCheckEdit(newAccount);

        }
    }


}