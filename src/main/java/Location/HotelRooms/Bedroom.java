package Location.HotelRooms;

import Location.Room;

public class Bedroom extends Room {

    private String roomNumber;
    private int rate;

    public Bedroom(String roomNumber, BedType bedType, int rate){
        super(bedType.getTypeCapacity());
        this.roomNumber = roomNumber;
        this.rate = rate;
//
    }

    public String getRoomNumber() {
        String copyRoomNumber = this.roomNumber;
        return copyRoomNumber;
    }

    public int getRate() {
        int copyRate = this.rate;
        return copyRate;
    }
}
