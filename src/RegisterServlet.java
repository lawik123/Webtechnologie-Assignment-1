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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Room Rental</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1> Room Rental</h1>\n" +
                "<h4> Register </h4>\n" +
                "\n" +
                "<form action=\"login\">\n" +
                "    Username: <input type=\"text\" name=\"UserName\" value=\"\"><br>\n" +
                "    <br>\n" +
                "    Password: <input type=\"text\" name=\"PassWord\" value=\"\"><br>\n" +
                "    <br>\n" +
                "    <input type=\"radio\" name=\"UserType\" value=\"renter\"> Renter" +
                "    <input type=\"radio\" name=\"UserType\" value=\"owner\"> owner<br>" +
                "    <br>" +
                "    <input type=\"submit\" value=\"Register\">\n" +
                "</form>\n" +
                "<br>\n" +
                "</body>\n" +
                "</html>");
    }
}
