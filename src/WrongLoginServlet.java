import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/wronglogin")
public class WrongLoginServlet extends HttpServlet {
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
                "<p> Wrong username and password combination" +
                "<br>\n" +
                "<a href=\"/wronglogin\">Login </a>" +
                "</body>\n" +
                "</html>");
    }
}
