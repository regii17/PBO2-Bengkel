public class Komponen {
    private String name;
    private int price;
    private char vehicleType; 

    public Komponen(String name, int price, char vehicleType) {
        this.name = name;
        this.price = price;
        this.vehicleType = vehicleType;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public char getVehicleType() { return vehicleType; }
}
