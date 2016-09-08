package Model;

import java.util.ArrayList;

/**
 * Created by Lawik Ayoub on 02-Sep-16.
 */
public class Owner extends User {

    private ArrayList<Room> myrooms;

    /**
     * Constructor for the Owner Class
     *
     * @param username The username
     * @param password The password
     */
    public Owner(String username, String password) {
        super(username, password);
        myrooms = new ArrayList<>();
    }

    //getters
    public ArrayList<Room> getMyrooms() {
        return myrooms;
    }
}
