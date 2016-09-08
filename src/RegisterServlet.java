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
 * Created by Lawik Ayoub on 29-Aug-16.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get PrintWriter
        PrintWriter out = response.getWriter();

        String username;
        String password;
        String type;
        boolean equals = false;

        ArrayList<User> list = (ArrayList<User>) getServletContext().getAttribute("user_list");

        username = request.getParameter("UserName"); //Get the UserName parameter
        password = request.getParameter("PassWord"); //Get the PassWord parameter
        type = request.getParameter("UserType"); //Get the UserType parameter


        //Check if the username doesn't already exist
        for (User u : list) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                equals = true;
            }
        }
        //If the username already exists let the user know and give the option to try again
        if (equals) {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Registratie mislukt</title>\n" +
                    "<style> .center {\n"+
                    "text-align:center;\n"+
                    "}\n"+
                    "</style>\n"+
                    "</head>\n"+
                    "<body class ='center'>\n" +
                    "<h2>Gebruikersnaam is al in gebruik</h2>\n" +
                    "<a href=registreer.html>Probeer opnieuw</a>\n" +
                    "</body>");

        } else if (type.equals("renter")) {
            //If the username is valid and the user type is Renter create a new Renter and add it to the user list
            list.add(new Renter(username, password));
            //Redirect to the login.html page
            response.sendRedirect("/login.html");
        } else if (type.equals("owner")) {
            //If the username is valid and the user type is Owner create a new Owner and add it to the user list
            list.add(new Owner(username, password));
            //Redirect to the login.html page
            response.sendRedirect("/login.html");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
