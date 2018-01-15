## Classes - with Properties / Methods
##

## Guest
<!-- -- name [String] -->
<!-- -- wallet [int] -->
<!-- -- bookings -->


\~~checkCurrentFutureBookings
\~~checkPastBookings
<!-- ~~getName() [String] -->
<!-- ~~getWallet() [int] -->
<!-- ~~spendMoney(amountSpent) [void] -->
<!-- \~~requestReservation(hotel, room, guests) -->

## Room
<!-- -- capacity [int] -->
<!-- -- occupants [ArrayList<Guest>] -->
<!-- ~~ getCapacity() [int] -->
<!-- ~~ receiveGuest(guests) [void] -->
<!-- ~~ releaseGuest(guests) [void/Guest] - void -->

##
#Room Subclass - with Properties / Methods

#Bedroom
<!-- -- roomNumber [String] -->
<!-- -- bedType [Enum] -->
<!-- -- capacity [override - taken from bedType Enum] -->
<!-- -- rate [int] -->
<!-- ~~ getRoomNumber() [String] -->
<!-- ~~ getRate() -->

#Conference Room
<!-- -- name [String] -->
<!-- -- rate [int] -->

#Dining Room
<!-- -- name [String] -->
##

## Hotel
<!-- -- rooms hashMap -->
<!-- ~~ addRooms(Location... rooms) -->
<!-- ~~ removeRooms(Location.. rooms) -->

<!-- -- roomsReserved [HashMap] -->

<!-- ~~ chargeGuest(room, guests) -->
<!-- ~~makeReservation -->
<!-- ~~removeReservation -->

#Booking

<!-- ~~ findBookedRoomsInDateRange(dateStart, dateEnd) -->

<!-- ~~ findBookingsForRoom(room) -->

~~ findOrganiserBookedRoom(HashMap)
~~ findNameOfGuestsInBookedRoom(HashMap)

~~ findEmptyRoomsInDateRange(Type, DateStart, DateEnd)
