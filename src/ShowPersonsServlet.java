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
        //Get PrintWriter
        PrintWriter out = response.getWriter();

        //Get the current Session
        HttpSession session = request.getSession();

        //Check if the user is logged in, if not let the user know and give the option to login
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
        } else {
            //get the user list
            ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");

            //create new renters and owners ArrayLists
            ArrayList<User> renters = new ArrayList<>();
            ArrayList<User> owners = new ArrayList<>();

            //Get the current date
            Date date = new Date();

            //Get all cookies
            Cookie[] ck = request.getCookies();

            String lastVisit = null;
            String numberVisit = null;

            //If cookies are available get the values of the lastVisitCookie and the numberVisitCookie
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

            //Add all the Renters to the renters ArrayList and all the Owners to the owners ArrayList
            for (User user : users) {
                if (user instanceof Renter) {
                    renters.add(user);
                } else if (user instanceof Owner) {
                    owners.add(user);
                }
            }

            //Display all the renters, owners, the last time this page was visited and the amount of times this page has been visited
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Beheer </title>\n" +
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
                    "<body class ='center'>\n" +
                    "<h4> Laatste bezoek:\n");
            if (lastVisit != null) {
                out.println(lastVisit + "</h4>");
            }
            out.println(
                    "<h4> Aantal keren bezocht: \n ");
            if (numberVisit != null) {
                out.println(numberVisit + "</h4>");
            }
            out.println(
                    "<h2> Gebruikers </h2>\n");
            out.println("<table>\n" +
                    "<tr>\n" +
                    "<th>Naam</th>" +
                    "<th>Type</th>\n");
            for (User user : users) {
                out.println("<tr>\n" +
                        "<td>" + user.getUsername() + "</td>\n");
                if (user instanceof Owner) {
                    out.println("<td>" + "Owner" + "</td>\n");
                } else if (user instanceof Renter) {
                    out.println("<td>" + "Renter" + "</td>\n");
                }
                out.println("</tr>\n");

            }
            out.println(
                    "</table>\n" + "" +
                            "</body>");

            //create a new lastVisitCookie containing the date and time this page was visited
            Cookie lastVisitCookie = new Cookie("lastVisitCookie", date.toString());
            lastVisitCookie.setMaxAge(2629743);
            response.addCookie(lastVisitCookie);

            //If this is the first time this page has been visited create a new numberVisitCookie containing 1 as the page counter
            if (numberVisit == null) {
                Cookie numberVisitCookie = new Cookie("numberVisitCookie", "1");
                numberVisitCookie.setMaxAge(2629743);
                response.addCookie(numberVisitCookie);

                //if this is not the first time this page has been visited create a new numberVisitCookie containing the previous count+1 as the page counter
            } else {
                Cookie numberVisitCookie = new Cookie("numberVisitCookie", Integer.toString(Integer.parseInt(numberVisit) + 1));
                numberVisitCookie.setMaxAge(2629743);
                response.addCookie(numberVisitCookie);
            }


        }


    }
}
