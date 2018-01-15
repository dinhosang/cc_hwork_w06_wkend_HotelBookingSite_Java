package WebSite;

import Location.Hotel.Hotel;
import Location.HotelRooms.DiningRoom;
import Location.HotelRooms.ReservedRooms.BedType;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Location.HotelRooms.ReservedRooms.ConferenceRoom;
import Person.Guest;

import java.time.LocalDate;

public class TravelInCode extends Site {

    Hotel hotel1;
    Hotel hotel2;
    Hotel hotel3;

    Bedroom bedroom1;
    Bedroom bedroom2;
    Bedroom bedroom3;
    Bedroom bedroom4;
    Bedroom bedroom5;
    Bedroom bedroom6;
    Bedroom bedroom7;
    Bedroom bedroom8;

    ConferenceRoom conferenceRoom1;
    ConferenceRoom conferenceRoom2;
    ConferenceRoom conferenceRoom3;
    ConferenceRoom conferenceRoom4;

    DiningRoom diningRoom1;
    DiningRoom diningRoom2;
    DiningRoom diningRoom3;

    Guest user1;
    Guest user2;
    Guest user3;
    Guest user4;

    String guestName1;
    String guestName2;
    String guestName3;
    String guestName4;
    String guestName5;
    String guestName6;
    String guestName7;
    String guestName8;




    public TravelInCode() {
        super("Travel In Code");

        createHotels();
        createBedrooms();
        createConferenceRooms();
        createDiningRooms();
        createUsers();
        createGuestNames();

        enterRoomsInHotels();
        enterUsersIntoSiteUserAccountsArray();
        enterHotelsIntoSiteHotelsArray();

        createDefaultReservations();
    }


    private void createHotels(){
        this.hotel1 = new Hotel("The Cosmos", 5);
        this.hotel2 = new Hotel("Lovely Place", 10);
        this.hotel3 = new Hotel("Beach-side", 20);
    }

    private void createBedrooms() {
        this.bedroom1 = new Bedroom("1", BedType.DOUBLE, 10);
        this.bedroom2 = new Bedroom("2", BedType.SINGLE, 15);

        this.bedroom3 = new Bedroom("1", BedType.DOUBLE, 15);
        this.bedroom4 = new Bedroom("2", BedType.TRIPLE, 20);
        this.bedroom5 = new Bedroom("3", BedType.DOUBLE, 15);

        this.bedroom6 = new Bedroom("1", BedType.DOUBLE, 20);
        this.bedroom7 = new Bedroom("2", BedType.TRIPLE, 30);
        this.bedroom8 = new Bedroom("3", BedType.TRIPLE, 30);
    }

    private void createConferenceRooms() {
        this.conferenceRoom1 = new ConferenceRoom("Conference 1", 20, 30);
        this.conferenceRoom2 = new ConferenceRoom("Meeting Room", 20, 25);
        this.conferenceRoom3 = new ConferenceRoom("Small Conference", 30, 40);
        this.conferenceRoom4 = new ConferenceRoom("Large Conference", 50, 60);
    }

    private void createDiningRooms() {
        this.diningRoom1 = new DiningRoom("The Luncheon", 30);
        this.diningRoom2 = new DiningRoom("A Happy Meal", 20);
        this.diningRoom3 = new DiningRoom("Gourmet Food", 10);
    }

    private void createUsers() {
        this.user1 = new Guest("jen7", "Jenny", "Klinktock", 1900);
        this.user2 = new Guest("jazzy", "Jason", "Cullins", 1700);
        this.user3 = new Guest("winterk", "Kyra", "Winter", 1800);
        this.user4 = new Guest("toto", "Liara", "Buttons", 1200);
    }

    private void createGuestNames() {
        this.guestName1 = "Ribena Biscuit";
        this.guestName2 = "Janyra Wislow";
        this.guestName3 = "Cutter Jackley";
        this.guestName4 = "Keith Leslow";
        this.guestName5 = "Tolda Rida";
        this.guestName6 = "Jessy Tolsy";
        this.guestName7 = "Hannah Hana";
        this.guestName8 = "Postie Ecks";
    }

    private void enterRoomsInHotels() {
        this.hotel1.addRooms(bedroom1, bedroom2, conferenceRoom1, diningRoom1);
        this.hotel2.addRooms(bedroom3, bedroom4, bedroom5, conferenceRoom2, diningRoom2);
        this.hotel3.addRooms(bedroom6, bedroom7, bedroom8, conferenceRoom3, conferenceRoom4, diningRoom3);
    }

    private void enterUsersIntoSiteUserAccountsArray() {
        this.userAccounts.add(this.user1);
        this.userAccounts.add(this.user2);
        this.userAccounts.add(this.user3);
        this.userAccounts.add(this.user4);
    }

    private void enterHotelsIntoSiteHotelsArray() {
        this.listAllHotels.add(this.hotel1);
        this.listAllHotels.add(this.hotel2);
        this.listAllHotels.add(this.hotel3);
    }

    private void createDefaultReservations() {
        LocalDate currentDate = LocalDate.now();


        LocalDate past1Start = currentDate.minusMonths(3);
        LocalDate past1End = currentDate.minusMonths(2).minusDays(16);
        this.user1.requestReservation(this.hotel1, bedroom2, past1Start, past1End, user1.getName());

        LocalDate present1Start = currentDate.minusWeeks(1);
        LocalDate present1End = currentDate.plusDays(4);
        this.user1.requestReservation(this.hotel2, bedroom4, present1Start, present1End, user1.getName(), guestName1, guestName2);



        LocalDate past2Start = currentDate.minusYears(2).minusMonths(3).minusDays(13);
        LocalDate past2End = currentDate.minusYears(2).minusMonths(3).minusDays(4);
        String[] guests = {guestName1, guestName5, guestName7, guestName8, guestName4};
        this.user2.requestReservation(this.hotel2, conferenceRoom2, past2Start, past2End, guests);

        LocalDate present2Start = currentDate.minusDays(3);
        LocalDate present2End = currentDate.plusDays(3);
        this.user2.requestReservation(this.hotel3, bedroom7, present2Start, present2End, user2.getName(), guestName3);

        LocalDate future1Start = currentDate.plusMonths(3).plusDays(6);
        LocalDate future1End = currentDate.plusMonths(3).plusDays(16);
        this.user2.requestReservation(this.hotel3, bedroom7, future1Start, future1End,guestName3, guestName5);



        LocalDate past3Start = currentDate.minusMonths(7).minusDays(23);
        LocalDate past3End = currentDate.minusMonths(7).minusDays(13);
        this.user3.requestReservation(this.hotel1, bedroom1, past3Start, past3End,this.user3.getName(), guestName8);



        LocalDate future2Start = currentDate.plusDays(11);
        LocalDate future2End = currentDate.plusDays(16);
        this.user4.requestReservation(this.hotel1, bedroom1, future2Start, future2End, this.user4.getName(), guestName8);

        LocalDate future3Start = currentDate.plusYears(1).plusDays(23);
        LocalDate future3End = currentDate.plusYears(1).plusDays(24);
        this.user4.requestReservation(this.hotel1, conferenceRoom1, future3Start, future3End, this.user4.getName(), guestName8, guestName5, guestName3, guestName2, guestName1, guestName4);

    }

}
