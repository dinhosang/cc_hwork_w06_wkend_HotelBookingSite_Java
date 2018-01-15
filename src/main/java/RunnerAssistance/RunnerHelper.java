package RunnerAssistance;

import Person.Guest;
import WebSite.Site;
import com.sun.xml.internal.xsom.impl.Ref;

import java.util.ArrayList;
import java.util.Arrays;

public class RunnerHelper {

    private Site site;
    private ArrayList<String> keywords;

    public RunnerHelper(Site site){
        this.site = site;
        this.keywords = new ArrayList<>(Arrays.asList("yes", "no", "y", "n", "user", "last", "first", "balance", "user name", "username", "first name", "last name", "starting balance", "login", "finish"));
    }

    public void userLoginQuery() {
        boolean needtoReturnToStart = true;
        LoginQueryResult loginResult;
        String userName = new String();

        String finalAccountName = new String();
        Guest activeAccount = null;

        System.out.println(String.format("Welcome to %s, the hotel booking site.\n", this.site.getSiteName()));
        System.out.println("This site uses '' around keywords to signify that they can be used as input.\n Please do not include the '' when typing.\n\n");
        System.out.println("At any time you may type 'quit' to leave the site\n\n");

        while(needtoReturnToStart) {
            loginResult = LoginQueryResult.NOTCOMPLETEDLOGIN;
            while (loginResult.equals(LoginQueryResult.NOTCOMPLETEDLOGIN)) {
                System.out.println("Please enter your username to continue, or type 'new' to create a new account");
                userName = TerminalHelper.getInput();
                finalAccountName = userName;
                TerminalHelper.flushMacScreen();

                if (TerminalHelper.stringIsEmpty(userName)) {
                    System.out.println("Please enter a user name, or type 'new' to create a new account\n\n");
                    continue;
                }
                if (userName.equals("new")) {
                    loginResult = LoginQueryResult.NEWUSER;
                    break;
                }

                if (userName.equals("login")){
                    System.out.println("Login is a reserved word, please try another user name\n\n");
                    continue;
                }

                if (!TerminalHelper.stringIsAlphaNumeric(userName)){
                    System.out.println("Please use only letters of the English alphabet, and whole numbers.\n\n");
                    continue;
                }

                if (this.keywords.contains(userName)){
                    System.out.println("You have chosen a reserved word, please enter another user name\n\n");
                    continue;
                }

                boolean isActiveUser = this.site.checkIfHaveAccount(userName);

                if (!isActiveUser) {
                    System.out.println("This user name has not been found");
                    System.out.println("Would you like to create a 'new' account, or try to 'login' again?");
                    while (true) {
                        String userChoice = TerminalHelper.getInput();
                        TerminalHelper.flushMacScreen();
                        if (userChoice.equals("new")) {
                            loginResult = LoginQueryResult.NEWUSER;
                            break;
                        }
                        if (userChoice.equals("login")) {
                            break;
                        }
                        System.out.println("Please enter 'new' to create a new account, or 'login' to try again");
                    }
                } else if (isActiveUser) {
                    loginResult = LoginQueryResult.ACTIVEUSER;
                }
            }

            if (loginResult.equals(LoginQueryResult.NEWUSER)) {
                Boolean succesfullyAdded = addUser(userName);
                if (succesfullyAdded == true) {
                    needtoReturnToStart = false;
                }
            }
            if (loginResult.equals(LoginQueryResult.ACTIVEUSER)) {
                activeAccount = this.site.findUserAccount(finalAccountName);
                needtoReturnToStart = false;
                this.site.enterCurrentUser(activeAccount);
            }
        }
        TerminalHelper.flushMacScreen();
    }



    public void mainMenu() {

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

        if (!allowedOptions.contains(userChoice)){
            TerminalHelper.flushMacScreen();
            System.out.println("Please select one of the given options\n\n");
            mainMenu();
        } else if (userChoice.equals("1") || userChoice.equals("account")){
            TerminalHelper.flushMacScreen();
            viewActiveAccountDetails();
        } else if (userChoice.equals("2") || userChoice.equals("booking")){
            TerminalHelper.flushMacScreen();
            viewActiveAccountBookings();
        } else if (userChoice.equals("3") || userChoice.equals("new")) {
            TerminalHelper.flushMacScreen();
//            makeNewBooking();
        } else if (userChoice.equals("logout")) {
            TerminalHelper.flushMacScreen();
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

        if (!allowedOptions.contains(userChoice)){
            TerminalHelper.flushMacScreen();
            System.out.println("Please select one of the given options\n\n");
            viewActiveAccountBookings();
        } else if (userChoice.equals("1") || userChoice.equals("active")){
            TerminalHelper.flushMacScreen();
//            viewActiveAccountActiveBookings();
        } else if (userChoice.equals("2") || userChoice.equals("historic")){
            TerminalHelper.flushMacScreen();
//            viewActiveAccountHistoricBookings();
        } else if (userChoice.equals("3") || userChoice.equals("return")) {
            TerminalHelper.flushMacScreen();
            mainMenu();
        } else if (userChoice.equals("logout")) {
            TerminalHelper.flushMacScreen();
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

        if (!allowedOptions.contains(userChoice)){
            TerminalHelper.flushMacScreen();
            System.out.println("Please select one of the given options\n\n");
            viewActiveAccountDetails();
        } else if (userChoice.equals("1") || userChoice.equals("edit")){
            TerminalHelper.flushMacScreen();
            editActiveAccountDetails();
        } else if (userChoice.equals("2") || userChoice.equals("transfer")){
            TerminalHelper.flushMacScreen();
            addFundsToActiveAccountWallet();
        } else if (userChoice.equals("3") || userChoice.equals("return")) {
            TerminalHelper.flushMacScreen();
            mainMenu();
        } else if (userChoice.equals("logout")) {
            TerminalHelper.flushMacScreen();
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

        if (userChoice.equals("1") || userChoice.equals("return")){
            TerminalHelper.flushMacScreen();
            viewActiveAccountDetails();
        } else if (userChoice.equals("logout")) {
            TerminalHelper.flushMacScreen();
            this.site.clearCurrentUser();
            userLoginQuery();
        }

        if (!TerminalHelper.stringIsNumeric(userChoice)){
            TerminalHelper.flushMacScreen();
            System.out.println("Please use only whole numbers");
            System.out.println("-----------------------------\n\n");
            addFundsToActiveAccountWallet();
        } else {
            Long userChoiceLong = Long.parseLong(userChoice);
            user.addMoney(userChoiceLong);
            TerminalHelper.flushMacScreen();
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

        if (!allowedOptions.contains(userChoice)){
            TerminalHelper.flushMacScreen();
            System.out.println("Please select one of the given options\n\n");
            viewActiveAccountDetails();
        } else if (userChoice.equals("1") || userChoice.equals("first")){
            TerminalHelper.flushMacScreen();
            editActiveAccountFirstName();
        } else if (userChoice.equals("2") || userChoice.equals("last")){
            TerminalHelper.flushMacScreen();
            editActiveAccountLastName();
        } else if (userChoice.equals("3") || userChoice.equals("return")) {
            TerminalHelper.flushMacScreen();
            viewActiveAccountDetails();
        } else if (userChoice.equals("logout")) {
            TerminalHelper.flushMacScreen();
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

        if (userChoice.equals("1") || userChoice.equals("return")){
            TerminalHelper.flushMacScreen();
            editActiveAccountDetails();
        } else if (userChoice.equals("logout")) {
            TerminalHelper.flushMacScreen();
            this.site.clearCurrentUser();
            userLoginQuery();
        }

        if (!TerminalHelper.stringIsAlpha(userChoice)){
            TerminalHelper.flushMacScreen();
            System.out.println("Please only use letters of the English alphabet");
            System.out.println("-----------------------------------------------\n\n");
            editActiveAccountLastName();
        } else {
            user.setLastName(userChoice);
            TerminalHelper.flushMacScreen();
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

        if (userChoice.equals("1") || userChoice.equals("return")){
            TerminalHelper.flushMacScreen();
            editActiveAccountDetails();
        } else if (userChoice.equals("logout")) {
            TerminalHelper.flushMacScreen();
            this.site.clearCurrentUser();
            userLoginQuery();
        }

        if (!TerminalHelper.stringIsAlpha(userChoice)){
            TerminalHelper.flushMacScreen();
            System.out.println("Please only use letters of the English alphabet");
            System.out.println("-----------------------------------------------\n\n");
            editActiveAccountFirstName();
        } else {
            user.setFirstName(userChoice);
            TerminalHelper.flushMacScreen();
            System.out.println(String.format("First name successfully changed to '%s'\n\n", user.getFirstName()));
            editActiveAccountDetails();
        }
    }


    private Boolean addUser(String attemptedUserName) {
        String userName = attemptedUserName;
        String firstName = new String();
        String lastName = new String();
        Integer startingBalance = 0;
        Boolean finishedProcess = false;
        Boolean returnToLogin = false;

        while(!finishedProcess) {

            userName = getAcceptableUserName(firstName, lastName, startingBalance);
            if(userName.isEmpty()){
                returnToLogin = true;
                finishedProcess = true;
                break;
            }

            firstName = getAcceptableFirstName(userName, lastName, startingBalance);
            if(firstName.isEmpty()){
                returnToLogin = true;
                finishedProcess = true;
                break;
            }

            lastName = getAcceptableLastName(userName, firstName, startingBalance);
            if(lastName.isEmpty()){
                returnToLogin = true;
                finishedProcess = true;
                break;
            }

            startingBalance = getStartingBalance(userName, firstName, lastName);
            if(startingBalance == null){
                returnToLogin = true;
                finishedProcess = true;
                break;
            }

            FinalCheckUserCreationResult resultOfFinalCheck = makeFinalCheck(userName, firstName, lastName, startingBalance);
            if(resultOfFinalCheck.equals(FinalCheckUserCreationResult.RESTART)) {
                returnToLogin = true;
            }
            finishedProcess = true;
        }

        if(returnToLogin){
            return false;
        }
        return true;
    }

    private FinalCheckUserCreationResult makeFinalCheck(String userName, String firstName, String lastName, Integer startingBalance) {

        boolean finalCheckOngoing = true;

        while(finalCheckOngoing) {
            System.out.println(String.format("Chosen User Name: %s", userName));
            System.out.println(String.format("Chosen First Name: %s", firstName));
            System.out.println(String.format("Chosen Last Name: %s", lastName));
            System.out.println(String.format("Chosen Starting Balance: %d", startingBalance));

            System.out.println("\nAre all the above fields correct? 'Yes' / 'No', or 'login' to restart from login screen: ");
            String response = TerminalHelper.getInput();
            TerminalHelper.flushMacScreen();

            if(response.equals("yes") || response.equals("y")){
                finalCheckOngoing = false;
                break;
            } else if(response.equals("no") || response.equals("n")){
                boolean furtherChangesRequired = true;
                while (furtherChangesRequired) {
                    boolean changeMade = false;
                    System.out.println("Please enter the field you wish to change first, or 'login' to restart");
                    System.out.println("Enter 'finish' to return to the final step, or 'login' to cancel and return to the login screen");
                    System.out.print("['User' name, 'First' name, 'Last' name, Starting 'Balance']: ");
                    String changeRequired = TerminalHelper.getInput();
                    TerminalHelper.flushMacScreen();

                    if(changeRequired.equals("finish")){
                        System.out.println(String.format("Payment received from bank account for starting Balance, account wallet credited: $%d.\n", startingBalance));
                        furtherChangesRequired = false;
                        break;
                    }

                    if(changeRequired.equals("login")){
                        return FinalCheckUserCreationResult.RESTART;
                    }

                    if(changeRequired.equals("user") || changeRequired.equals("user name")){
                        String resultQuery = getAcceptableUserName(firstName, lastName, startingBalance);
                        if (resultQuery == null) {
                            return FinalCheckUserCreationResult.RESTART;
                        }
                        userName = resultQuery;
                        changeMade = true;
                    }

                    if(changeRequired.equals("first") || changeRequired.equals("first name")){
                        String resultQuery = getAcceptableFirstName(userName, lastName, startingBalance);
                        if (resultQuery == null) {
                            return FinalCheckUserCreationResult.RESTART;
                        }
                        firstName = resultQuery;
                        changeMade = true;
                    }

                    if(changeRequired.equals("last") || changeRequired.equals("last name")){
                        String resultQuery = getAcceptableLastName(userName, firstName, startingBalance);
                        if (resultQuery == null) {
                            return FinalCheckUserCreationResult.RESTART;
                        }
                        lastName = resultQuery;
                        changeMade = true;
                    }

                    if(changeRequired.equals("balance") || changeRequired.equals("starting balance")){
                        Integer resultQuery = getStartingBalance(userName, firstName, lastName);
                        if (resultQuery == null) {
                            return FinalCheckUserCreationResult.RESTART;
                        }
                        startingBalance = resultQuery;
                        changeMade = true;
                    }


                    if(furtherChangesRequired && !changeMade){
                        System.out.println("Please enter only the given options");
                    }
                }
            } else if(response.contains("login")){
                return FinalCheckUserCreationResult.RESTART;
            } else {
                System.out.println("Please enter only 'yes', 'no', or 'login'");
                break;
            }
        }

        this.site.addUser(userName, firstName, lastName, startingBalance);
        Guest activeAccount = this.site.findUserAccount(userName);
        this.site.enterCurrentUser(activeAccount);

        return FinalCheckUserCreationResult.COMPLETED;
    }

    private String getAcceptableUserName(String firstName, String lastName, Integer startingBalance){
        String userName = new String();
        boolean acceptableUserName = false;

        while(!acceptableUserName){
            if(!firstName.isEmpty()) {
                System.out.println(String.format("Chosen First Name: %s", firstName));
            }
            if(!lastName.isEmpty()){
                System.out.println(String.format("Chosen Last Name: %s", lastName));
            } else if (!lastName.isEmpty() && startingBalance == 0){
                System.out.println(String.format("Chosen Last Name: %s\n", lastName));
            }
            if(startingBalance != 0){
                System.out.println(String.format("Chosen Starting Balance: %d\n", startingBalance));
            }

            System.out.print("Please enter your desired user name, or enter 'login' to cancel new account creation and return to login screen: ");
            userName = TerminalHelper.getInput();
            TerminalHelper.flushMacScreen();

            if(userName.equals("login")){
                return "";
            } else if(!TerminalHelper.stringIsAlphaNumeric(userName)){
                System.out.println("Apologies, but due to current system constraints please use only numbers and letters of the English alphabet\n\n");
                continue;
            } else if(site.checkIfHaveAccount(userName)){
                System.out.println("The user name entered already exists on our system\n\n");
                continue;
            } else if (this.keywords.contains(userName)){
                System.out.println("You have chosen a reserved word, please enter another user name\n\n");
                continue;
            } else {
                boolean finishedCheckingUserName = false;
                while (!finishedCheckingUserName) {
                    System.out.print(String.format("You have entered '%s' as your user name.\nIs this correct? 'Yes' / 'No': ", userName));
                    String response;
                    response = TerminalHelper.getInput();
                    TerminalHelper.flushMacScreen();

                    if (response.equals("login")) {
                        return "";
                    } else if (response.equals("yes") || response.equals("y")) {
                        finishedCheckingUserName = true;
                        acceptableUserName = true;
                    } else if (response.equals("no") || response.equals("n")) {
                        finishedCheckingUserName = true;
                        acceptableUserName = false;
                    } else {
                        System.out.println("Please enter 'Yes' or 'No', or enter 'login' to return to the login screen\n");
                        continue;
                    }
                }
            }
        }
        return userName;
    }

    private String getAcceptableFirstName(String userName, String lastName, Integer startingBalance){
        String firstName = new String();
        boolean acceptableFirstName = false;

        while(!acceptableFirstName){
            if(!userName.isEmpty()) {
                System.out.println(String.format("Chosen User Name: %s", userName));
            }
            if(!lastName.isEmpty()){
                System.out.println(String.format("Chosen Last Name: %s", lastName));
            }
            if(startingBalance != 0){
                System.out.println(String.format("Chosen Starting Balance: %d", startingBalance));
            }

            System.out.print("\nPlease enter your first name, or enter 'login' to cancel new account creation and return to login screen: ");
            firstName = TerminalHelper.getInput();
            TerminalHelper.flushMacScreen();

            if(firstName.equals("login")){
                return "";
            }

            if(!TerminalHelper.stringIsAlpha(firstName)){
                System.out.println("Due to current system constraints please only use letters of the English alphabet.");
                System.out.println("This will be updated soon, our apologies for any inconvenience caused.\n\n");
                continue;
            } else if (this.keywords.contains(firstName)){
                System.out.println("You have chosen a reserved word, please try again\n\n");
                continue;
            }



            boolean finishedCheckingFirstName = false;
            while(!finishedCheckingFirstName) {
                System.out.print(String.format("You have entered '%s' as your first name.\nIs this correct? 'Yes' / 'No': ", firstName));
                String response = TerminalHelper.getInput();
                TerminalHelper.flushMacScreen();


                if (response.equals("login")) {
                    return "";
                }

                if(response.equals("yes") || response.equals("y")){
                    finishedCheckingFirstName = true;
                    acceptableFirstName = true;
                }

                if(response.equals("no") || response.equals("n")){
                    finishedCheckingFirstName = true;
                    acceptableFirstName = false;
                }

                if(!finishedCheckingFirstName){
                    System.out.println("Please enter 'Yes' or 'No', or enter 'login' to return to the login screen\n");
                    continue;
                }
            }
        }
        return firstName;
    }

    private String getAcceptableLastName(String userName, String firstName, Integer startingBalance) {
        String lastName = "";
        boolean acceptableLastName = false;

        while(!acceptableLastName){
            if(!userName.isEmpty()) {
                System.out.println(String.format("Chosen User Name: %s", userName));
            }
            if(!firstName.isEmpty()){
                System.out.println(String.format("Chosen First Name: %s", firstName));
            }
            if(startingBalance != 0){
                System.out.println(String.format("Chosen Starting Balance: %d", startingBalance));
            }

            System.out.print("\nPlease enter your last name, or enter 'login' to return to login screen: ");
            lastName = TerminalHelper.getInput();
            TerminalHelper.flushMacScreen();

            if(lastName.equals("login")){
                return "";
            }

            if(!TerminalHelper.stringIsAlpha(lastName)){
                System.out.println("Due to current system constraints please only use letters of the English alphabet.");
                System.out.println("This will be updated soon, our apologies for any inconvenience caused.\n\n");
                continue;
            } else if (this.keywords.contains(lastName)){
                System.out.println("You have chosen a reserved word, please try again\n\n");
                continue;
            }


            boolean finishedCheckingLastName = false;
            while(!finishedCheckingLastName) {
                System.out.print(String.format("You have entered '%s' as your last name.\nIs this correct? 'Yes' / 'No': ", lastName));
                String response = TerminalHelper.getInput();
                TerminalHelper.flushMacScreen();


                if (response.equals("login")) {
                    return "";
                }

                if(response.equals("yes") || response.equals("y")){
                    finishedCheckingLastName = true;
                    acceptableLastName = true;
                }

                if(response.equals("no") || response.equals("n")){
                    finishedCheckingLastName = true;
                    acceptableLastName = false;
                }

                if(!finishedCheckingLastName){
                    System.out.println("Please enter 'Yes' or 'No', or enter 'login' to cancel new account creation and return to the login screen\n");
                    continue;
                }
            }
        }
        return lastName;
    }

    private Integer getStartingBalance(String userName, String firstName, String lastName) {
        Integer startingBalance = 0;
        String startingBalanceAsString = "";
        boolean acceptableStartingBalance = false;

        while(!acceptableStartingBalance) {
            if (!userName.isEmpty()) {
                System.out.println(String.format("Chosen User Name: %s", userName));
            }
            if (!firstName.isEmpty()) {
                System.out.println(String.format("Chosen First Name: %s", firstName));
            }
            if (!lastName.isEmpty()) {
                System.out.println(String.format("Chosen Last Name: %s", lastName));
            }
            if (startingBalance != 0) {
                System.out.println(String.format("Chosen Starting Balance: %d", startingBalance));
            }

            System.out.print("\nPlease enter a starting balance of full pounds for your account, using only numbers.\n\nOr enter 'login' to cancel the new account creating and return to login screen: ");
            startingBalanceAsString = TerminalHelper.getInput();
            TerminalHelper.flushMacScreen();
            Integer testAmount;

            if(TerminalHelper.stringIsNumeric(startingBalanceAsString)){
                testAmount = Integer.parseInt(startingBalanceAsString);
            } else {
                System.out.println("Please use only whole numbers\n\n");
                continue;
            }

            if (startingBalanceAsString.equals("login")) {
                return null;
            } else {
                boolean finishedCheckingStartingBalance = false;
                while (!finishedCheckingStartingBalance) {
                    System.out.print(String.format("You have entered £%s as your starting balance.\nIs this correct? 'Yes' / 'No': ", startingBalanceAsString));
                    String response = TerminalHelper.getInput();
                    TerminalHelper.flushMacScreen();


                    if (response.equals("login")) {
                        return null;
                    }

                    if (response.equals("yes") || response.equals("y")) {
                        finishedCheckingStartingBalance = true;
                        acceptableStartingBalance = true;
                    }

                    if (response.equals("no") || response.equals("n")) {
                        finishedCheckingStartingBalance = true;
                        acceptableStartingBalance = false;
                    }

                    if (!finishedCheckingStartingBalance) {
                        System.out.println("Please enter 'Yes' or 'No', or enter 'login' to cancel new account creation and return to the login screen\n");
                        continue;
                    }
                }
            }
        }
        startingBalance = Integer.parseInt(startingBalanceAsString);
        return startingBalance;
    }

}
