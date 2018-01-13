package Location.HotelRooms.ReservedRooms;


public class Bedroom extends ReservedRoom {

    public Bedroom(String roomNumber, BedType bedType, int rate){
        super(roomNumber, bedType.getTypeCapacity(), rate);
    }

    public String getRoomNumber() {
        String copyRoomNumber = this.name;
        return copyRoomNumber;
    }

}
