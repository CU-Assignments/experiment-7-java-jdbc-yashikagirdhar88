import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/shop"; 
    static final String USER = "root"; 
    static final String PASSWORD = ""; 

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Insert Product");
                System.out.println("2. Display Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product Name: ");
                        String name = scanner.next();
                        System.out.print("Enter Price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter Quantity: ");
                        int quantity = scanner.nextInt();

                        String insertSQL = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
                        insertStmt.setString(1, name);
                        insertStmt.setDouble(2, price);
                        insertStmt.setInt(3, quantity);
                        insertStmt.executeUpdate();
                        System.out.println("Product Inserted.");
                        break;

                    case 2:
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
                        while (rs.next()) {
                            System.out.println(rs.getInt("ProductID") + " " + rs.getString("ProductName") + " " + rs.getDouble("Price") + " " + rs.getInt("Quantity"));
                        }
                        break;

                    case 3:
                        System.out.print("Enter Product ID to update: ");
                        int updateId = scanner.nextInt();
                        System.out.print("Enter new price: ");
                        double newPrice = scanner.nextDouble();
                        String updateSQL = "UPDATE Product SET Price = ? WHERE ProductID = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
                        updateStmt.setDouble(1, newPrice);
                        updateStmt.setInt(2, updateId);
                        updateStmt.executeUpdate();
                        System.out.println("Product Updated.");
                        break;

                    case 4:
                        System.out.print("Enter Product ID to delete: ");
                        int deleteId = scanner.nextInt();
                        String deleteSQL = "DELETE FROM Product WHERE ProductID = ?";
                        PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);
                        deleteStmt.setInt(1, deleteId);
                        deleteStmt.executeUpdate();
                        System.out.println("Product Deleted.");
                        break;

                    case 5:
                        conn.close();
                        scanner.close();
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
