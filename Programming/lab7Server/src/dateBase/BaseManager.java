package dateBase;

import collection.Coordinates;
import collection.Person;
import collection.Semester;
import collection.StudyGroup;
import util.Answer;
import util.ServerTransport;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BaseManager {

    private static Connection connection;
    private final String DB_URL;
    private String LOGIN = "s291145";
    private String PASS = "******";
    private ServerTransport transport;
    private Map<Integer, StudyGroup> collection;

    public BaseManager(int host, ServerTransport transport,Map<Integer, StudyGroup> collection) throws ClassNotFoundException, SQLException {
        this.DB_URL = "jdbc:postgresql://pg:" + "5432" + "/studs";
        this.transport=transport;
        this.collection=collection;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(DB_URL, LOGIN, PASS);
    }

    public HashMap<String, String> getUsersMap() throws SQLException {
        HashMap<String, String> usersInfo = new HashMap<>();
        String query = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usersInfo.put(resultSet.getString(1), resultSet.getString(2));
        }
        return usersInfo;
    }


    public boolean isUserExist(String login, String password) throws SQLException {
        return (!(getUsersMap().get(login) == null)) && (getUsersMap().get(login).equals(password));
    }

    public boolean addUser(String login, String password) {
        try {
            String query = "INSERT INTO users(login, password) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            transport.send(new Answer("Успех"));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addElement(StudyGroup studyGroup,String login) throws SQLException {
        String query = "INSERT INTO collection(study_name, " +
                "coordinates_x, coordinates_y, create_date, " +
                "students_count, transferred, average, semester, person_name, person_birthday, person_height, person_weight, person_id, owner_log) " +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, studyGroup.getName());
        preparedStatement.setString(2, String.valueOf(studyGroup.getCoordinates().getX()));
        preparedStatement.setString(3, String.valueOf(studyGroup.getCoordinates().getY()));
        preparedStatement.setString(4, studyGroup.getCreationDate());
        preparedStatement.setString(5, String.valueOf(studyGroup.getStudentsCount()));
        preparedStatement.setString(6, String.valueOf(studyGroup.getTransferredStudents()));
        preparedStatement.setString(7, String.valueOf(studyGroup.getAverageMark()));
        preparedStatement.setString(8, studyGroup.getSemesterEnum() == null ? "FIRST" : studyGroup.getSemesterEnum().toString());
        preparedStatement.setString(9, studyGroup.getGroupAdmin().getName());
        preparedStatement.setString(10, studyGroup.getGroupAdmin().getBirthday());
        preparedStatement.setString(11, String.valueOf(studyGroup.getGroupAdmin().getHeight()));
        preparedStatement.setString(12, String.valueOf(studyGroup.getGroupAdmin().getWeight()));
        preparedStatement.setString(13, studyGroup.getGroupAdmin().getPassportID());
        preparedStatement.setString(14, login);
        preparedStatement.executeUpdate();
    }

//    public void writeCollectionToDataBase() throws SQLException {
//        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
//            StudyGroup studyGroup = collection.get(element.getKey());
//            addElement(studyGroup);
//        }
//    }

    public boolean checkUser(String login, int argument) throws SQLException {
        String query = "SELECT * FROM collection WHERE id = ? AND owner_log = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, argument);
        preparedStatement.setString(2, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet.getFetchSize());
        return resultSet.next();
//        Integer answer = resultSet.getInt(1);
//        if (answer==1) {
//            System.out.println(answer);
//            return true;
//        }
//        System.out.println(answer);
//        return false;
    }
    public boolean isStudyOwnedBy(int id, String login) throws SQLException {
        return getCollection().entrySet().stream().
                filter(x -> x.getKey() == id).
                anyMatch(x -> x.getValue().getOwnerLogin().equals(login));

    }

    public void clearUsersTable() {
        try {
            String query = "DELETE FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBase(int choice, String argument, int id) throws SQLException {
        if (choice == 0) {
            String query = "UPDATE collection SET study_name = ? WHERE ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,argument);
            preparedStatement.setInt(   2,id);
            preparedStatement.executeUpdate();
        } else if (choice == 1) {
            String query = "UPDATE collection SET students_count = ? WHERE ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,argument);
            preparedStatement.setInt(   2,id);
            preparedStatement.executeUpdate();
        } else if (choice == 2) {
            String query = "UPDATE collection SET transferred = ? WHERE ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,argument);
            preparedStatement.setInt(   2,id);
            preparedStatement.executeUpdate();
        } else if (choice == 3) {
            String query = "UPDATE collection SET average = ? WHERE ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,argument);
            preparedStatement.setInt(   2,id);
            preparedStatement.executeUpdate();
        }
    }

    public void removeStudy(int id) throws SQLException {
        String query = "DELETE FROM collection WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(   1,id);
        preparedStatement.executeUpdate();
    }

    public void clearStudyTable() {
        try {
            String query = "DELETE FROM collection";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, StudyGroup> getCollection() throws SQLException {
        HashMap<Integer, StudyGroup> map = new HashMap<Integer, StudyGroup>();
        String query = "SELECT * FROM collection";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            float coordinates_x = Float.parseFloat(resultSet.getString(3));
            int coordinates_y = Integer.parseInt(resultSet.getString(4));
            String creationDate = resultSet.getString(5);
            long students_count = Long.parseLong(resultSet.getString(6));
            int transferred = Integer.parseInt(resultSet.getString(7));
            long average = Long.parseLong(resultSet.getString(8));
            Semester semester = Semester.FIRST;
            String person_name = resultSet.getString(10);
            String person_birthday = resultSet.getString(11);
            float person_height = Float.parseFloat(resultSet.getString(12));
            int person_weight = Integer.parseInt(resultSet.getString(13));
            String person_id = resultSet.getString(14);
            Person person = new Person(person_name,person_birthday,person_height,person_weight,person_id);
            Coordinates coordinates = new Coordinates(coordinates_x,coordinates_y);
            String owner_log = resultSet.getString(15);
            StudyGroup studyGroup = new StudyGroup(id, name, coordinates, creationDate, students_count, transferred, average,semester,person,owner_log);
            map.put(id,studyGroup);
        }
        return map;
    }

    public boolean removeUser(String login) throws SQLException {
        boolean isUserExist = isUserExist(login);
        String query = "DELETE FROM users WHERE login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);
        preparedStatement.executeUpdate();
        return isUserExist;
    }

    public boolean isUserExist(String login) throws SQLException {
        return (!(getUsersMap().get(login) == null));
    }

//    public void removeStudy(int id) throws SQLException {
//        boolean b = isStudyExist(id);
//        String query = "DELETE FROM collection WHERE id = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1, id);
//        preparedStatement.executeUpdate();
//    }
}
