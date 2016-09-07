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


    /**
     * Constructor for the Room Class without a renter
     *
     * @param city       The city
     * @param postcode   The postal code
     * @param streetName The street name
     * @param number     The house number
     * @param price      The monthly price
     * @param size       The size in m2
     * @param owner      The room owner
     */
    public Room(String city, String postcode, String streetName, int number, double price, int size, Owner owner) {
        this.city = city;
        this.postcode = postcode;
        this.streetName = streetName;
        this.number = number;
        this.price = price;
        this.size = size;
        this.owner = owner;
    }

    /**
     * Constructor for the Room Class wit a renter
     *
     * @param city       The city
     * @param postcode   The postal code
     * @param streetName The street name
     * @param number     The house number
     * @param price      The monthly price
     * @param size       The size in m2
     * @param owner      The room owner
     * @param renter     The current renter
     */
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

    //getters
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

    public Renter getRenter() {
        return renter;
    }

    //setters
    public void setRenter(Renter renter) {
        this.renter = renter;
    }
}
