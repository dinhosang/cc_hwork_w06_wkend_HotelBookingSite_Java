package Location.HotelRooms.RentedRooms;


public class Bedroom extends RentedRoom {


    public Bedroom(String roomNumber, BedType bedType, int rate){
        super(roomNumber, bedType.getTypeCapacity(), rate);
//
    }

    public String getRoomNumber() {
        String copyRoomNumber = this.name;
        return copyRoomNumber;
    }

}
