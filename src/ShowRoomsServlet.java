import Model.Model;
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
        User user = Model.getInstance().getUser(session.getAttribute("user").toString());
        ArrayList<Room> rooms = new ArrayList<Room>();

        for(Room room: Model.getInstance().getRooms()){
            if(room.getOwner().getUsername().equals(user.getUsername())){
                rooms.add(room);
            }
        }

        out.println("<!DOCTYPE html>\n" +
                "<html lang =\"en\">\n" +
                "<head\n" +
                "<meta charset = \"UTF-8\">\n" +
                "<title> Mijn kamers</title>\n" +
                "<body>\n" +
                "<h2> Mijn kamers: </h1>\n" +
                "</br>\n" +
                "<form action=\"addroom.html\" method=\"post\">\n" +
                "    <label>\n" +
                "    <input type=\"submit\" value=\"AddRoom\">\n" +
                "    </label>\n" +
                "</form>");
        for (int i = 0; i < rooms.size(); i++) {
            out.println(i + 1 + ". " + rooms.get(i).toString() + "<br>");
        }
        out.println("</body>");


    }
}
