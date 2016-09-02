import Model.Model;
import Model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Lawik Ayoub on 31-Aug-16.
 */
@WebServlet("/searchroom")
public class SearchRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        for (Room room : Model.getInstance().getRooms()) {
            if (room.getCity().equals(request.getParameter("Plaats"))
                    && room.getSize() == Integer.parseInt(request.getParameter("VierkanteMeters"))
                    && room.getPrice() <= Double.parseDouble(request.getParameter("MaximalePrijs"))
                    &&room.getRenter()==null) {
                availableRooms.add(room);
            }

        }
        out.println("<!DOCTYPE html>\n" +
                "<html lang =\"en\">\n" +
                "<head\n" +
                "<meta charset = \"UTF-8\">\n" +
                "<title> Register</title>\n" +
                "<body>\n" +
                "<h1> Beschikbare kamers: </h1>\n" +
                "</br>\n");
                for(int i= 0; i<availableRooms.size();i++){
                    out.println(i+1+". "+availableRooms.get(i).toString()+"<br>");
                }
                out.println("</body>");


    }
}
