/*import java.util.List;

public class StudentView {
    public void displayStudent(Student student) {
        System.out.println("StudentID: " + student.getStudentID());
        System.out.println("Name: " + student.getName());
        System.out.println("Department: " + student.getDepartment());
        System.out.println("Marks: " + student.getMarks());
        System.out.println("-----------------------------");
    }

    public void displayAllStudents(List<Student> students) {
        for (Student student : students) {
            displayStudent(student);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
//
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentController {
    private static final String URL = "jdbc:mysql://localhost:3306/company"; 
    private static final String USER = "root";
    private static final String PASSWORD = "@Pcjha870";

    private StudentView view = new StudentView();
    private Connection conn;

    public StudentController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(Student student) {
        try {
            String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student.getStudentID());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getDepartment());
            stmt.setDouble(4, student.getMarks());
            stmt.executeUpdate();
            view.showMessage("Student Inserted Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Student";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"),
                        rs.getString("Department"), rs.getDouble("Marks")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void updateStudentMarks(int studentID, double newMarks) {
        try {
            String sql = "UPDATE Student SET Marks = ? WHERE StudentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, newMarks);
            stmt.setInt(2, studentID);
            stmt.executeUpdate();
            view.showMessage("Student Marks Updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentID) {
        try {
            String sql = "DELETE FROM Student WHERE StudentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);
            stmt.executeUpdate();
            view.showMessage("Student Deleted Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentController controller = new StudentController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Department: ");
                    String dept = scanner.next();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    controller.insertStudent(new Student(id, name, dept, marks));
                    break;

                case 2:
                    List<Student> students = controller.getAllStudents();
                    controller.view.displayAllStudents(students);
                    break;

                case 3:
                    System.out.print("Enter Student ID to Update Marks: ");
                    int updateID = scanner.nextInt();
                    System.out.print("Enter New Marks: ");
                    double newMarks = scanner.nextDouble();
                    controller.updateStudentMarks(updateID, newMarks);
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int deleteID = scanner.nextInt();
                    controller.deleteStudent(deleteID);
                    break;

                case 5:
                    controller.closeConnection();
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}
//
public class Student {
    private int studentID;
    private String name;
    private String department;
    private double marks;

    // Constructor
    public Student(int studentID, String name, String department, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    // Getters and Setters
    public int getStudentID() { return studentID; }
    public void setStudentID(int studentID) { this.studentID = studentID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }
}

