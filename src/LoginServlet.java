import Model.Owner;
import Model.Renter;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
            if (u.getUsername().equals(request.getParameter("UserName"))) {
                user = u;
            }
        }

        //Check if the user exists and if the password is correct
        if (user != null && user.getPassword().equals(request.getParameter("PassWord"))) {

            //If the user exists and the password is correct, add the user to the current Session
            session.setAttribute("user", user.getUsername());
            if (user instanceof Renter) {
                Random random = new Random();
                int ra = random.nextInt();
                session.setAttribute("random", Integer.toString(ra));
                //If the user is a renter forward the user to the /WEB-INF/huurder.html page
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/huurder.html");
                dispatcher.forward(request, response);
            } else if (user instanceof Owner) {
                //If the user is an owner redirect the user to the showrooms page
                response.sendRedirect("showrooms");
            }

        } else {
            //If the user does not exists or if the password is incorrect redirect to the fouteinlog.html page
            response.sendRedirect("fouteinlog.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
