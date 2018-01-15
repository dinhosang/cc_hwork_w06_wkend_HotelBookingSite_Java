package WebSite;

import Location.Hotel.Hotel;
import Person.Guest;

import java.util.ArrayList;

public class Site {

    protected String siteName;
    protected Guest currentUser;
    protected ArrayList<Guest> userAccounts;
    protected ArrayList<Hotel> listAllHotels;

    public Site(String siteName){
        this.currentUser = null;
        this.siteName = siteName;
        this.userAccounts = new ArrayList<>();
        this.listAllHotels = new ArrayList<>();
    }

    public String getSiteName(){
        String copySiteName = this.siteName;
        return copySiteName;
    }

    public ArrayList<Guest> getUserAccounts() {
        ArrayList<Guest> copyUserAccounts = this.userAccounts;
        return copyUserAccounts;
    }

    public Guest findUserAccount(String userName){
        Guest soughtForAccount;
        for(Guest user: this.userAccounts){
            if(user.getUserName().equals(userName)){
                soughtForAccount = user;
                return soughtForAccount;
            }
        }
        return null;
    }

    public boolean checkIfHaveAccount(String userName){
        boolean isActiveUser = false;
        for(Guest user: this.userAccounts){
            if(user.getUserName().toLowerCase().equals(userName)){
                isActiveUser = true;
                return isActiveUser;
            }
        }
        return isActiveUser;
    }

    public void addUser(String userName, String firstName, String lastName, Integer startingBalance) {
        Guest newUser = new Guest(userName, firstName, lastName, startingBalance);
        this.userAccounts.add(newUser);
    }

    public void enterCurrentUser(Guest currentUser){
        this.currentUser = currentUser;
    }

    public void clearCurrentUser(){
        this.currentUser = null;
    }

    public Guest getCurrentUser(){
        Guest copyCurrentUser = this.currentUser;
        return copyCurrentUser;
    }
}
