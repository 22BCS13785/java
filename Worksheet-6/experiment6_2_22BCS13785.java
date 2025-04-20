// Create a program to use lambda expressions and stream operations to filter students scoring above 75%, 
// sort them by marks, and display their names.

import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return name + " | Marks: " + marks;
    }
}

public class experiment6_2_22BCS13785 {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Anya", 92.5),
            new Student("Raj", 74.3),
            new Student("Meena", 81.2),
            new Student("Vikram", 68.0)
        );

        System.out.println("Students scoring above 75%, sorted by marks:");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .map(s -> s.name)
                .forEach(System.out::println);
    }
}
