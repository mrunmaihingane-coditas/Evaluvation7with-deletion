import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ResourceBundle;

@WebServlet("/studentPage")
public class studentPage extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session=req.getSession();
        String name = (String) session.getAttribute("uname");
        int Id = (int) session.getAttribute("Id");
        ServletContext context = getServletContext();


        if(name==null){
            resp.sendRedirect("login.html");
        }
        else {
            out.println("WELCOME STUDENT " + name);
            out.println("<br>");
            out.println("<br>");
            try{

            Class.forName(context.getInitParameter("driver"));
            Connection con = DriverManager.getConnection(context.getInitParameter("url"),
                    context.getInitParameter("username"), context.getInitParameter("password"));

            PreparedStatement ps=con.prepareStatement(" select email, password from users where name = ? and id = ?");
            ps.setString(1,name);
            ps.setInt(2,Id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                String email = rs.getString(1);
                String pas=rs.getString(2);
                out.println("EMAIL OF STUDENT: "+email);
                out.println("<br>");
                out.println("<br>");
                out.println("PASSWORD :"+pas);
                out.println("<br>");
                out.println("<br>");
            }

            }
            catch (SQLException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }

            out.println("<a href ='Logout'>logout</a>");
            out.println("<br>"); }


                }


        }


