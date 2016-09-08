import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Get the current Session
        HttpSession session = request.getSession();

        //Invalidate the session
        session.setAttribute("user","1111");
//        session.removeAttribute("user");
//        session.invalidate();
        //Redirect to the login.html page
//        response.sendRedirect("login.html");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
        dispatcher.forward(request,response);


    }
}
