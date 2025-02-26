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

@WebServlet("/Registration")
public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String pass = req.getParameter("pass");
            String city = req.getParameter("city");
            Long phone = Long.parseLong(req.getParameter("mobile"));
            int age = Integer.parseInt(req.getParameter("age"));
            String country = req.getParameter("country");
            Date dob = Date.valueOf(req.getParameter("dob"));

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/204db", "root", "4828");

            PreparedStatement ps = c.prepareStatement("INSERT INTO employee (id, name, email, password, phone, city, age, country, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setLong(5, phone);
            ps.setString(6, city);
            ps.setInt(7, age);
            ps.setString(8, country);
            ps.setDate(9, dob);
            ps.executeUpdate();
            ps.close();
            c.close();

            RequestDispatcher rd = req.getRequestDispatcher("/login.html");
            rd.forward(req, resp);
            
            System.out.println(name);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
