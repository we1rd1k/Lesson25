import DB.Props;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static DB.DBConnect.*;

public class ConnectionTest {

    static Props props = ConfigFactory.create(Props.class);


    public static void main(String[] args) throws SQLException {
        Connection connection = dbConnection(props.url(), props.user(), props.password());
        String addCustomerQuery = "INSERT INTO customers (id, name, age) " +
                "VALUES (?, ?, ?)";
        String query = "SELECT * FROM customers;";
        insertData(connection, addCustomerQuery);
        getDataFromTable(connection, query);
        getFuncData(connection, "MAX");
        getFuncData(connection, "MIN");
        getFuncData(connection, "AVG");

    }


    private Connection connection;

    @BeforeEach
    public void init() throws SQLException {
        connection = dbConnection(props.url(), props.user(), props.password());
    }

    @AfterEach
    public void close() throws SQLException {
        connection.close();
    }

    @Test
    public void jdbcConnectionTest() throws SQLException {
        String customerTableQuery = "CREATE TABLE customers " +
                "(id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
        String customerEntryQuery = "INSERT INTO customers " +
                "VALUES (75, 'Fred', 37)";
        executeUpdate(connection, customerTableQuery);
        executeUpdate(connection, customerEntryQuery);
    }
}
