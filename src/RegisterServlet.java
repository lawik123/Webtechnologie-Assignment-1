import Model.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lawik Ayoub on 29-Aug-16.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username;
        String password;
        String type;

        username = request.getParameter("UserName");
        password = request.getParameter("PassWord");
        type = request.getParameter("UserType");
        if(Model.getInstance().getUser(username)!=null){
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Registration failed</title>\n" +
                    "<body>\n" +
                    "<h2>username already exists</h2>\n" +
//                    "<br>\n" +
                    "<a href=registreer.html>Try again</a>\n"+
                    "</body>");


        }
        else if(type.equals("renter")){
            Model.getInstance().addUser(new Renter(username,password));
            response.sendRedirect("/login.html");
        }
        else if(type.equals("owner")){
            Model.getInstance().addUser(new Owner(username,password));
            response.sendRedirect("/login.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
