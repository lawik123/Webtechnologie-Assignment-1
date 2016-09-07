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

        Owner currentUser =null;
        for (User user: users){
            if(user.getUsername().equalsIgnoreCase(session.getAttribute("user").toString())){
                currentUser = (Owner) user;
            }
        }

        out.println("<!DOCTYPE html>\n" +
                "<html lang =\"en\">\n" +
                "<head\n" +
                "<meta charset = \"UTF-8\">\n" +
                "<title> Mijn kamers</title>\n" +
                "<style> .center {\n"+
                "text-align: center;\n"+
                "}\n"+
                "</style>\n"+
                "</head>\n"+
                "<body class='center'>\n" +
                "<h2> Mijn kamers: </h1>\n" +
                "</br>\n");
        for (int i = 0; i < currentUser.getMyrooms().size(); i++) {
            out.println(i + 1 + ". " + currentUser.getMyrooms().get(i).toString() + "<br>");
        }
        out.println("<br>" +
                "<form action=\"/addroom.html\" method=\"post\">\n" +
                "    <label>\n" +
                "    <input type=\"submit\" value=\"AddRoom\">\n" +
                "    </label>\n" +
                "</form>\n" +
                "<br>\n" +
                "<form action=\"delete\" method=\"post\">\n" +
                "<label>\n" +
                "Voer kamer nummer in: <input type=\"number\" name=\"KamerGetal\" value=\"\" min=\"1\">" +
                "<input type=\"submit\" value=\"Delete\">\n" +
                "</label>" +
                "</form>" +
                "<br>\n" +
                "<br>\n" +
                "<form action=\"logout\" method=\"get\">\n" +
                "<input type=\"submit\" value=\"Log out\">\n" +
                "</form>\n" +
                "</body>");
    }
}
