package Location;

import Person.Guest;

import java.util.ArrayList;
import java.util.Arrays;

public class Room {

    protected int capacity;
    protected ArrayList<Guest> occupants;

    public Room(int capacity) {
        this.capacity = capacity;
        this.occupants = new ArrayList<>();
    }

    public ArrayList<Guest> getOccupants() {
        ArrayList<Guest> copyOccupants = this.occupants;
        return copyOccupants;
    }

    public void receiveGuests(Guest... guests) {
        this.occupants.addAll(Arrays.asList(guests));
    }

    public void releaseGuests(Guest... guests) {
        this.occupants.removeAll(Arrays.asList(guests));
    }

    public int getCapacity() {
        int copyCapacity = this.capacity;
        return copyCapacity;
    }
}
