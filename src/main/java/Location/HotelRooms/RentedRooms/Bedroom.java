package Location.HotelRooms.RentedRooms;


import Location.HotelRooms.RoomType;

public class Bedroom extends RentedRoom {

    public Bedroom(String roomNumber, BedType bedType, int rate){
        super(roomNumber, bedType.getTypeCapacity(), rate);
    }

    public String getRoomNumber() {
        String copyRoomNumber = this.name;
        return copyRoomNumber;
    }

}
