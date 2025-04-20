// Create a menu-based Java application with the following options. 
// 1.Add an Employee 
// 2. Display All 
// 3. Exit If option 1 is selected, the application should gather details of the employee like employee name, 
// employee id, designation and salary and store it in a file. 
// If option 2 is selected, the application should display all the employee details. 
// If option 3 is selected the application should exit.

import java.io.*;
import java.util.*;

class Emp implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String desig;
    private Double money;

    public Emp(Integer id, String name, String desig, Double money) {
        this.id = id;
        this.name = name;
        this.desig = desig;
        this.money = money;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Designation: " + desig + ", Salary: " + money);
    }

    public Double get_salary() {  
        return money;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class experiment5_3__22bcs13785 {
    private static final String FILE_NAME = "employees.ser";
    private static List<Emp> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        loadEmployees();  
        Scanner ss = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Employee\n2. Display All\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = ss.nextInt();
            ss.nextLine(); 

            switch (choice) {
                case 1:
                    addEmployee(ss);
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    saveEmployees();
                    ss.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addEmployee(Scanner ss) {
        System.out.print("Enter Employee ID: ");
        Integer id = ss.nextInt();
        ss.nextLine();
        System.out.print("Enter Name: ");
        String name = ss.nextLine();
        System.out.print("Enter Designation: ");
        String designation = ss.nextLine();
        System.out.print("Enter Salary: ");
        Double salary = ss.nextDouble();

        Emp emp = new Emp(id, name, designation, salary);
        employeeList.add(emp);
        System.out.println("Employee added successfully!");
        saveEmployees();
    }

    private static void displayAll() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        employeeList.forEach(Emp::display);  

        System.out.println("\nEmployees earning above 50000:");
        employeeList.stream()
                .filter(emp -> emp.get_salary() > 50000) 
                .forEach(Emp::display);  
    }

    private static void saveEmployees() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employeeList);
        } catch (IOException e) {
            System.err.println("Error saving employees.");
        }
    }

    private static void loadEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employeeList = (List<Emp>) ois.readObject();  
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading employees.");
        }
    }
}
