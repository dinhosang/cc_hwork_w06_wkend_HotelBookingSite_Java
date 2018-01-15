package Location.HotelRooms.ReservedRooms;

import Location.Location;

public class ReservedRoom extends Location {

    protected int rate;


    public ReservedRoom(String name, int capacity, int rate) {
        super(name, capacity);
        this.rate = rate;
    }


    public int getRate() {
        int copyRate = this.rate;
        return copyRate;
    }

}