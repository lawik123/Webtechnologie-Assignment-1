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
        HttpSession session = request.getSession();
        ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("user_list");

        Owner currentUser =null;
        for (User user: users){
            if(user.getUsername().equalsIgnoreCase(session.getAttribute("user").toString())){
                currentUser = (Owner) user;
            }
        }

        int lijstnummer = 0;
        try {
            lijstnummer = Integer.parseInt(request.getParameter("KamerGetal"))-1;
        } catch (NumberFormatException nfe) {
        }

        try {
            currentUser.getMyrooms().remove(lijstnummer);
        } catch (NullPointerException npe) {

        }


        response.sendRedirect("showrooms");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
