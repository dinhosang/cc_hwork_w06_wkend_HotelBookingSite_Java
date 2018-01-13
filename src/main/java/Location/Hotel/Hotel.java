package Location.Hotel;

import Location.HotelRooms.DiningRoom;
import Location.HotelRooms.RentedRooms.Bedroom;
import Location.HotelRooms.RentedRooms.ConferenceRoom;
import Location.HotelRooms.RoomType;
import Location.Location;

import java.util.*;

public class Hotel extends Location {

    private HashMap<RoomType, ArrayList> rooms;

    public Hotel(String name, int foyerCapacity){
        super(name, foyerCapacity);
        createHashMapOfRooms();
    }


    public void addRooms(Location... rooms) {
        for (Location room: rooms){

            // could just place the RoomType as a property in the class
            // but wanted to see how the conversion would happen

            // could also have just used strings of class names for
            // HashMap keys instead of enums.

            String roomTypeEnumAsAString = room.getClass().getSimpleName();

            // if below is ArrayList<? extends Location>
            // the the .add command below cannot compile as we
            // cannot add object of Location.Location to such an ArrayList
            // even though class of room must be a subclass of Location
            // as the .getClass() returns the likes of Bedroom etc.

            ArrayList arrayRoomsForType = findCorrectArrayForRoomType(room);

            arrayRoomsForType.add(room);

            this.rooms.put(RoomType.valueOf(roomTypeEnumAsAString.toUpperCase()), arrayRoomsForType);
        }
    }

    public void removeRooms(Location... rooms){
        for(Location room: rooms){
            String roomTypeEnumAsAString = room.getClass().getSimpleName();

            ArrayList arrayRoomsForType = findCorrectArrayForRoomType(room);
            arrayRoomsForType.remove(room);

            this.rooms.put(RoomType.valueOf(roomTypeEnumAsAString.toUpperCase()), arrayRoomsForType);
        }
    }


    public int numberOfRooms() {
        int numberOfRooms = 0;

        Set keys = this.rooms.keySet();

        for(Object type: keys){
            numberOfRooms += this.rooms.get(type).size();
//            if(!this.rooms.get(type).isEmpty()) {
//                // if statement is need as otherwise it fails
//                // if the arrayList is empty when trying to get(0)
//                System.out.println(this.rooms.get(type).get(0).getClass());
//            }
        }

        return numberOfRooms;
    }

    public boolean containsRoom(Location room) {
        ArrayList arrayForRoomType = findCorrectArrayForRoomType(room);
        return arrayForRoomType.contains(room);
    }

    public int getCapacity(){
        int roomsCapacity = 0;
        Set keys = this.rooms.keySet();

        for(Object type: keys){
           ArrayList<? extends Location> arrayOfType = this.rooms.get(type);
           for (Location room: arrayOfType){
               roomsCapacity += room.getCapacity();
           }
        }
        return this.capacity + roomsCapacity;
    }



    private void createHashMapOfRooms(){
        ArrayList<Bedroom> bedrooms = new ArrayList<>();
        ArrayList<ConferenceRoom> conferenceRooms = new ArrayList<>();
        ArrayList<DiningRoom> diningRooms = new ArrayList<>();


        HashMap<RoomType, ArrayList> rooms = new HashMap<>();
        rooms.put(RoomType.BEDROOM, bedrooms);
        rooms.put(RoomType.DININGROOM, conferenceRooms);
        rooms.put(RoomType.CONFERENCEROOM, diningRooms);

        this.rooms = rooms;
    }

    private ArrayList<? extends Location> findCorrectArrayForRoomType(Location room){
        ArrayList<? extends Location> arrayForRoomType = new ArrayList<>();
        if (room instanceof Bedroom) {
            arrayForRoomType = this.rooms.get(RoomType.BEDROOM);

        }
        else if (room instanceof DiningRoom) {
            arrayForRoomType = this.rooms.get(RoomType.DININGROOM);
        } else if (room instanceof ConferenceRoom) {
            arrayForRoomType = this.rooms.get(RoomType.CONFERENCEROOM);
        }
        return arrayForRoomType;
    }
}
