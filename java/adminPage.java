import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/adminPage")
public class adminPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("WELCOME ADMIN");
        out.println("<br>");
        out.println("<br>");
        out.println("<a href ='AdminEdit'>ADDSTUDENT</a>");
        out.println("<br>");
        out.println("<br>");
        out.println("<a href ='Delete.html'>DELET</a>");
        out.println("<br>");
        out.println("<br>");
    }
}
