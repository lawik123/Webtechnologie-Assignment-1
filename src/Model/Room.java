package Model;

/**
 * Created by Lawik Ayoub on 02-Sep-16.
 */
public class Room {
    private String city;
    private String postcode;
    private String streetName;
    private int number;
    private double price;
    private int size;
    private Owner owner;
    private Renter renter;


    public Room(String city, String postcode, String streetName, int number, double price, int size,Owner owner) {
        this.city = city;
        this.postcode = postcode;
        this.streetName = streetName;
        this.number = number;
        this.price = price;
        this.size = size;
        this.owner = owner;
    }

    public Room(String city, String postcode, String streetName, int number, double price, int size, Owner owner, Renter renter) {
        this.city = city;
        this.postcode = postcode;
        this.streetName = streetName;
        this.number = number;
        this.price = price;
        this.size = size;
        this.owner = owner;
        this.renter = renter;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Renter getRenter() {
        return renter;
    }

    @Override
    public String toString() {
        return "stad: "+city+" postcode: "+postcode+" Adres: "+streetName+" "+number+" Grootte: "+size+"m2 "+"Prijs: "+price+" euro Eigenaar: "+owner.getUsername();
    }
}
