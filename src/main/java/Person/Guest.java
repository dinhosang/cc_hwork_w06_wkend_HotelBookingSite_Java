package Person;

import Location.Hotel.Hotel;
import Location.HotelRooms.ReservedRooms.ReservedRoom;
import Location.Location;
import Reservation.Booking;
import Reservation.ReservationResult;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Guest {

    private String userName;
    private String firstName;
    private String lastName;
    private int wallet;
    private ArrayList<Booking> bookings;

    public Guest(String userName, String firstName, String lastName, int wallet){
        this.userName = userName;
        setFirstName(firstName);
        setLastName(lastName);
        this.wallet = wallet;
        this.bookings = new ArrayList<>();
    }


    public String getUserName(){
        String copyUserName = this.userName;
        return copyUserName;
    }

    public String getName() {
        String copyName = String.format("%s %s", this.firstName, this.lastName);
        return copyName;
    }

    public String getFirstName() {
        String copyFirstName = this.firstName;
        return copyFirstName;
    }

    public String getLastName() {
        String copyLastName = this.lastName;
        return copyLastName;
    }

    public void setFirstName(String firstName){
        int lengthFirstName = firstName.length();
        String firstNameCapitalised = firstName.substring(0, 1).toUpperCase() + firstName.substring(1, lengthFirstName);
        this.firstName = firstNameCapitalised;
    }

    public void setLastName(String lastName){
        int lengthLastName = lastName.length();
        String lastNameCapitalised = lastName.substring(0, 1).toUpperCase() + lastName.substring(1, lengthLastName);
        this.lastName = lastNameCapitalised;

    }

    public int getWallet() {
        int copyWallet = this.wallet;
        return copyWallet;
    }

    public void spendMoney(long amountSpent) {
        this.wallet -= amountSpent;
    }

    public void addMoney(long amountAdd){
        this.wallet += amountAdd;
    }

    public ReservationResult requestReservation(Hotel hotel, Location room, LocalDate startDate, LocalDate endDate, String... guests) {
        return hotel.receiveReservationRequest(room, startDate, endDate, this, guests);
    }

    public void addBooking(Hotel hotel, ReservedRoom room, long cost, LocalDate arrivalDate, LocalDate leaveDate, ArrayList<String> guestNames) {
        Booking newBooking = new Booking(hotel, room, cost, arrivalDate, leaveDate, guestNames);
        this.bookings.add(newBooking);
    }

    public ArrayList<Booking> getAllBookings(){
        ArrayList<Booking> copyBookings = this.bookings;
        return copyBookings;
    }

    public ArrayList<Booking> getHistoricBookings() {
        LocalDate timeNow = LocalDate.now();

        ArrayList<Booking> historicBookings = new ArrayList<>();
        for(Booking booking: this.bookings){
            LocalDate endDateOfBooking = booking.getLeaveDate();
            if(!timeNow.isAfter(endDateOfBooking)){
                continue;
            }
            historicBookings.add(booking);
        }
        return historicBookings;
    }

    public ArrayList<Booking> getCurrentAndFutureBookings(){
        LocalDate timeNow = LocalDate.now();

        ArrayList<Booking> currentAndFutureBookings = new ArrayList<>();
        for(Booking booking: this.bookings){
            LocalDate endDateOfBooking = booking.getLeaveDate();
            if(!timeNow.isBefore(endDateOfBooking)){
                continue;
            }
            currentAndFutureBookings.add(booking);
        }
        return currentAndFutureBookings;
    }
}
