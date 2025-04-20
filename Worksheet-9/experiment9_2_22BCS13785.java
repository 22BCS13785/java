// Develop a Hibernate-based application to perform CRUD (Create, Read, Update, Delete) 
// operations on a Student entity using Hibernate ORM with MySQL.

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.hibernate.mapping.PersistentClass;

import javax.persistence.*;

@Entity
@Table(name = "student")
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    public Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}

public class experiment9_2_22BCS13785 {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Student student = new Student("Alice", 22);

            // Start a transaction
            session.beginTransaction();

            // Save the student object
            session.save(student);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Saved student: " + student);

            // Now retrieve the student based on ID
            session = factory.getCurrentSession();
            session.beginTransaction();

            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Retrieved student: " + retrievedStudent);

            // Update the student's name
            retrievedStudent.setName("Alice Updated");
            session.update(retrievedStudent);

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            session.delete(retrievedStudent);
            session.getTransaction().commit();

            System.out.println("Deleted student");

        } finally {
            factory.close();
        }
    }
}
