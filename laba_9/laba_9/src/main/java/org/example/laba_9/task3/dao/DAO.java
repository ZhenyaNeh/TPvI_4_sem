package org.example.laba_9.task3.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DAO implements IConnection, IQuery {
    private String url;
    private String user;
    private String password;
    private String driver;
    private Connection con;
    private Statement statement;

    public DAO() {
    }

    public ArrayList<String> getProperties() {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        url = resourceBundle.getString("db.url");
        user = resourceBundle.getString("db.username");
        password = resourceBundle.getString("db.password");
        driver = resourceBundle.getString("db.driver");
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Driver class is missing in classpath", e);
        }

        ArrayList<String> ret = new ArrayList<>();
        ret.add(url);
        ret.add(user);
        ret.add(password);
        return ret;
    }
    public Boolean getConnection() {
        try {
            getProperties();
            con = DriverManager.getConnection(url, user, password);
            statement = con.createStatement();
            return true;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ResultSet ExecuteQuery(String sqlQuery) {
        try {
            return statement.executeQuery(sqlQuery);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public void addUser(String login, String password) throws SQLException {
        String query = "insert into USERS (USER_LOG, USER_PAS, USER_ROL) values (?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, login);
        statement.setBytes(2, HashPassword.getHash(password));
        statement.setString(3, "user");
        statement.executeUpdate();
        statement.close();
    }
    public ResultSet checkUser(String login) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from USERS where USER_LOG = ?");
        statement.setString(1, login);
        return statement.executeQuery();
    }
    public ResultSet checkUsersCount(String login, String password) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "select count(*)[count] from USERS where USER_LOG = ? and USER_PAS = ?");
        statement.setString(1, login);
        statement.setBytes(2, HashPassword.getHash(password));
        return statement.executeQuery();
    }
    public void closeConnection()
    {
        try {
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
