// Develop a Java application using JDBC and MVC architecture to manage student data. The application should:

// Use a Student class as the model with fields like StudentID, Name, Department, and Marks.

// Include a database table to store student data.

// Allow the user to perform CRUD operations through a simple menu-driven view.

// Implement database operations in a separate controller class.

 


import java.sql.*;
import java.util.*;

class Student {
    int studentID;
    String name;
    String department;
    double marks;

    Student(int studentID, String name, String department, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }
}


class StudentController {
    private Connection conn;

    StudentController(Connection conn) {
        this.conn = conn;
    }

    void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Student(StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, student.studentID);
        pstmt.setString(2, student.name);
        pstmt.setString(3, student.department);
        pstmt.setDouble(4, student.marks);
        pstmt.executeUpdate();
        System.out.println("Student added successfully.");
    }

    void viewStudents() throws SQLException {
        String sql = "SELECT * FROM Student";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        System.out.println("\nStudentID\tName\tDepartment\tMarks");
        while (rs.next()) {
            System.out.println(rs.getInt("StudentID") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getString("Department") + "\t" +
                    rs.getDouble("Marks"));
        }
    }

    void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, student.name);
        pstmt.setString(2, student.department);
        pstmt.setDouble(3, student.marks);
        pstmt.setInt(4, student.studentID);
        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    void close() throws SQLException {
        conn.close();
    }
}

public class experiment7_3_22BCS13785 {
    static final String URL = "jdbc:mysql://localhost:3306/your_database";
    static final String USER = "your_username";
    static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            StudentController studentController = new StudentController(conn);
            conn.setAutoCommit(false);

            while (true) {
                System.out.println("\n========= MENU =========");
                System.out.println("1. View Employees");
                System.out.println("2. Product CRUD");
                System.out.println("3. Student MVC CRUD");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewEmployees(conn);
                        break;
                    case 2:
                        productCRUD(conn, scanner);
                        break;
                    case 3:
                        studentMenu(scanner, studentController);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewEmployees(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Employee");
        System.out.println("\nEmpID\tName\tSalary");
        while (rs.next()) {
            System.out.println(rs.getInt("EmpID") + "\t" + rs.getString("Name") + "\t" + rs.getDouble("Salary"));
        }
    }

    static void productCRUD(Connection conn, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n--- Product Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            int ch = scanner.nextInt();
            scanner.nextLine();
            if (ch == 5) break;

            switch (ch) {
                case 1:
                    System.out.print("Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Quantity: ");
                    int qty = scanner.nextInt();
                    PreparedStatement insert = conn.prepareStatement("INSERT INTO Product(ProductName, Price, Quantity) VALUES (?, ?, ?)");
                    insert.setString(1, name);
                    insert.setDouble(2, price);
                    insert.setInt(3, qty);
                    insert.executeUpdate();
                    conn.commit();
                    System.out.println("Product added.");
                    break;
                case 2:
                    ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Product");
                    while (rs.next()) {
                        System.out.println(rs.getInt("ProductID") + "\t" + rs.getString("ProductName") + "\t" + rs.getDouble("Price") + "\t" + rs.getInt("Quantity"));
                    }
                    break;
                case 3:
                    System.out.print("Product ID: ");
                    int pid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New Price: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("New Quantity: ");
                    int newQty = scanner.nextInt();
                    PreparedStatement update = conn.prepareStatement("UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?");
                    update.setString(1, newName);
                    update.setDouble(2, newPrice);
                    update.setInt(3, newQty);
                    update.setInt(4, pid);
                    update.executeUpdate();
                    conn.commit();
                    System.out.println("Product updated.");
                    break;
                case 4:
                    System.out.print("Product ID to delete: ");
                    int delId = scanner.nextInt();
                    PreparedStatement delete = conn.prepareStatement("DELETE FROM Product WHERE ProductID=?");
                    delete.setInt(1, delId);
                    delete.executeUpdate();
                    conn.commit();
                    System.out.println("Product deleted.");
                    break;
            }
        }
    }

    static void studentMenu(Scanner scanner, StudentController controller) throws SQLException {
        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Back");
            System.out.print("Choice: ");
            int ch = scanner.nextInt();
            scanner.nextLine();
            if (ch == 5) break;

            switch (ch) {
                case 1:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Dept: ");
                    String dept = scanner.nextLine();
                    System.out.print("Marks: ");
                    double marks = scanner.nextDouble();
                    controller.addStudent(new Student(id, name, dept, marks));
                    break;
                case 2:
                    controller.viewStudents();
                    break;
                case 3:
                    System.out.print("ID: ");
                    int upId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New Dept: ");
                    String newDept = scanner.nextLine();
                    System.out.print("New Marks: ");
                    double newMarks = scanner.nextDouble();
                    controller.updateStudent(new Student(upId, newName, newDept, newMarks));
                    break;
                case 4:
                    System.out.print("ID to delete: ");
                    int delId = scanner.nextInt();
                    controller.deleteStudent(delId);
                    break;
            }
        }
    }
}
