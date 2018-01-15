package Reservation;

import Location.Hotel.Hotel;
import Location.HotelRooms.ReservedRooms.ReservedRoom;
import Location.HotelRooms.RoomType;
import Person.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Booking {

    private Hotel hotel;
    private ReservedRoom room;
    private ArrayList<String> guests;
    private LocalDate arrivalDate;
    private LocalDate leaveDate;
    private Guest organiser;
    private long cost;

    public Booking(Hotel hotel, ReservedRoom room, long cost, LocalDate arrivalDate, LocalDate leaveDate, Guest organiser, ArrayList<String> guests){
        this.hotel = hotel;
        this.room = room;
        this.arrivalDate = arrivalDate;
        this.leaveDate = leaveDate;
        this.organiser = organiser;
        this.guests = guests;
        this.cost = cost;
    }

    public Hotel getHotel() {
        Hotel copyHotel = this.hotel;
        return copyHotel;
    }

    public ReservedRoom getRoom() {
        ReservedRoom copyRoom = this.room;
        return copyRoom;
    }

    public LocalDate getArrivalDate() {
        LocalDate copyArrivalDate = this.arrivalDate;
        return copyArrivalDate;
    }

    public LocalDate getLeaveDate() {
        LocalDate copyLeaveDate = this.leaveDate;
        return copyLeaveDate;
    }

    public ArrayList<String> getGuests() {
        ArrayList<String> copyGuests = this.guests;
        return copyGuests;
    }

    public long getCost() {
        long copyCost = this.cost;
        return copyCost;
    }


}
