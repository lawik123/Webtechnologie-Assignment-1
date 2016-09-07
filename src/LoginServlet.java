import Model.Owner;
import Model.Renter;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lars Meulenbroek on 29-Aug-16.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the current Session
        HttpSession session = request.getSession();
        User user = null;

        //get the user list from ServletContext
        List<User> list = (List<User>) getServletContext().getAttribute("user_list");

        //Find the current user
        for (User u : list) {
            if(u.getUsername().equals(request.getParameter("UserName"))) {
                user = u;
            }
        }

        //Check if the user exists and if the password is correct
        if (user != null && user.getPassword().equals(request.getParameter("PassWord"))) {

            //If the user exists and the password is correct, add the user to the current Session
            session.setAttribute("user", user.getUsername());
            if (user instanceof Renter) {
                //If the user is a renter redirect the user to the huurder.html page
                response.sendRedirect("/huurder.html");
            } else if (user instanceof Owner) {
                //If the user is an owner redirect the user to the showrooms page
                response.sendRedirect("showrooms");
            }

        } else {
            //If the user does not exists or if the password is incorrect redirect to the fouteinlog.html page
            response.sendRedirect("/fouteinlog.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
