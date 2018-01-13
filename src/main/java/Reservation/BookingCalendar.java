package Reservation;

import Location.HotelRooms.ReservedRooms.ReservedRoom;
import Location.HotelRooms.RoomType;
import Location.Location;
import Person.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BookingCalendar {


    private HashMap<Integer, HashMap> calendar;

    public BookingCalendar(){
        calendar = new HashMap<>();
    }

    // need to look up raising of exceptions and catching them
    // so I can return different errors depending on reason
    // for instance if room class is not subclass of ReservedRoom
    // is a different problem to room capacity being too low for number of guests
    // would return exceptions instead of false
    public ReservationResult addBooking(ReservedRoom room, LocalDate startDate, LocalDate endDate, Guest... guests) {

        int startYear;
        int startDayOfYear;
        int endYear = endDate.getYear();
        int endDayOfYear = endDate.getDayOfYear();

        Boolean roomFreeForBooking = true;

        for(startYear = startDate.getYear(); startYear <= endYear; ++startYear){

            if(!calendar.containsKey(startYear)){
                HashMap<Integer, HashMap> yearHash = new HashMap<>();
                calendar.put(startYear, yearHash);
            }

            for(startDayOfYear = startDate.getDayOfYear(); startDayOfYear < endDayOfYear; ++startDayOfYear){

                HashMap<Integer, HashMap> chosenYearHash = calendar.get(startYear);
                if(!chosenYearHash.containsKey(startDayOfYear)){
                    HashMap<Integer, HashMap> dayOfYearHash = new HashMap<>();
                    chosenYearHash.put(startDayOfYear, dayOfYearHash);
                }

                String roomTypeEnumAsAString = room.getClass().getSimpleName();
                RoomType roomType = RoomType.valueOf(roomTypeEnumAsAString.toUpperCase());

                // below if errors at containsKey if trying to chain two .gets
                HashMap<RoomType, HashMap> chosenDayOfYearHash = chosenYearHash.get(startDayOfYear);
                if(!chosenDayOfYearHash.containsKey(roomType)){
                    HashMap<RoomType, ArrayList> roomTypeHash = new HashMap<>();
                    chosenDayOfYearHash.put(roomType, roomTypeHash);
                }

                HashMap chosenRoomTypeHash = chosenDayOfYearHash.get(roomType);
                if(chosenRoomTypeHash.containsKey(room)){
                    roomFreeForBooking = false;
                    break;
                }

                ArrayList<Guest> guestList = new ArrayList<>(Arrays.asList(guests));

                chosenRoomTypeHash.put(room, guestList);
            }

            if(roomFreeForBooking.equals(false)){
                //removeBooking(room, startDate, endDate);
                return ReservationResult.FAILEDALREADYRESERVED;
            }
        }

        return ReservationResult.SUCCESS;
    }




}
