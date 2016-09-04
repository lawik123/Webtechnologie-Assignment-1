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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lars Meulenbroek on 29-Aug-16.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
