import Model.Owner;
import Model.Room;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


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

        User user = null;

        //Get the user list
        List<User> list = (List<User>) getServletContext().getAttribute("user_list");

        //Create ArrayList for available rooms
        ArrayList<Room> availableRooms = new ArrayList<Room>();

        //Add all available rooms from the search request to the available rooms ArrayList
        for (User u : list) {
            if(u instanceof Owner) {
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
                "<style> .center {\n"+
                "text-align:center;\n"+
                "}\n"+
                "table{\n"+
                "font-family: arial, sans-serif;\n"+
                "border-collapse: collapse;\n"+
                "width: 100%;\n"+
                "}"+
                "td,th {\n"+
                "border: 1px solid #dddddd;\n"+
                "text-align: left;\n"+
                "padding: 8px;\n"+
                "}\n"+
                "tr:nth-child(even) {\n"+
                "background-color: #dddddd;\n"+
                "}\n"+
                "</style>\n"+
                "</head>\n"+
                "<body class= 'center'>\n" +
                "<h2> Beschikbare kamers </h1>\n" +
                "</br>\n"+
                "<table>\n"+
                "<tr>\n"+
                "<th>Stad</th>\n"+
                "<th>Postcode</th>\n"+
                "<th>Adres</th>\n"+
                "<th>Grootte</th>\n"+
                "<th>Prijs</th>\n"+
                "<th>Eigenaar</th>\n");
        for (int i = 0; i < availableRooms.size(); i++) {
//            out.println(i + 1 + ". " + availableRooms.get(i).toString() + "<br>" + "<br>");
            out.println("<tr>\n"+
                    "<td>"+availableRooms.get(i).getCity()+"</td>\n"+
                    "<td>"+availableRooms.get(i).getPostcode()+"</td>\n"+
                    "<td>"+availableRooms.get(i).getStreetName()+" "+availableRooms.get(i).getNumber()+"</td>\n"+
                    "<td>"+availableRooms.get(i).getSize()+"</td>\n"+
                    "<td>"+availableRooms.get(i).getPrice()+"</td>\n"+
                    "<td>"+availableRooms.get(i).getOwner()+"</td>\n"+
                    "</tr>");
        }
        out.println("</table>\n");
        out.println("<form action=\"huurder.html\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Opnieuw zoeken\">\n" +
                "</form>");


    }

}
