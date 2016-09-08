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
        //Get the ServletContext
        ServletContext context = servletContextEvent.getServletContext();

        //If there is no user list create a new new one
        if (context.getAttribute("user_list") == null) {
            context.setAttribute("user_list", new ArrayList<User>());
            ArrayList<User> list = (ArrayList<User>) context.getAttribute("user_list");

            //Add dummy data to the user list
            Owner owner = new Owner("owner", "owner");
            list.add(new Renter("renter", "renter"));
            list.add(owner);
            owner.getMyrooms().add(new Room("Deventer", "7421AD", "Grove Denlaan", 34, 100.00, 100, owner));
            owner.getMyrooms().add(new Room("Deventer", "3333BM", "Aap", 22, 50.53, 100, owner));
            owner.getMyrooms().add(new Room("Deventer", "4444DS", "Test", 30, 25, 100, owner));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
