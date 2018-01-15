package Person;

import RunnerAssistance.FinalCheckUserCreationResult;

public class ProtoGuest {

    private String userName;
    private String firstName;
    private String lastName;
    private Integer startingBalance;
    private FinalCheckUserCreationResult finalCheck;

    public ProtoGuest(){
        this.userName = new String();
        this.firstName = new String();
        this.lastName = new String();
        this.startingBalance = 0;
        this.finalCheck = FinalCheckUserCreationResult.NOISSUE;
    }

    public void setUserName(String userName) {

        this.userName = userName;

    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;

    }

    public void setLastName(String lastName) {

        this.lastName = lastName;

    }

    public void setStartingBalance(int wallet) {

        this.startingBalance = wallet;

    }

    public String getUserName() {

        String copyUserName = this.userName;

        return copyUserName;

    }

    public String getFirstName() {

        String copyFirstName = this.firstName;

        return copyFirstName;
    }

    public String getLastName() {

        String copyLastName = this.lastName;

        return copyLastName;
    }

    public Integer getStartingBalance() {

        int copyWallet = this.startingBalance;

        return copyWallet;
    }

    public FinalCheckUserCreationResult getFinalCheck() {

        FinalCheckUserCreationResult copyFinalCheck = this.finalCheck;

        return copyFinalCheck;

    }

    public void setFinalCheck(FinalCheckUserCreationResult finalCheck) {

        this.finalCheck = finalCheck;

    }
}
