import Model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lars Meulenbroek on 29-Aug-16.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        User user = Model.getInstance().getUser(request.getParameter("UserName"));
        if(user!=null&&user.getPassword().equals(request.getParameter("PassWord"))){
            if(user instanceof Renter){
                response.sendRedirect("/huurder.html");
            }
            else if(user instanceof Owner){

            }

        }
        else{
            response.sendRedirect("/fouteinlog.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
