import Model.Owner;
import Model.Renter;
import Model.Room;
import Model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;

public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        if (context.getAttribute("user_list") == null) {
            context.setAttribute("user_list", new ArrayList<User>());

            ArrayList<User> list = (ArrayList<User>) context.getAttribute("user_list");
            list.add(new Renter("renter","renter"));
            list.add(new Owner("owner","owner"));
        }
        if (context.getAttribute("room_list") == null) {
            context.setAttribute("room_list", new ArrayList<Room>());
            ArrayList<Room> rooms = (ArrayList<Room>) context.getAttribute("room_list");
            ArrayList<User> users = (ArrayList<User>) context.getAttribute("user_list");
            rooms.add(new Room("Deventer","7421AD","Grove Denlaan",34,100.00,100,(Owner)users.get(1)));
            rooms.add(new Room("Deventer","3333BM","Aap",22,50.53,100,(Owner)users.get(1)));
            rooms.add(new Room("Deventer","4444DS","Test",30,25,100,(Owner)users.get(1),(Renter)users.get(0)));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
