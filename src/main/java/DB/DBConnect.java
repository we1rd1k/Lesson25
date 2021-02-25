package DB;

import java.sql.*;
import java.util.Scanner;

public class DBConnect {


    public static Connection dbConnection(String url, String user, String pass) throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public static int executeUpdate(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(query);
        return result;
    }

    public static int insertData(Connection connection, String statement) throws SQLException {
        PreparedStatement pr = connection.prepareStatement(statement);
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите id: ");
        Integer id = sc.nextInt();
        System.out.print("Введите name: ");
        String name = sc.next();
        System.out.print("Введите age: ");
        Integer age = sc.nextInt();
        pr.setInt(1, id);
        pr.setString(2, name);
        pr.setInt(3, age);
        return pr.executeUpdate();
    }

    public static void getDataFromTable(Connection connection, String query) throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
        }
    }

    public static void getFuncData(Connection connection, String func) throws SQLException {
        Statement st = connection.createStatement();
        String string = "SELECT " + func + "(age) as" + "\"" + func + "\"" + " FROM customers;";
        ResultSet rs = st.executeQuery(string);
        rs.next();
        System.out.println(func + " age:" + rs.getInt(func));
    }
}
