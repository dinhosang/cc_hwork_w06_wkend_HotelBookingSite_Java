package Location;

import Person.Guest;

import java.util.ArrayList;
import java.util.Arrays;

public class Location {

    protected int capacity;
    protected String name;
    protected ArrayList<Guest> occupants;

    public Location(String name, int capacity) {
        this.name = name;
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

    public String getName() {
        String copyName = this.name;
        return copyName;
    }
}
