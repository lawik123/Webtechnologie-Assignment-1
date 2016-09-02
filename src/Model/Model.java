package Model;

import java.util.ArrayList;

/**
 * Created by Lawik Ayoub on 02-Sep-16.
 */
public class Model {
    private static Model ourInstance = new Model();
    private ArrayList<User> users;
    private ArrayList<Room> rooms;

    public static Model getInstance() {
        return ourInstance;
    }

    private Model() {
        //create dummy data
        users = new ArrayList<User>();
        rooms = new ArrayList<Room>();
        users.add(new Renter("renter","renter"));
        users.add(new Owner("owner","owner"));
        rooms.add(new Room("Deventer","7421AD","Grove Denlaan",34,100.00,100,(Owner)users.get(1)));
        rooms.add(new Room("Deventer","3333BM","Aap",22,50.53,100,(Owner)users.get(1)));
        rooms.add(new Room("Deventer","4444DS","Test",30,25,100,(Owner)users.get(1),(Renter)users.get(0)));
    }

    public void addUser(User user){
        users.add(user);
    }


    public User getUser(String userName){
        for(User user:users){
            if(user.getUsername().equals(userName)){
                return user;
            }
        }
        return null;
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }
}
