import Model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> list = (List<Room>) getServletContext().getAttribute("room_list");
        int lijstnummer = 0;
        try {
            lijstnummer = Integer.parseInt(request.getParameter("KamerGetal"))-1;
        } catch (NumberFormatException nfe) {
            
        }
        list.remove(lijstnummer);

        response.sendRedirect("showrooms");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
