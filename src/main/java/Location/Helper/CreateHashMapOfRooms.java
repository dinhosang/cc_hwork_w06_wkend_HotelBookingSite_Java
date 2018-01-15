package Location.Helper;

import Location.HotelRooms.DiningRoom;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Location.HotelRooms.ReservedRooms.ConferenceRoom;
import Location.HotelRooms.RoomType;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateHashMapOfRooms {

    // could do with taking arguments so that if new room types are added to a hotel
    // that it could then still create an arraylist for that

    public HashMap<RoomType, ArrayList> createRoomHashMap(){
        ArrayList<Bedroom> bedrooms = new ArrayList<>();
        ArrayList<ConferenceRoom> conferenceRooms = new ArrayList<>();
        ArrayList<DiningRoom> diningRooms = new ArrayList<>();


        HashMap<RoomType, ArrayList> rooms = new HashMap<>();
        rooms.put(RoomType.BEDROOM, bedrooms);
        rooms.put(RoomType.DININGROOM, conferenceRooms);
        rooms.put(RoomType.CONFERENCEROOM, diningRooms);

        return rooms;
    }
}
