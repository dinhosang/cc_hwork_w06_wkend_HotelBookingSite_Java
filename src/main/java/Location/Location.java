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
    }

    public ArrayList<Guest> getOccupants() {
        ArrayList<Guest> copyOccupants = this.occupants;
        return copyOccupants;
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
