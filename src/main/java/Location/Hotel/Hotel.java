package Location.Hotel;

import Location.Helper.CreateHashMapOfRooms;
import Location.HotelRooms.DiningRoom;
import Location.HotelRooms.ReservedRooms.Bedroom;
import Location.HotelRooms.ReservedRooms.ConferenceRoom;
import Location.HotelRooms.ReservedRooms.ReservedRoom;
import Location.HotelRooms.RoomType;
import Location.Location;
import Person.Guest;
import Reservation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Hotel extends Location {

    private HashMap<RoomType, ArrayList> rooms;
    private ArrayList<Booking> bookings;

    public Hotel(String name, int foyerCapacity){
        super(name, foyerCapacity);
        createHashMapOfRooms();
        this.bookings = new ArrayList<>();
    }


    public void addRooms(Location... rooms) {
        for (Location room: rooms){

            // could just place the RoomType as a property in the class
            // but wanted to see how the conversion would happen

            // could also have just used strings of class names for
            // HashMap keys instead of enums.

            String roomTypeEnumAsAString = room.getClass().getSimpleName();

            // if below is ArrayList<? extends Location>
            // the the .add command below cannot compile as we
            // cannot add object of Location.Location to such an ArrayList
            // even though class of room must be a subclass of Location
            // as the .getClass() returns the likes of Bedroom etc.

            if(room instanceof Bedroom){
                ArrayList<Bedroom> arrayRoomsForType = (ArrayList<Bedroom>) findCorrectArrayForRoomType(room);
                Bedroom currentRoom = (Bedroom) room;
                arrayRoomsForType.add(currentRoom);
                this.rooms.put(RoomType.valueOf(roomTypeEnumAsAString.toUpperCase()), arrayRoomsForType);
            } else if (room instanceof ConferenceRoom) {
                ArrayList<ConferenceRoom> arrayRoomsForType = (ArrayList<ConferenceRoom>) findCorrectArrayForRoomType(room);
                ConferenceRoom currentRoom = (ConferenceRoom) room;
                arrayRoomsForType.add(currentRoom);
                this.rooms.put(RoomType.valueOf(roomTypeEnumAsAString.toUpperCase()), arrayRoomsForType);
            } else {
                ArrayList<DiningRoom> arrayRoomsForType = (ArrayList<DiningRoom>) findCorrectArrayForRoomType(room);
                DiningRoom currentRoom = (DiningRoom) room;
                arrayRoomsForType.add(currentRoom);
                this.rooms.put(RoomType.valueOf(roomTypeEnumAsAString.toUpperCase()), arrayRoomsForType);
            }

        }
    }

    public void removeRooms(Location... rooms){
        for(Location room: rooms){
            String roomTypeEnumAsAString = room.getClass().getSimpleName();

            if(room instanceof Bedroom){
                ArrayList<Bedroom> arrayRoomsForType = (ArrayList<Bedroom>) findCorrectArrayForRoomType(room);
                Bedroom currentRoom = (Bedroom) room;
                arrayRoomsForType.remove(currentRoom);
                this.rooms.put(RoomType.valueOf(roomTypeEnumAsAString.toUpperCase()), arrayRoomsForType);
            } else if (room instanceof ConferenceRoom) {
                ArrayList<ConferenceRoom> arrayRoomsForType = (ArrayList<ConferenceRoom>) findCorrectArrayForRoomType(room);
                ConferenceRoom currentRoom = (ConferenceRoom) room;
                arrayRoomsForType.remove(currentRoom);
                this.rooms.put(RoomType.valueOf(roomTypeEnumAsAString.toUpperCase()), arrayRoomsForType);
            } else {
                ArrayList<DiningRoom> arrayRoomsForType = (ArrayList<DiningRoom>) findCorrectArrayForRoomType(room);
                DiningRoom currentRoom = (DiningRoom) room;
                arrayRoomsForType.remove(currentRoom);
                this.rooms.put(RoomType.valueOf(roomTypeEnumAsAString.toUpperCase()), arrayRoomsForType);
            }
        }
    }


    public int numberOfRooms() {
        int numberOfRooms = 0;

        Set keys = this.rooms.keySet();

        for(Object type: keys){
            numberOfRooms += this.rooms.get(type).size();
        }

        return numberOfRooms;
    }

    public boolean containsRoom(Location room) {
        ArrayList arrayForRoomType = findCorrectArrayForRoomType(room);
        return arrayForRoomType.contains(room);
    }

    public int getCapacity(){
        int roomsCapacity = 0;
        Set keys = this.rooms.keySet();

        for(Object type: keys){
           ArrayList<? extends Location> arrayOfType = this.rooms.get(type);
           for (Location room: arrayOfType){
               roomsCapacity += room.getCapacity();
           }
        }
        return this.capacity + roomsCapacity;
    }

    public ReservationResult receiveReservationRequest(Location room, LocalDate startDate, LocalDate endDate, Guest guestToCharge, String... guests) {


        long usageLength = ChronoUnit.DAYS.between(startDate, endDate);


        if (!ReservedRoom.class.isInstance(room)) {
            return ReservationResult.FAILEDWRONGROOMTYPE;
        }


        ReservedRoom chosenRoom = (ReservedRoom) room;
        if(chosenRoom.getCapacity() < guests.length){
            return ReservationResult.FAILEDCAPACITY;
        }


        Boolean canPay = checkGuestCanPay(guestToCharge, chosenRoom, usageLength);
        if(!canPay){
            return ReservationResult.FAILEDNOTENOUGHFUNDS;
        }

        long cost = usageLength * chosenRoom.getRate();

        ReservationResult resultOfAttemptToReserve = ReservationSystem.createBooking(this, chosenRoom, cost, startDate, endDate, guestToCharge, guests);


        if(resultOfAttemptToReserve.equals(ReservationResult.FAILEDALREADYRESERVED)){
            return resultOfAttemptToReserve;
        }

        chargeGuest(guestToCharge, chosenRoom, usageLength);

        return resultOfAttemptToReserve;
    }

    public void addBooking(Hotel hotel, ReservedRoom room, long cost, LocalDate arrivalDate, LocalDate leaveDate, Guest organiser, ArrayList<String> guestNames){
        Booking newBooking = new Booking(hotel, room, cost, arrivalDate, leaveDate, organiser, guestNames);
        this.bookings.add(newBooking);
    }

    public ArrayList<Booking> getAllBookings() {
        ArrayList<Booking> copyBookings = this.bookings;
        return copyBookings;
    }

    public ArrayList<Booking> findAllBookingsForRoom(ReservedRoom room){

        ArrayList<Booking> roomBookings = new ArrayList<>();

        for (Booking booking: this.bookings){
            if (booking.getRoom().equals(room)){
                roomBookings.add(booking);
            }
        }

        return roomBookings;
    }

    public ArrayList<Booking> findBookingsForRoomInDateRange(ReservedRoom room, LocalDate startDate, LocalDate endDate){

        ArrayList<Booking> roomBookings = new ArrayList<>();

        for (Booking booking: this.bookings){
            if (booking.getRoom().equals(room)){
                LocalDate arrivalDate = booking.getArrivalDate();
                LocalDate leaveDate = booking.getLeaveDate();
                if (arrivalDate.isBefore(endDate) && leaveDate.isAfter(startDate)){
                    roomBookings.add(booking);
                }
            }
        }
        return roomBookings;
    }

    public ArrayList<Booking> findAllBookedRoomsInDateRange(LocalDate startDate, LocalDate endDate) {

        ArrayList<Booking> roomBookingsInRange = new ArrayList<>();

        for (Booking booking: this.bookings){
            LocalDate arrivalDate = booking.getArrivalDate();
            LocalDate leaveDate = booking.getLeaveDate();
            if (arrivalDate.isBefore(endDate) && leaveDate.isAfter(startDate)){
                roomBookingsInRange.add(booking);
            }
        }
        return roomBookingsInRange;
    }

    public HashMap<RoomType, ArrayList> findAllFreeRoomsDateRange(LocalDate startDate, LocalDate endDate){

        HashMap<RoomType, ArrayList> freeRooms = new HashMap<>();

        ArrayList<Booking> bookings = this.bookings;

        ArrayList<Bedroom> allBedrooms = rooms.get(RoomType.BEDROOM);
        ArrayList<ConferenceRoom> allConferenceRooms = rooms.get(RoomType.CONFERENCEROOM);

        ArrayList<Bedroom> freeBedrooms = new ArrayList<>();
        ArrayList<ConferenceRoom> freeConferenceRooms = new ArrayList<>();

        for(Bedroom room: allBedrooms){

            freeBedrooms.add(room);

            for(Booking booking: bookings) {

                LocalDate arrivalDate = booking.getArrivalDate();
                LocalDate leaveDate = booking.getLeaveDate();

                if(arrivalDate.isBefore(endDate) && leaveDate.isAfter(startDate)) {

                    freeBedrooms.remove(room);
                    break;

                }
            }
        }

        for(ConferenceRoom room: allConferenceRooms) {

            freeConferenceRooms.add(room);

            for(Booking booking: bookings) {

                LocalDate arrivalDate = booking.getArrivalDate();
                LocalDate leaveDate = booking.getLeaveDate();

                if(arrivalDate.isBefore(endDate) && leaveDate.isAfter(startDate)) {

                    freeConferenceRooms.remove(room);
                    break;

                }
            }
        }

        freeRooms.put(RoomType.BEDROOM, freeBedrooms);
        freeRooms.put(RoomType.CONFERENCEROOM, freeConferenceRooms);

        return freeRooms;
    }

    public ArrayList<String> getNameOfGuestsInRoomOnDate(ReservedRoom room, LocalDate date) {

        ArrayList<Booking> arrayOfOneBooking = findBookingsForRoomInDateRange(room, date, date);

        Booking booking = arrayOfOneBooking.get(0);
        ArrayList<String> guestNames = booking.getGuests();

        return guestNames;
    }

    private void chargeGuest(Guest guest, ReservedRoom room, long usageLength) {
        long rate = room.getRate();
        long totalPrice = rate * usageLength;

        guest.spendMoney(totalPrice);
    }

    private void createHashMapOfRooms(){
        HashMap<RoomType, ArrayList> rooms;

        CreateHashMapOfRooms createHashMapHelper = new CreateHashMapOfRooms();
        rooms = createHashMapHelper.createRoomHashMap();

        this.rooms = rooms;
    }

    private ArrayList<? extends Location> findCorrectArrayForRoomType(Location room){
        ArrayList<? extends Location> arrayForRoomType = new ArrayList<>();
        if (room instanceof Bedroom) {
            arrayForRoomType = this.rooms.get(RoomType.BEDROOM);

        }
        else if (room instanceof DiningRoom) {
            arrayForRoomType = this.rooms.get(RoomType.DININGROOM);
        } else if (room instanceof ConferenceRoom) {
            arrayForRoomType = this.rooms.get(RoomType.CONFERENCEROOM);
        }
        return arrayForRoomType;
    }

    private Boolean checkGuestCanPay(Guest guest, ReservedRoom room, long usageLength) {
        long guestWallet = guest.getWallet();
        long rate = room.getRate();

        long totalPrice = rate * usageLength;

        if(!(guestWallet >= totalPrice)){
            return false;
        }

        return true;
    }


}
