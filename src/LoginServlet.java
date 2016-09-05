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
        HttpSession session = request.getSession();
        User user = null;
        List<User> list = (List<User>) getServletContext().getAttribute("user_list");
        for (User u : list) {
            if(u.getUsername().equals(request.getParameter("UserName"))) {
                user = u;
            }
        }

        if (user != null && user.getPassword().equals(request.getParameter("PassWord"))) {
            session.setAttribute("user", user.getUsername());
            if (user instanceof Renter) {
                response.sendRedirect("/huurder.html");
            } else if (user instanceof Owner) {
                response.sendRedirect("showrooms");
            }

        } else {
            response.sendRedirect("/fouteinlog.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
