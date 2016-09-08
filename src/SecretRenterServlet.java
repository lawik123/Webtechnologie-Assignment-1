import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lawik Ayoub on 08-Sep-16.
 */
@WebServlet("/searchagain")
public class SecretRenterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get PrintWriter
        PrintWriter out = response.getWriter();

        //Get the current Session
        HttpSession session = request.getSession();

        //check if the user came here from the /searchroom page.
        String secretParamString = request.getParameter("renterkey");
        String secretAttributeString = session.getAttribute("renterkey").toString();
        int secretParamInt = Integer.parseInt(secretParamString);
        int secretAttributeInt = Integer.parseInt(secretAttributeString);
        if (secretParamInt == secretAttributeInt) {
            //if the user came here from the /searchroom page forward to the /WEB-INF/huurder.html page
            session.removeAttribute("renterkey");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/huurder.html");
            dispatcher.forward(request, response);
        }

        //if the user did not get here from the /searchroom page, let the user know and give the option to go to the previous page
        else {
            out.println("<!DOCTYPE html>\n" +
                    "<html lang =\"en\">\n" +
                    "<head\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<title> Pagina niet beschikbaar </title>\n" +
                    "<style> .center {\n" +
                    "text-align:center;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body class ='center'>\n" +
                    "<h3> Deze pagina kan niet worden weergegeven</h3>\n" +
                    "<button onclick=\"goBack()\">Vorige pagina</button>\n" +
                    "\n" +
                    "<script>\n" +
                    "function goBack() {\n" +
                    "    window.history.back();\n" +
                    "}\n" +
                    "</script>" +
                    "</body>");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get the PrintWriter
        PrintWriter out = response.getWriter();

        //If the user did not get here from the /searchroom page, let the user know and give the option to go to the previous page
        out.println("<!DOCTYPE html>\n" +
                "<html lang =\"en\">\n" +
                "<head\n" +
                "<meta charset = \"UTF-8\">\n" +
                "<title> Pagina niet beschikbaar </title>\n" +
                "<style> .center {\n" +
                "text-align:center;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body class ='center'>\n" +
                "<h3> Deze pagina kan niet worden weergegeven</h3>\n" +
                "<button onclick=\"goBack()\">Vorige pagina</button>\n" +
                "\n" +
                "<script>\n" +
                "function goBack() {\n" +
                "    window.history.back();\n" +
                "}\n" +
                "</script>" +
                "</body>");
    }

}
