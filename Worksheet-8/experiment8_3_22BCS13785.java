// Develop a JSP-based student portal. 
// Include a form for entering attendance details and save them to the database using a servlet.

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/experiment8_3_22BCS13785")
public class experiment8_3_22BCS13785 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String JDBC_USER = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Student Attendance Portal</title></head><body>");
        out.println("<h2>Enter Attendance Details</h2>");
        out.println("<form method='post' action='experiment8_3_22BCS13785'>");
        out.println("Name: <input type='text' name='student_name' required><br><br>");
        out.println("Roll No: <input type='text' name='roll_no' required><br><br>");
        out.println("Subject: <input type='text' name='subject' required><br><br>");
        out.println("Status: <select name='status'>");
        out.println("<option value='Present'>Present</option>");
        out.println("<option value='Absent'>Absent</option>");
        out.println("</select><br><br>");
        out.println("<input type='submit' value='Submit Attendance'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("student_name");
        String roll = request.getParameter("roll_no");
        String subject = request.getParameter("subject");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "INSERT INTO attendance (student_name, roll_no, subject, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, roll);
            ps.setString(3, subject);
            ps.setString(4, status);

            int i = ps.executeUpdate();

            out.println("<html><body>");
            if (i > 0) {
                out.println("<h2>Attendance submitted successfully!</h2>");
            } else {
                out.println("<h2>Failed to submit attendance.</h2>");
            }
            out.println("<a href='experiment8_3_22BCS13785'>Back to Form</a>");
            out.println("</body></html>");

            conn.close();
        } catch (Exception e) {
            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
        }
    }
}
