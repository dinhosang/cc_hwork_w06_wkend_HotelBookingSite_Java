## Classes - with Properties / Methods
##

## Guest
<!-- -- name [String] -->
<!-- -- wallet [int] -->
\--location [Room]

<!-- ~~getName() [String] -->
<!-- ~~getWallet() [int] -->
<!-- ~~spendMoney(amountSpent) [void] -->
\~~getLocation() [Room]

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

-- roomsReserved [HashMap]

~~ checkIn(room, numberNight, guests) [void]
  - test capacity here
~~ chargeGuest(room, guests)
~~ checkOut(room, guests) [void]
~~ findGuestsInRoom(room) [ArrayList<Guest>]
~~ findGuestsInHotel() [ArrayList<Guest>]
~~ findEmptyRooms() [ArrayList<Room>]
