package Person;

import Location.Room;

public class Guest {

    String name;
    int wallet;
    Room location;

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
}
