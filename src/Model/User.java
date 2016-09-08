package Model;

/**
 * Created by Lawik Ayoub on 02-Sep-16.
 */
public class User {
    private String username;
    private String password;

    /**
     * Constructor for the User Class
     *
     * @param username The username
     * @param password The password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username;
    }
}
