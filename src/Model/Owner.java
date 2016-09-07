package Model;

import java.util.ArrayList;

/**
 * Created by Lawik Ayoub on 02-Sep-16.
 */
public class Owner extends User {

    private ArrayList<Room> myrooms;

    public Owner(String username, String password) {
        super(username, password);
        myrooms = new ArrayList<>();
    }

    public ArrayList<Room> getMyrooms() {
        return myrooms;
    }
}
