import Model.Owner;
import Model.Renter;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lawik Ayoub on 05-Sep-16.
 */

@WebServlet("/showpersons")
public class ShowPersonsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Beheer </title>\n" +
                    "<style> .center {\n"+
                    "text-align:center;\n"+
                    "}\n"+
                    "</style>\n"+
                    "</head>\n"+
                    "<body class ='center'>\n" +
                    "<h3> U bent niet ingelogd</h3>\n" +
                    "<a href='/login.html'>Login</a>\n" +
                    "</body>"
            );
        } else {
            ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");
            ArrayList<User> renters = new ArrayList<>();
            ArrayList<User> owners = new ArrayList<>();
            Date date = new Date();
            Cookie[] ck = request.getCookies();
            String lastVisit = null;
            String numberVisit = null;
            if (ck != null) {
                for (Cookie cookie1 : ck) {
                    if (cookie1.getName().equals("lastVisitCookie")) {
                        lastVisit = cookie1.getValue();
                    }
                    if (cookie1.getName().equals("numberVisitCookie")) {
                        numberVisit = cookie1.getValue();
                    }
                }
            }


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
                    "<style> .center {\n"+
                    "text-align:center;\n"+
                    "}\n"+
                    "</style>\n"+
                    "</head>\n"+
                    "<body class ='center'>\n" +
                    "<h3> Laatste bezoek:\n");
            if (lastVisit != null) {
                out.println(lastVisit + "</h3>");
            }
            out.println(
                    "<h3> Aantal keren bezocht: \n ");
            if (numberVisit != null) {
                out.println(numberVisit + "</h3>");
            }
            out.println(
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
            Cookie lastVisitCookie = new Cookie("lastVisitCookie", date.toString());
            lastVisitCookie.setMaxAge(2629743);
            response.addCookie(lastVisitCookie);
            if (numberVisit == null) {
                Cookie numberVisitCookie = new Cookie("numberVisitCookie", "1");
                numberVisitCookie.setMaxAge(2629743);
                response.addCookie(numberVisitCookie);
            } else {
                Cookie numberVisitCookie = new Cookie("numberVisitCookie", Integer.toString(Integer.parseInt(numberVisit) + 1));
                numberVisitCookie.setMaxAge(2629743);
                response.addCookie(numberVisitCookie);
            }


        }

    }
}
