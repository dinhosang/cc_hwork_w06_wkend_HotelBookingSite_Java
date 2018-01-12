## Classes - with Properties / Methods

##Guest
<!-- -- name [String] -->
<!-- -- wallet [int] -->
--location [Room]

<!-- ~~getName() [String] -->
<!-- ~~getWallet() [int] -->
~~getLocation() [Room]

##Room
<!-- -- capacity [int] -->
<!-- -- occupants [ArrayList<Guest>] -->
<!-- ~~ receiveGuest(guests) [void] -->
<!-- ~~ releaseGuest(guests) [void/Guest] - void -->

## Room Subclass - with Properties / Methods

Bedroom
-- roomNumber [int]
-- bedType [Enum]
-- capacity [override - taken from bedType Enum]
-- rate [int]

Conference Room
-- name [String]
-- rate [int]

Dining Room
-- name [String]
##


##Hotel
-- rooms [ArrayList<Room>]
~~ checkIn(room, numberNight, guests) [void]
  - test capacity here
~~ chargeGuest(room, guests)
~~ checkOut(room, guests) [void]
~~ findGuestsInRoom(room) [ArrayList<Guest>]
~~ findGuestsInHotel() [ArrayList<Guest>]
~~ findEmptyRooms() [ArrayList<Room>]
