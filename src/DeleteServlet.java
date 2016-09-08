import Model.Owner;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get the current session
        HttpSession session = request.getSession();

        //Get the user list from the ServletContext
        ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");

        Owner currentUser = null;

        //Find the current user
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(session.getAttribute("user").toString())) {
                currentUser = (Owner) user;
            }
        }

        int listNumber = 0;
        try {
            //Retrieve the ArrayList position of the room from the parameter
            listNumber = Integer.parseInt(request.getParameter("KamerGetal")) - 1;
        } catch (NumberFormatException nfe) {
        }

        try {
            //Delete the room from the ArrayList
            currentUser.getMyrooms().remove(listNumber);
        } catch (NullPointerException npe) {

        }

        //Redirect to showrooms
        response.sendRedirect("showrooms");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
