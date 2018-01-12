package Person;

import Location.Room;

public class Guest {

    private String name;
    private int wallet;
    private Room location;

    public Guest(String name, int wallet){
        this.name = name;
        this.wallet = wallet;
    }

    public String getName() {
        String copyName = this.name;
        return copyName;
    }

    public int getWallet() {
        int copyWallet = this.wallet;
        return copyWallet;
    }

    public void spendMoney(int amountSpent) {
        this.wallet -= amountSpent;
    }
}
