package org.example.ex1;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DAO implements IConnection, IQuery {
    private String url;
    private String user;
    private String password;
    private String driver;
    private Connection con;
    private Statement statement;

    private static DAO instance;
    private DAO(){}

    public static DAO getInstance() {
        if(instance == null){
            instance = new DAO();
            instance.getConnection();
        }

        return instance;
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

   /* public int updateResults(String candidateName) throws SQLException {
        String query = "update Candidates set votes = votes + 1 where last_name = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, candidateName);
        int rowsAffected = statement.executeUpdate();
        statement.close();
        return rowsAffected;
    }*/

    public ResultSet countPeople(String login) throws SQLException {
        String query = "select count(loginPeople)[count] from PEOPLEINFO WHERE loginPeople = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, login);
        ResultSet count = statement.executeQuery();
        return count;
    }

    public int sumPeople(String login) throws SQLException {
        String query = "select sumPeople[count] from PEOPLEINFO WHERE loginPeople = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, login);
        ResultSet count = statement.executeQuery();
        count.next();
        int price =  count.getInt("count");
        return price;
    }


    /*public ArrayList<President> showAll() throws SQLException {
        ArrayList<President> list = new ArrayList<>();
        String query = "select * from Candidates";

        try (ResultSet rs = ExecuteQuery(query)) {
            while (rs.next()) {
                President pr = new President();
                pr.setName(rs.getString("last_name"));
                pr.setGolos(rs.getDouble("votes"));
                list.add(pr);
            }
            //rs.close();
        }

        return list;
    }*/

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
