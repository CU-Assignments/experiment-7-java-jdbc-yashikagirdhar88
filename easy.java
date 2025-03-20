import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDataFetcher {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/company"; 
        String user = "root"; 
        String password = "@Pcjha870"; 

     
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM Employee";

            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("EmpID\tName\tSalary");
            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.println(empId + "\t" + name + "\t" + salary);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
