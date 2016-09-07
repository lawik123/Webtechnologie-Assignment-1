import Model.Owner;
import Model.Room;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addroom")
public class AddRoomServlet extends HttpServlet {
    private ArrayList<Room> rooms;
    private ArrayList<User> users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = (ArrayList<User>) getServletContext().getAttribute("user_list");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");

        Owner currentUser =null;
        for (User user: users){
            if(user.getUsername().equalsIgnoreCase(session.getAttribute("user").toString())){
                currentUser = (Owner) user;
            }
        }

        String location = request.getParameter("Plaats");
        String postalcode = request.getParameter("Postcode");
        String street = request.getParameter("Straat");
        int number = Integer.parseInt(request.getParameter("Huisnummer"));
        double price = Double.parseDouble(request.getParameter("MaximalePrijs"));
        int squareMeters = Integer.parseInt(request.getParameter("VierkanteMeters"));

        currentUser.getMyrooms().add(new Room(location, postalcode, street, number, price, squareMeters, currentUser));

        response.sendRedirect("showrooms");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
