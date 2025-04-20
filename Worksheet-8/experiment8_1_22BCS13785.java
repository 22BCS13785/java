// Write a servlet to accept user credentials through an HTML form and display a 
// personalized welcome message if the login is successful.

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class experiment8_1_22BCS13785 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password123";

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Login Page</title></head><body>");
        out.println("<h2>Login</h2>");
        out.println("<form method='post' action='LoginServlet'>");
        out.println("Username: <input type='text' name='username' required><br><br>");
        out.println("Password: <input type='password' name='password' required><br><br>");
        out.println("<input type='submit' value='Login'>");
        out.println("</form>");
        out.println("</body></html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Login Result</title></head><body>");

        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            out.println("<h2>Welcome, " + username + "!</h2>");
        } else {
            out.println("<h2>Invalid username or password.</h2>");
            out.println("<a href='LoginServlet'>Try again</a>");
        }

        out.println("</body></html>");
    }
}
