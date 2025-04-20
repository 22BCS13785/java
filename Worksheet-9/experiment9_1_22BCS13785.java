// Create a simple Spring application that demonstrates Dependency Injection (DI) using Java-based configuration instead of XML. 
// Define a Student class that depends on a Course class. Use Spring’s @Configuration and @Bean annotations to inject dependencies.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class Course {
    private String courseName;
    private String duration;

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Course [courseName=" + courseName + ", duration=" + duration + "]";
    }
}

class Student {
    private String name;
    private Course course;

    public Student(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", course=" + course + "]";
    }
}

@Configuration
class AppConfig {
    @Bean
    public Course course() {
        return new Course("Spring Framework", "3 months");
    }

    @Bean
    public Student student() {
        return new Student("John Doe", course());
    }
}

public class experiment9_1_22BCS13785 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = context.getBean(Student.class);
        System.out.println(student);

        context.close();
    }
}
