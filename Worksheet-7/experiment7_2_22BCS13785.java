// Build a program to perform CRUD operations (Create, Read, Update, Delete) on a database table Product with columns:

// ProductID, ProductName, Price, and Quantity.
// The program should include:

// Menu-driven options for each operation.

// Transaction handling to ensure data integrity.

import java.sql.*;
import java.util.Scanner;

public class experiment7_2_22BCS13785 {
    static final String URL = "jdbc:mysql://localhost:3306/your_database";
    static final String USER = "your_username";
    static final String PASSWORD = "your_password";

    static Connection conn;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false);

            while (true) {
                System.out.println("\n1. Add Product\n2. View Products\n3. Update Product\n4. Delete Product\n5. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: addProduct(); break;
                    case 2: viewProducts(); break;
                    case 3: updateProduct(); break;
                    case 4: deleteProduct(); break;
                    case 5: conn.close(); return;
                    default: System.out.println("Invalid Choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addProduct() {
        try {
            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = scanner.nextInt();

            String sql = "INSERT INTO Product(ProductName, Price, Quantity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, qty);
            pstmt.executeUpdate();

            conn.commit();
            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Transaction rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    static void viewProducts() throws SQLException {
        String sql = "SELECT * FROM Product";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        System.out.println("ProductID\tName\tPrice\tQuantity");
        while (rs.next()) {
            System.out.println(rs.getInt("ProductID") + "\t" +
                               rs.getString("ProductName") + "\t" +
                               rs.getDouble("Price") + "\t" +
                               rs.getInt("Quantity"));
        }
    }

    static void updateProduct() {
        try {
            System.out.print("Enter Product ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter New Quantity: ");
            int qty = scanner.nextInt();

            String sql = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, qty);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();
            conn.commit();
            if (rows > 0) System.out.println("Product updated.");
            else System.out.println("Product not found.");

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
    }

    static void deleteProduct() {
        try {
            System.out.print("Enter Product ID to delete: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM Product WHERE ProductID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            conn.commit();
            if (rows > 0) System.out.println("Product deleted.");
            else System.out.println("Product not found.");

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
    }
}
