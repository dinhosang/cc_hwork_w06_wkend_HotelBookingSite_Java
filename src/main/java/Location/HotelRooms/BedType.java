package Location.HotelRooms;

public enum BedType {

    SINGLE(1),
    DOUBLE(2),
    TRIPLE(3);


    private final int typeCapacity;

    BedType(int typeCapacity) {
        this.typeCapacity = typeCapacity;
    }


    public int getTypeCapacity() {
        int copyTypeCapacity = this.typeCapacity;
        return copyTypeCapacity;
    }
}
