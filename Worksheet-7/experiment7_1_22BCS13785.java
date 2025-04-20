// Create a Java program to connect to a MySQL database and fetch data from a single table. The program should:

// Use DriverManager and Connection objects.

// Retrieve and display all records from a table named Employee with columns EmpID, Name, and Salary.

import java.sql.*;

public class experiment7_1_22BCS13785 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database";name
        String user = "your_username";
        String password = "your_password";

        String query = "SELECT EmpID, Name, Salary FROM Employee";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("EmpID\tName\tSalary");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + "\t" +
                                   rs.getString("Name") + "\t" +
                                   rs.getDouble("Salary"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
