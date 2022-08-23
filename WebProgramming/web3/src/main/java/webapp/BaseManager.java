package webapp;

import java.sql.*;

public class BaseManager {

    private static Connection connection;
    private final String DB_URL;
//    private static String URL= "jdbc:postgresql://localhost:11902/studs";
    private static String URL= "jdbc:postgresql://pg:5432/studs";
    private String LOGIN = "s291145";
    private String PASS = "******";

    public BaseManager() throws ClassNotFoundException, SQLException {
        this.DB_URL = URL;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
    }

    public void addUser(double x, double y, double r, String time, String result) {
        try {
            String query = "INSERT INTO web3(x_value, y_value, r_value, req_time, result) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(x));
            preparedStatement.setString(2, String.valueOf(y));
            preparedStatement.setString(3, String.valueOf(r));
            preparedStatement.setString(4, result);
            preparedStatement.setString(5, time);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        try {
            String query = "DELETE FROM web3";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
