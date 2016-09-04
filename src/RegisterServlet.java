import Model.Owner;
import Model.Renter;
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
 * Created by Lawik Ayoub on 29-Aug-16.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


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
        PrintWriter out = response.getWriter();
        String username;
        String password;
        String type;
        boolean equals = false;

        ArrayList<User> list = (ArrayList<User>) getServletContext().getAttribute("user_list");

        username = request.getParameter("UserName");
        password = request.getParameter("PassWord");
        type = request.getParameter("UserType");

        for (User u : list) {
            if (u.getUsername().equals(username)) {
                equals = true;
            }
        }
        if (equals) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Registration failed</title>\n" +
                    "<body>\n" +
                    "<h2>username already exists</h2>\n" +
                    "<a href=registreer.html>Try again</a>\n" +
                    "</body>");

        } else if (type.equals("renter")) {
            list.add(new Renter(username, password));
            response.sendRedirect("/login.html");
        } else if (type.equals("owner")) {
            list.add(new Owner(username, password));
            response.sendRedirect("/login.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
