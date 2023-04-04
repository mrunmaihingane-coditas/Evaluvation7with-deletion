import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AdminDeleting")
public class AdminDeleting extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<br>");
        out.println("<br>");

        ServletContext sr = getServletContext();
        int id = Integer.parseInt(req.getParameter("id"));
        out.println("I AM DELETING "+id);
        out.println("<br>");
        String name = "";
        String email = "";
        String pwd = "";
        String city ="";
        String phone ="";
        try {
            Class.forName(sr.getInitParameter("driver"));
            Connection con = DriverManager.getConnection(sr.getInitParameter("url"), sr.getInitParameter("username"), sr.getInitParameter("password"));
            out.println("JDBC Connection Done");
            out.println("<br>");
            out.println("<br>");
            PreparedStatement pt = con.prepareStatement("select *from users where id=?");
            pt.setInt(1,id);
            ResultSet rs = pt.executeQuery();
            out.println("selected");
            out.println("<br>");
            while (rs.next()){
                name = rs.getString("name");
                email = rs.getString("email");
               pwd = rs.getString("password");
                city = rs.getString("city");
                phone =rs.getString("phone");
            }
            out.println("setted values");
            out.println("<br>");
            PreparedStatement pt32 = con.prepareStatement("insert into  backup(name, email, password, city, phone)values(?,?,?,?,?)");
            pt32.setString(1,name);
            pt32.setString(2,email);
            pt32.setString(3,pwd);
            pt32.setString(4,city);
            pt32.setString(5,phone);
            pt32.executeUpdate();
            out.println("Inserted  values");
            out.println("<br>");

            PreparedStatement pt1 = con.prepareStatement("DELETE FROM users WHERE id=?");
            pt1.setInt(1,id);
            pt1.executeUpdate();
            out.println("deleted values");
            out.println("<br>");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

    }
}
