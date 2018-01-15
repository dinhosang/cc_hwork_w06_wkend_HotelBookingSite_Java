package Reservation;

import Location.Helper.CreateHashMapOfRooms;
import Location.Hotel.Hotel;
import Location.HotelRooms.DiningRoom;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Location.HotelRooms.ReservedRooms.ConferenceRoom;
import Location.HotelRooms.ReservedRooms.ReservedRoom;
import Location.HotelRooms.RoomType;
import Person.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class BookingCalendar {


    private HashMap<Integer, HashMap> calendar;

    public BookingCalendar(){
        calendar = new HashMap<>();
    }


    public ReservationResult addReservation(Hotel hotel, ReservedRoom room, long cost, LocalDate startDate, LocalDate endDate, Guest organiser, String... guests) {

        if(!startDate.isBefore(endDate)){
            return ReservationResult.FAILEDENDDATEISBEFORESTARTDATE;
        }

        int currentYear;
        int currentDayOfYear;
        int endYear = endDate.getYear();
        int endDayOfEndYear = endDate.getDayOfYear();
        int endDayOfCurrentYear;

        ArrayList<String> guestNames = new ArrayList<>(Arrays.asList(guests));

        Boolean roomFreeForBooking = true;
        Boolean haveCreatedAtLeastOneEntry = false;


        for(currentYear = startDate.getYear(); currentYear <= endYear; ++currentYear){

            if(currentYear < endYear){
                endDayOfCurrentYear = startDate.lengthOfYear() + 1;
            } else {
                endDayOfCurrentYear = endDayOfEndYear;
            }


            if(!calendar.containsKey(currentYear)){
                HashMap<Integer, HashMap> yearHash = new HashMap<>();
                calendar.put(currentYear, yearHash);
            }

            for(currentDayOfYear = startDate.getDayOfYear(); currentDayOfYear < endDayOfCurrentYear; ++currentDayOfYear){

                HashMap<Integer, HashMap> chosenYearHash = calendar.get(currentYear);

                if(!chosenYearHash.containsKey(currentDayOfYear)){
                    HashMap<Integer, HashMap> dayOfYearHash = new HashMap<>();
                    chosenYearHash.put(currentDayOfYear, dayOfYearHash);
                }

                String roomTypeEnumAsAString = room.getClass().getSimpleName();
                RoomType roomType = RoomType.valueOf(roomTypeEnumAsAString.toUpperCase());

                // below if errors at containsKey if trying to chain two .gets
                // and then do a third method (like .containsKey) on it
                // the second get does return a HashMap
                // but Java does not allow us to treat it as such
                // unless we cast it as one before applying the hashmap
                // method to it
                // or we can go the other route of putting the result
                // of the first two gets into a variable to then manipulate
                // strange thing is that running .getClass() after the second
                // .get return a HashMap type, even without casting!
                // but java won't let us treat it as such.

                HashMap<RoomType, HashMap> chosenDayOfYearHash = chosenYearHash.get(currentDayOfYear);
                if(!chosenDayOfYearHash.containsKey(roomType)){
                    HashMap<RoomType, HashMap> roomTypeHash = new HashMap<>();
                    chosenDayOfYearHash.put(roomType, roomTypeHash);
                }


                HashMap chosenRoomTypeHash = chosenDayOfYearHash.get(roomType);
                if(chosenRoomTypeHash.containsKey(room)){
                    roomFreeForBooking = false;
                    break;
                }


                HashMap<Guest, ArrayList> bookingDetails = new HashMap<>();
                bookingDetails.put(organiser, guestNames);

                chosenRoomTypeHash.put(room, bookingDetails);

                if(!haveCreatedAtLeastOneEntry) {
                    haveCreatedAtLeastOneEntry = true;
                }
            }

            if(!roomFreeForBooking) {
                if(haveCreatedAtLeastOneEntry) {
                    int yearToRemoveUpTo = currentYear;
                    int dayOfYearToRemoveUpTo = currentDayOfYear;
                    removeBooking(room, startDate, yearToRemoveUpTo, dayOfYearToRemoveUpTo);
                }
                return ReservationResult.FAILEDALREADYRESERVED;
            }
        }

        organiser.addBooking(hotel, room, cost, startDate, endDate, guestNames);
        room.addReservationDetails(organiser, startDate, endDate, guestNames);

        return ReservationResult.SUCCESS;
    }


    public HashMap<RoomType, ArrayList> findBookedRoomsDateRange(LocalDate startDate, LocalDate endDate){

        CreateHashMapOfRooms hashMapHelper = new CreateHashMapOfRooms();
        HashMap<RoomType, ArrayList> rooms = hashMapHelper.createRoomHashMap();
        rooms.remove(RoomType.DININGROOM);

        if(!startDate.equals(endDate)){
            if(!startDate.isBefore(endDate)){
                return null;
            }
        }

        int currentYear;
        int currentDayOfYear;
        int endYear = endDate.getYear();
        int endDayOfEndYear = endDate.getDayOfYear();
        int endDayOfCurrentYear;


        for(currentYear = startDate.getYear(); currentYear <= endYear; ++currentYear){

            if(currentYear < endYear){
                endDayOfCurrentYear = startDate.lengthOfYear() + 1;
            } else if(startDate.getDayOfYear() == endDayOfEndYear){
                endDayOfCurrentYear = endDayOfEndYear +1;
            } else {
                endDayOfCurrentYear = endDayOfEndYear;
            }


            if(!calendar.containsKey(currentYear)){
                continue;
            }

            for(currentDayOfYear = startDate.getDayOfYear(); currentDayOfYear < endDayOfCurrentYear; ++currentDayOfYear){

                HashMap<Integer, HashMap> chosenYearHash = calendar.get(currentYear);

                if(!chosenYearHash.containsKey(currentDayOfYear)){
                    continue;
                }

                HashMap<RoomType, HashMap> chosenDayOfYearHash = chosenYearHash.get(currentDayOfYear);

                Set keys = chosenDayOfYearHash.keySet();

                for(Object key: keys){
                    HashMap chosenRoomTypeHash = chosenDayOfYearHash.get(key);
                    Set roomsAsKeys = chosenRoomTypeHash.keySet();
                    for(Object roomAsKey: roomsAsKeys){
                        if(roomAsKey instanceof Bedroom){
                            ArrayList<Bedroom> bedrooms = rooms.get(RoomType.BEDROOM);
                            Bedroom foundRoom = (Bedroom) roomAsKey;
                            bedrooms.add(foundRoom);
                            rooms.put(RoomType.BEDROOM, bedrooms);
                        } else if(roomAsKey instanceof ConferenceRoom){
                            ArrayList<ConferenceRoom> conferenceRooms = rooms.get(RoomType.CONFERENCEROOM);
                            ConferenceRoom foundRoom = (ConferenceRoom) roomAsKey;
                            conferenceRooms.add(foundRoom);
                            rooms.put(RoomType.CONFERENCEROOM, conferenceRooms);
                        }
                    }
                }
            }
        }
        return rooms;
    }


    private void removeBooking(ReservedRoom room, LocalDate startDate, int yearToRemoveUpTo, int dayOfYearToRemoveUpTo) {

        int currentYear;
        int currentDayOfYear;
        int endDayOfYear;

        String roomTypeEnumAsAString = room.getClass().getSimpleName();
        RoomType roomType = RoomType.valueOf(roomTypeEnumAsAString.toUpperCase());

        for(currentYear = startDate.getYear(); currentYear <= yearToRemoveUpTo; ++currentYear){

            if(currentYear < yearToRemoveUpTo) {
                endDayOfYear = startDate.lengthOfYear() + 1;
            } else {
                endDayOfYear = dayOfYearToRemoveUpTo;
            }

            for(currentDayOfYear = startDate.getDayOfYear(); currentDayOfYear < endDayOfYear; ++currentDayOfYear){

                HashMap<Integer, HashMap> chosenYearHash = calendar.get(currentYear);
                HashMap<Integer, HashMap> chosenDayOfYearHash = chosenYearHash.get(currentDayOfYear);
                HashMap<RoomType, HashMap> chosenRoomTypeHash = chosenDayOfYearHash.get(roomType);
                chosenRoomTypeHash.remove(room);
            }

        }
    }


}
