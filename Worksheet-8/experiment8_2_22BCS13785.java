// Create a servlet integrated with JDBC to display a list of employees from a database. 
// Include a search form to fetch employee details by ID.

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/experiment8_2_22BCS13785")
public class experiment8_2_22BCS13785 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String JDBC_USER = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Employee Directory</title></head><body>");
        out.println("<h2>Search Employee by ID</h2>");
        out.println("<form method='post' action='experiment8_2_22BCS13785'>");
        out.println("Employee ID: <input type='text' name='empId' required>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form><hr>");

        out.println("<h2>All Employees</h2>");
        out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th><th>Email</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" +
                            rs.getString("name") + "</td><td>" +
                            rs.getString("department") + "</td><td>" +
                            rs.getString("email") + "</td></tr>");
            }

            conn.close();
        } catch (Exception e) {
            out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
        }

        out.println("</table></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empId = request.getParameter("empId");

        out.println("<html><head><title>Employee Search</title></head><body>");
        out.println("<a href='experiment8_2_22BCS13785'>Back to All Employees</a><hr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
            ps.setInt(1, Integer.parseInt(empId));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h2>Employee Details</h2>");
                out.println("<p><strong>ID:</strong> " + rs.getInt("id") + "</p>");
                out.println("<p><strong>Name:</strong> " + rs.getString("name") + "</p>");
                out.println("<p><strong>Department:</strong> " + rs.getString("department") + "</p>");
                out.println("<p><strong>Email:</strong> " + rs.getString("email") + "</p>");
            } else {
                out.println("<h3>No employee found with ID " + empId + "</h3>");
            }

            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}
