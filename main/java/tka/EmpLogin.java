package tka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class EmpLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/204db", "root", "4828")) {
                PreparedStatement ps = c.prepareStatement("SELECT * FROM employee WHERE email = ? AND password = ?");
                ps.setString(1, email);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                   
                    HttpSession session = req.getSession();
                    session.setAttribute("id", rs.getInt("id"));
                    session.setAttribute("user", rs.getString("name"));
                    session.setAttribute("email", rs.getString("email"));
                    session.setAttribute("phone", rs.getLong("phone"));
                    session.setAttribute("city", rs.getString("city"));
                    session.setAttribute("age", rs.getInt("age"));
                    session.setAttribute("country", rs.getString("country"));
                    session.setAttribute("dob", rs.getDate("date"));

                    
                    resp.sendRedirect(req.getContextPath() + "/profile.jsp");
                } else {
                   
                    resp.sendRedirect("login.html?error=Invalid Credentials");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


