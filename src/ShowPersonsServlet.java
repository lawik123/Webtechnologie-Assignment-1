import Model.Owner;
import Model.Renter;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Lawik Ayoub on 05-Sep-16.
 */

@WebServlet("/showpersons")
public class ShowPersonsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");
        ArrayList<User> renters = new ArrayList<>();
        ArrayList<User> owners = new ArrayList<>();

        for (User user : users) {
            if (user instanceof Renter) {
                renters.add(user);
            } else if (user instanceof Owner) {
                owners.add(user);
            }
        }
        out.println("<!DOCTYPE html>\n" +
                "<html lang =\"en\">\n" +
                "<head\n" +
                "<meta charset = \"UTF-8\">\n" +
                "<title> Beheer </title>\n" +
                "<body>\n" +
                "<h3> Laatste bezoek: </h3> \n"+
                "<h3> Aantal keren bezocht:</h3>\n "+
                "<h4> Verhuurders: </h4>\n");
        for (int i = 0; i < owners.size(); i++) {
            out.println(i + 1 + ". " + owners.get(i).toString() + "<br>");
        }
        out.println("<br>\n" +
                "<h4> Huurders:</h4> \n ");
        for (int i = 0; i < renters.size(); i++) {
            out.println(i + 1 + ". " + renters.get(i).toString() + "<br>");
        }
        out.println("</body>");
    }


}
