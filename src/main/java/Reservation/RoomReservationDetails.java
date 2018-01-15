package Reservation;

import Person.Guest;

import java.time.LocalDate;
import java.util.ArrayList;

public class RoomReservationDetails {

    private ArrayList<String> guests;
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest organiser;

    public RoomReservationDetails(Guest organiser, LocalDate startDate, LocalDate endDate, ArrayList<String> guests){
        this.organiser = organiser;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guests = guests;
    }

    public LocalDate getStartDate() {
        LocalDate copyStartDate = this.startDate;
        return copyStartDate;
    }

    public LocalDate getEndDate() {
        LocalDate copyEndDate = this.endDate;
        return copyEndDate;
    }

    public ArrayList<String> getGuests() {
        ArrayList<String> copyGuests = this.guests;
        return copyGuests;
    }

    public Guest getOrganiser() {
        Guest copyOrganiser = this.organiser;
        return copyOrganiser;
    }
}
