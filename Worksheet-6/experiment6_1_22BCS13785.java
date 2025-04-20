// Write a program to sort a list of Employee objects (name, age, salary) using lambda expressions.

import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String toString() {
        return name + " | Age: " + age + " | Salary: $" + salary;
    }
}

public class experiment6_1_22BCS13785 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 30, 70000),
            new Employee("Bob", 25, 50000),
            new Employee("Charlie", 35, 60000)
        );

        // Sort by salary using lambda
        employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));

        System.out.println("Employees sorted by salary:");
        employees.forEach(System.out::println);
    }
}
