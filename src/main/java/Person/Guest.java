package Person;

import Location.Hotel.Hotel;
import Location.Location;
import Reservation.ReservationResult;

import java.time.LocalDate;

public class Guest {

    private String name;
    private int wallet;
    private Location location;

    public Guest(String name, int wallet){
        this.name = name;
        this.wallet = wallet;
    }

    public String getName() {
        String copyName = this.name;
        return copyName;
    }

    public int getWallet() {
        int copyWallet = this.wallet;
        return copyWallet;
    }

    public void spendMoney(long amountSpent) {
        this.wallet -= amountSpent;
    }

    public ReservationResult requestReservation(Hotel hotel, Location room, LocalDate startDate, LocalDate endDate, Guest... guests) {
        return hotel.receiveReservationRequest(room, startDate, endDate, this, guests);
    }
}
