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

/**
 * Created by Lawik Ayoub on 03-Sep-16.
 */
@WebServlet("/showrooms")
public class ShowRoomsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");
        ArrayList<Room> rooms = (ArrayList<Room>) getServletContext().getAttribute("room_list");
        ArrayList<Room> myRooms = new ArrayList<>();
        User currentUser =null;
        for (User user: users){
            if(user.getUsername().equalsIgnoreCase(session.getAttribute("user").toString())){
                currentUser = user;
            }
        }

        for(Room room: rooms){
                if (room.getOwner().getUsername().equals(currentUser.getUsername())) {
                    myRooms.add(room);
                }

        }

        out.println("<!DOCTYPE html>\n" +
                "<html lang =\"en\">\n" +
                "<head\n" +
                "<meta charset = \"UTF-8\">\n" +
                "<title> Mijn kamers</title>\n" +
                "<body>\n" +
                "<h2> Mijn kamers: </h1>\n" +
                "</br>\n");
        for (int i = 0; i < myRooms.size(); i++) {
            out.println(i + 1 + ". " + myRooms.get(i).toString() + "<br>");
        }
        out.println("<br>" +
                "<form action=\"addroom.html\" method=\"post\">\n" +
                "    <label>\n" +
                "    <input type=\"submit\" value=\"AddRoom\">\n" +
                "    </label>\n" +
                "</form>\n" +
                "<a href=\"/login.html\">log uit </a>\n" +
                "</body>");
    }
}
