package com.motionCBSTest.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.motionCBSTest.client.rpc.MotionCBSTestService;
import com.motionCBSTest.shared.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotionCBSTestServiceImpl extends RemoteServiceServlet implements MotionCBSTestService {

    /*
     * The url, username and password for the database. The password is not necessarily
     * the same pass as your computer password
     */
    //private static final String JDBC_DRIVER = "com.mysql.jdcb.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/motioncbs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Esv23cfi";

    private static Connection connection = null;

    /**
     * The constructor which is creating the connection the the database
     */
    public MotionCBSTestServiceImpl() {
        try {
           // Class.forName("com.mysql.jdcb.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    private static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void test() throws IllegalArgumentException {
        System.out.println("Server/Client connection is fine");
    }

    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public User authorizeUser(String mobileNr, String password) {
        // By making a SELECT/executeQuery to the database the data will presented/saved in a ResultSet
        ResultSet resultSet = null;
        User user = null;

        // Using a catch since a query to the database can fail
        try {

            /*
             * The PreparedStatement which is used to make authorize a user.
             * This statement will return all users which both have a specific username and password in combination
             * The username and password is set later in this method
             */
            PreparedStatement authorizeUser = connection
                    .prepareStatement("SELECT * FROM users where mobilenr = ? AND password = ?");
            /*
             * In the next two lines the username and password is set.
             * The 1 is referring to the first question mark and the 2 is referring to the second question mark
             */
            authorizeUser.setString(1, mobileNr);
            authorizeUser.setString(2, password);

            // The PreparedStatement is executed and data is loaded into a resultSet
            resultSet = authorizeUser.executeQuery();

            // This loop will create and set a user if anyone found and there by returned in the resultSet
            while (resultSet.next()) {
                user = new User();
                user.setMobilenr(resultSet.getString("mobileNr"));
                user.setPassword(resultSet.getString("password"));
                user.setFname(resultSet.getString("firstname"));
                user.setLname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setEducation(resultSet.getString("education"));
                user.setExperience(resultSet.getInt("experience"));
                user.setHoursPrWeek(resultSet.getInt("hoursPrWeek"));
                user.setTeamtype(resultSet.getString("teamtype_teamID"));
                user.setType(resultSet.getInt("type"));
                user.setId(resultSet.getInt("TrainerID"));
            }
            // The catch which is used if either the statement or connection is failing
        } catch (SQLException e) {
            e.printStackTrace();
            // A finally try catch which closes the result set and it can't then close the db connection
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return user;
}
    @Override
    public ArrayList<User> getUsers(int userId) throws IllegalArgumentException {
        ResultSet resultSet = null;
        ArrayList<User> users = new ArrayList<>();

        try {
            // Same concept as getMessages method except there is no join in this statement
            PreparedStatement getUsers = connection.prepareStatement("SELECT trainerID, firstname, lastname, mobileNr, teamName FROM users " +
                    "INNER JOIN teamtype on teamtype_teamID = teamID WHERE type != 1;");
            //getUsers.setInt(1, userId);
            resultSet = getUsers.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("TrainerID"));
                user.setMobilenr(resultSet.getString("mobileNr"));
                user.setFname(resultSet.getString("firstname"));
                user.setLname(resultSet.getString("lastname"));
                user.setTeamName(resultSet.getString("teamName"));

                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return users;
    }

    // Her oprettes createUser
    @Override
    public boolean createUser(User user) throws IllegalArgumentException {
        try {
            // Same concept as createMessage method
            PreparedStatement createUser = connection
                  .prepareStatement("INSERT INTO users (firstname, lastname, email, adress, mobilenr, education," +
                            "experience, hoursprweek, password, isapproved, teamtype_teamID, type) " +
                          "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            createUser.setString(1, user.getFname());
            createUser.setString(2, user.getLname());
            createUser.setString(3, user.getEmail());
            createUser.setString(4, user.getAddress());
            createUser.setString(5, user.getMobilenr());
            createUser.setString(6, user.getEducation());
            createUser.setInt(7, user.getExperience());
            createUser.setInt(8, user.getHoursPrWeek());
            createUser.setString(9, user.getPassword());
            createUser.setBoolean(10, user.getIsApproved());
            createUser.setString(11, user.getTeamtype());
            createUser.setInt(12, user.getType());

            //
            int rowsAffected = createUser.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}