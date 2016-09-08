import Model.Owner;
import Model.Renter;
import Model.Room;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Lawik Ayoub on 31-Aug-16.
 */
@WebServlet("/searchroom")
public class SearchRoomServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get PrintWriter
        PrintWriter out = response.getWriter();

        //get the current Session
        HttpSession session = request.getSession();

        //check if the user is logged in, if not let the user go and give the option to login
        if (session.getAttribute("user") == null) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Beheer </title>\n" +
                    "<style> .center {\n" +
                    "text-align:center;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body class ='center'>\n" +
                    "<h3> U bent niet ingelogd</h3>\n" +
                    "<a href='/login.html'>Login</a>\n" +
                    "</body>"
            );
        }

        User currentUser = null;

        //Get the user list
        List<User> list = (List<User>) getServletContext().getAttribute("user_list");

        //Find the current user
        for (User user : list) {
            //If the user is an instance of the Renter class set the currentUser to the logged in user
            if (user.getUsername().equalsIgnoreCase(session.getAttribute("user").toString()) && user instanceof Renter) {
                currentUser = (Renter) user;
                //If the user is a renter let the user know he can't view this page and give the option to go back to the previous page
            } else if (user.getUsername().equalsIgnoreCase(session.getAttribute("user").toString())) {
                out.println("<!DOCTYPE html>\n" +
                        "<html lang =\"en\">\n" +
                        "<head\n" +
                        "<meta charset = \"UTF-8\">\n" +
                        "<title> Pagina niet beschikbaar </title>\n" +
                        "<style> .center {\n" +
                        "text-align:center;\n" +
                        "}\n" +
                        "</style>\n" +
                        "</head>\n" +
                        "<body class ='center'>\n" +
                        "<h3> Deze pagina is alleen beschikbaar voor huurders</h3>\n" +
                        "<button onclick=\"goBack()\">Vorige pagina</button>\n" +
                        "\n" +
                        "<script>\n" +
                        "function goBack() {\n" +
                        "    window.history.back();\n" +
                        "}\n" +
                        "</script>" +
                        "</body>");
            }
        }

        //if currentUser is not null(which means the current user is a Renter) display the page
        if (currentUser != null) {

            //generate unique key for the SecretRenterServlet
            Random random = new Random();
            int secretKey = random.nextInt();
            session.setAttribute("renterkey", Integer.toString(secretKey));

            //Create ArrayList for available rooms
            ArrayList<Room> availableRooms = new ArrayList<Room>();

            //Add all available rooms from the search request to the available rooms ArrayList
            for (User u : list) {
                if (u instanceof Owner) {
                    for (Room room : ((Owner) u).getMyrooms()) {
                        if (room.getCity().equals(request.getParameter("Plaats"))
                                && room.getSize() == Integer.parseInt(request.getParameter("VierkanteMeters"))
                                && room.getPrice() <= Double.parseDouble(request.getParameter("MaximalePrijs"))
                                && room.getRenter() == null) {
                            availableRooms.add(room);
                        }

                    }
                }
            }

            //Print out the available rooms
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Zoekresultaten</title>\n" +
                    "<style> .center {\n" +
                    "text-align:center;\n" +
                    "}\n" +
                    "table{\n" +
                    "font-family: arial, sans-serif;\n" +
                    "border-collapse: collapse;\n" +
                    "width: 100%;\n" +
                    "}" +
                    "td,th {\n" +
                    "border: 1px solid #dddddd;\n" +
                    "text-align: left;\n" +
                    "padding: 8px;\n" +
                    "}\n" +
                    "tr:nth-child(even) {\n" +
                    "background-color: #dddddd;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body class= 'center'>\n" +
                    "<h2> Beschikbare kamers </h1>\n" +
                    "</br>\n" +
                    "<table>\n" +
                    "<tr>\n" +
                    "<th>Stad</th>\n" +
                    "<th>Postcode</th>\n" +
                    "<th>Adres</th>\n" +
                    "<th>Grootte</th>\n" +
                    "<th>Prijs</th>\n" +
                    "<th>Eigenaar</th>\n");
            for (int i = 0; i < availableRooms.size(); i++) {
//            out.println(i + 1 + ". " + availableRooms.get(i).toString() + "<br>" + "<br>");
                out.println("<tr>\n" +
                        "<td>" + availableRooms.get(i).getCity() + "</td>\n" +
                        "<td>" + availableRooms.get(i).getPostcode() + "</td>\n" +
                        "<td>" + availableRooms.get(i).getStreetName() + " " + availableRooms.get(i).getNumber() + "</td>\n" +
                        "<td>" + availableRooms.get(i).getSize() + "</td>\n" +
                        "<td>" + availableRooms.get(i).getPrice() + "</td>\n" +
                        "<td>" + availableRooms.get(i).getOwner() + "</td>\n" +
                        "</tr>");
            }
            out.println("</table>\n");
            out.println("<form action=\"searchagain\" method=\"post\">\n" +
                    "    <input type=\"submit\" value=\"Opnieuw zoeken\">\n" +
                    "    <input type=\"hidden\" name=\"renterkey\" value=\"" + Integer.toString(secretKey) + "\">\n" +
                    "</form>");


        }
    }
}
