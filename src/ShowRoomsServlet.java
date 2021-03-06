import Model.Owner;
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
import java.util.Random;

/**
 * Created by Lawik Ayoub on 03-Sep-16.
 */
@WebServlet("/showrooms")
public class ShowRoomsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get PrintWriter
        PrintWriter out = response.getWriter();

        //Get the current Session
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
                    "<a href='login.html'>Login</a>\n" +
                    "</body>"
            );
        }

        //Get the user list
        ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");

        Owner currentUser = null;

        //Find the current user
        for (User user : users) {
            //If the user is an instance of the Owner class set the currentUser to the logged in user
            if (user.getUsername().equalsIgnoreCase(session.getAttribute("user").toString()) && user instanceof Owner) {
                currentUser = (Owner) user;
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
                        "<h3> Deze pagina is alleen beschikbaar voor verhuurders</h3>\n" +
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
        //if currentUser is not null(which means the current user is an Owner) display the page
        if (currentUser != null) {

            //generate unique key for the SecretOwnerServlet
            Random random = new Random();
            int secretKey = random.nextInt();
            session.setAttribute("ownerkey", Integer.toString(secretKey));

            //Display the current user's rooms
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Mijn kamers</title>\n" +
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
                    "<body class='center'>\n" +
                    "<h2> Mijn kamers</h1>\n" +
                    "<table>\n" +
                    "<tr>\n" +
                    "<th>Nummer</th>" +
                    "<th>Stad</th>\n" +
                    "<th>Postcode</th>\n" +
                    "<th>Adres</th>\n" +
                    "<th>Grootte</th>\n" +
                    "<th>Prijs</th>\n" +
                    "<th>Huurder</th>\n"
            );
            for (int i = 0; i < currentUser.getMyrooms().size(); i++) {
//            out.println(i + 1 + ". " + currentUser.getMyrooms().get(i).toString() + "<br>");
                out.println(
                        "<tr>\n" +
                                "<td>" + (i + 1) + "</td>\n" +
                                "<td>" + currentUser.getMyrooms().get(i).getCity() + "</td>\n" +
                                "<td>" + currentUser.getMyrooms().get(i).getPostcode() + "</td>\n" +
                                "<td>" + currentUser.getMyrooms().get(i).getStreetName() + " " + currentUser.getMyrooms().get(i).getNumber() + "</td>\n" +
                                "<td>" + currentUser.getMyrooms().get(i).getSize() + "m2" + "</td>\n" +
                                "<td>" + "&euro;" + currentUser.getMyrooms().get(i).getPrice() + "</td>\n"
                );
                if (currentUser.getMyrooms().get(i).getRenter() != null) {
                    out.println("<td>" + currentUser.getMyrooms().get(i).getRenter() + "</td>\n");
                } else {
                    out.println("<td>" + "Geen huurder" + "</td>\n");
                }
                out.println("</tr>\n");
            }

            out.println(
                    "</table>\n" +
                            "<br>" +
                            "<form action=\"roomadder\" method=\"post\">\n" +
                            "    <label>\n" +
                            "    <input type=\"submit\" value=\"Kamer toevoegen\">\n" +
                            "    <input type=\"hidden\" name=\"ownerkey\" value=\"" + Integer.toString(secretKey) + "\">\n" +
                            "    </label>\n" +
                            "</form>\n" +
                            "<br>\n" +
                            "<form action=\"delete\" method=\"post\">\n" +
                            "<label>\n" +
                            "Voer kamer nummer in: <input type=\"number\" name=\"KamerGetal\" value=\"\" min=\"1\" max=\""
                            + currentUser.getMyrooms().size()+"\">\n"+
                            "<input type=\"submit\" value=\"Verwijder\">\n" +
                            "</label>" +
                            "</form>" +
                            "<br>\n" +
                            "<form action=\"showpersons\" method=\"get\">\n" +
                            "<input type=\"submit\" value=\"beheer\">\n" +
                            "</form>\n" +
                            "<br>\n" +
                            "<form action=\"logout\" method=\"get\">\n" +
                            "<input type=\"submit\" value=\"log uit\">\n" +
                            "</form>\n" +
                            "</body>");
        }

    }
}
