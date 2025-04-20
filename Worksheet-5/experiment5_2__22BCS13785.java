// Create a Java program to serialize and deserialize a Student object. The program should:
// Serialize a Student object (containing id, name, and GPA) and save it to a file.
// Deserialize the object from the file and display the student details.
// Handle FileNotFoundException, IOException, and ClassNotFoundException using exception handling.



import java.io.*;

class Studs implements Serializable {
    private static final long serialVersionUID = 1L; 
    private int id;
    private String name;
    private double gpa;
    
    public Studs(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
    
    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("CGPA: " + gpa);
    }
}

public class experiment5_2__22BCS13785 {
    private static final String FILE_NAME = "student.ser"; 
    
    public static void serializeStudent(Studs student) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(student);
            System.out.println("Student object serialized successfully!");
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        } catch (IOException e) {
            System.err.println("Error: Unable to serialize object.");
        }
    }
    
    public static Studs deserializeStudent() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Studs) ois.readObject(); 
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        } catch (IOException e) {
            System.err.println("Error: Unable to deserialize object.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Studs class not found.");
        }
        return null;
    }
    
    public static void main(String[] args) {
        Studs student = new Studs(189, "Hemang Joshi", 3.2);
        serializeStudent(student);
        
        Studs deserializedStudent = deserializeStudent();
        if (deserializedStudent != null) {
            System.out.println("\nDeserialized Student Details:");
            deserializedStudent.display();
        }
    }
}
