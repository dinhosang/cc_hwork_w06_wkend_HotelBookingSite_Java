package Reservation;

import Location.Hotel.Hotel;
import Location.HotelRooms.ReservedRooms.ReservedRoom;
import Person.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ReservationSystem {


    public static ReservationResult createBooking(Hotel hotel, ReservedRoom room, long cost, LocalDate startDate, LocalDate endDate, Guest organiser, String... guests) {

        if(!startDate.isBefore(endDate)){
            return ReservationResult.FAILEDENDDATEISBEFORESTARTDATE;
        }

        ArrayList<String> guestNames = new ArrayList<>(Arrays.asList(guests));

        ArrayList<Booking> bookingsForRoomInDateRange = hotel.findBookingsForRoomInDateRange(room, startDate, endDate);


        if (!bookingsForRoomInDateRange.isEmpty()){
            return ReservationResult.FAILEDALREADYRESERVED;
        }


        organiser.addBooking(hotel, room, cost, startDate, endDate, organiser, guestNames);
        hotel.addBooking(hotel, room, cost, startDate, endDate, organiser, guestNames);

        return ReservationResult.SUCCESS;
    }

}
