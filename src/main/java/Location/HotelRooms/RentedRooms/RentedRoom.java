package Location.HotelRooms.RentedRooms;

import Location.Location;

public class RentedRoom extends Location {

    protected int rate;

    public RentedRoom(String name, int capacity, int rate){
        super(name, capacity);
        this.rate = rate;
    }


    public int getRate() {
        int copyRate = this.rate;
        return copyRate;
    }
}
