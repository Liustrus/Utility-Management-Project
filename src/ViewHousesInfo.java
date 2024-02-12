public class ViewHousesInfo {
    public static void main(String[] args) {


        House basicHouse = new House("Žalgirio g. 100, Vilnius", 10.0);
        ExtendedHouse extendedHouse = new ExtendedHouse("456 Oak St", 20.0, 21.31, 101, 2);

        basicHouse.displayDetails();
        System.out.println("\n");
        extendedHouse.displayDetails();

    }
}

class House {
    private String address;
    private double price;

    public House(String address, double price) {
        this.address = address;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Address: " + address);
        System.out.println("Price: $" + price);
    }
}

class ExtendedHouse extends House {
    private double newPrice;
    private int houseId;
    private int utilityId;

    public ExtendedHouse(String address, double price, double newPrice, int houseId, int utilityId) {
        super(address, price);
        this.newPrice = newPrice;
        this.houseId = houseId;
        this.utilityId = utilityId;
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("New Price: $" + newPrice);
        System.out.println("House ID: " + houseId);
        System.out.println("Utility ID: " + utilityId);
    }
}
