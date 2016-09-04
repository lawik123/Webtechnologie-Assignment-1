import Model.Room;
import Model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Lawik Ayoub on 03-Sep-16.
 */
@WebServlet("/showrooms")
public class ShowRoomsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        if (context.getAttribute("user_list") == null) {
            context.setAttribute("user_list", new ArrayList<User>());
        }
        if (context.getAttribute("room_list") == null) {
            context.setAttribute("room_list", new ArrayList<Room>());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");
        ArrayList<Room> rooms = (ArrayList<Room>) getServletContext().getAttribute("room_list");

        for(Room room: rooms){
            for (User user: users) {
                if (room.getOwner().getUsername().equals(user.getUsername())) {
                    rooms.add(room);
                }
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
