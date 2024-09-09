import java.sql.*;
import java.util.Scanner;

public class Renovation {

    static Scanner sc = new Scanner(System.in);

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/student";
        String userName = "root";
        String password = "jyoti";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection established successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to connect with your database!");
            e.printStackTrace();
        }

        return conn;
    }

    static int menu() {
        System.out.println("1. Add student");
        System.out.println("2. View student");
        System.out.println("3. Update student");
        System.out.println("4. Delete student");
        System.out.println("5. View all student");
        System.out.println("6. Exit student");
        System.out.print("Select an option: ");
        return sc.nextInt();
    }

    static void addStudent() {
        System.out.println("Adding a new student!");
        System.out.print("Please enter the roll number of the student: ");
        int roll = Integer.parseInt(sc.next());
        System.out.print("Enter the name of the student: ");
        String name = sc.next();
        System.out.print("Enter the age of the student: ");
        int age = Integer.parseInt(sc.next());
        System.out.print("Enter contact no of the student: ");
        String contact = sc.next();
        String query = "INSERT into hostel values(" + roll + ",'" + name + "'," + age + ",'" + contact + "');";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void viewStudent() {
        System.out.println("Viewing a student!");
        System.out.print("Please enter the roll number you want to view: ");
        int roll = Integer.parseInt(sc.next());
        String query = "SELECT * from hostel where roll=" + roll + ";";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println("Roll no= " + rs.getInt("roll"));
                System.out.println("Student name= " + rs.getString("sName"));
                System.out.println("Student age= " + rs.getInt("age"));
                System.out.println("----------------------------------------------------");
            } else {
                System.out.println("Student not found!");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    static void viewAll() {
        String query = "SELECT * from hostel;";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("\nStudent roll= " + rs.getInt("roll"));
                System.out.println("Student name= " + rs.getString("sName"));
                System.out.println("Student age= " + rs.getInt("age"));
                System.out.println("Student contact= " + rs.getString("contactNo"));
                System.out.println("----------------------------------------------------");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Viewing all student!");

    }


    static void updateStudent() {
        System.out.println("updating a student!");
        System.out.print("Please enter the roll no of the student: ");
        int roll = Integer.parseInt(sc.next());
        System.out.print("Enter the updated name: ");
        String name = sc.next();
        System.out.print("Enter the age of the student: ");
        int age = Integer.parseInt(sc.next());
        System.out.print("Enter new contact for the student: ");
        String contact = sc.next();

        String query = "UPDATE hostel set sName='" + name + "', age=" + age + ",contactNo='" + contact + "' where roll=+" + roll + ";";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) affected!");
            } else {
                System.out.println("0 row affected!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void removeStudent() {
        System.out.println("Deleting a student!");
        System.out.print("Please enter the roll no of the student: ");
        int roll = Integer.parseInt(sc.next());
        String query = "DELETE from hostel where roll=" + roll + ";";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) affected!");
            } else {
                System.out.println("0 row affected!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println("HOSTEL MANAGEMENT SYSTEM");
        while (true) {
            int input = menu();
            try {
                Connection conn = getConnection();
                switch (input) {
                    case 1: {
                        System.out.println("You have chose add a new student: ");
                        addStudent();
                        break;
                    }
                    case 2: {
                        viewStudent();
                        break;
                    }
                    case 3: {
                        updateStudent();
                        break;
                    }
                    case 4: {
                        removeStudent();
                        break;
                    }
                    case 5: {
                        viewAll();
                        break;
                    }
                    default: {
                        System.exit(0);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
