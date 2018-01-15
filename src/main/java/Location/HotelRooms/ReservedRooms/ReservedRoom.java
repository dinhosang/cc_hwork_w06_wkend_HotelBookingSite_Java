package Location.HotelRooms.ReservedRooms;

import Location.Location;
import Person.Guest;
import Reservation.RoomReservationDetails;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservedRoom extends Location {

    protected int rate;
    protected ArrayList<RoomReservationDetails> reservationDetails;

    public ReservedRoom(String name, int capacity, int rate){
        super(name, capacity);
        this.rate = rate;
        this.reservationDetails = new ArrayList<>();
    }


    public int getRate() {
        int copyRate = this.rate;
        return copyRate;
    }

    public void addReservationDetails(Guest organiser, LocalDate startDate, LocalDate endDate, ArrayList<String> guests){
        RoomReservationDetails details = new RoomReservationDetails(organiser, startDate, endDate, guests);
        this.reservationDetails.add(details);
    }

    public ArrayList<RoomReservationDetails> getReservationDetails() {
        ArrayList<RoomReservationDetails> copyReservationDetails = this.reservationDetails;
        return copyReservationDetails;
    }

    // cancel reservation should lead to removeReservationDetails
}
