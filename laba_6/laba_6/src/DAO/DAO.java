package DAO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DAO implements IConnection, IQuery{
    static {
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }

    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public void logInfo() {
        LOGGER.info("Started main");
    }

    public DAO() {
        logInfo();
        getProperties();
    }

    public List<String> getProperties(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

        url = resourceBundle.getString("db.url");
        user = resourceBundle.getString("db.username");
        password = resourceBundle.getString("db.password");

        List<String> ret = new ArrayList<>();

        ret.add(url);
        ret.add(user);
        ret.add(password);

        return ret;
    }

    public Boolean getConnection() {
        try {
            LOGGER.debug("Connecting...");
            getProperties();
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            LOGGER.info("Connected!");
            return true;
        }
        catch(SQLException ex) {
            LOGGER.error("ERROR Connect!");
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
            LOGGER.error("ERROR Query!");
            return null;
        }
    }

    public ResultSet ExecutePrepareStatements (int numberOfSatellites){
        try{
            String sqlQuery = """
                    USE LIBRARY
                    SELECT AUTHOR.NAME, AUTHOR.COUNTRY, count(BOOK.NAME_BOOK)[COUNT_BOOK]
                    FROM BOOK
                    inner join AUTHOR ON BOOK.NAME_AUTHOR = AUTHOR.NAME
                    GROUP BY AUTHOR.NAME, AUTHOR.COUNTRY
                    HAVING count(BOOK.NAME_BOOK) >= ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, numberOfSatellites);
            return preparedStatement.executeQuery();
        }
        catch (SQLException ex){
            ex.printStackTrace();
            LOGGER.error("ERROR Query!");
            return null;
        }
    }

    public void ExecuteTransaction() {
        try {
            try {
                connection.setAutoCommit(false);
                {
                    statement.executeUpdate("UPDATE BOOK SET YEAR_OF_RELEASE = 2009 WHERE NAME_BOOK = 'book_1'");
                    statement.executeUpdate("UPDATE BOOK SET YEAR_OF_RELEASE = 2009 WHERE NAME_BOOK = 'book_3'");
                    statement.executeUpdate("DELETE FROM BOOK WHERE YEAR_OF_RELEASE >= 2009");
                }
                ResultSet resultSet = statement.executeQuery("SELECT NAME_BOOK, YEAR_OF_RELEASE FROM BOOK");

                System.out.println("\nBefore..");
                while(resultSet.next()) {
                    System.out.print("\t|\t");
                    System.out.print(resultSet.getString(1) + "\t|\t");
                    System.out.print(resultSet.getString(2) + "\t|\n");
                }
                connection.rollback();

                System.out.println("\nAfter...");
                resultSet = statement.executeQuery("SELECT NAME_BOOK, YEAR_OF_RELEASE FROM BOOK");
                while(resultSet.next()) {
                    System.out.print("\t|\t");
                    System.out.print(resultSet.getString(1) + "\t|\t");
                    System.out.print(resultSet.getString(2) + "\t|\n");
                }
                connection.commit();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                LOGGER.error("Transaction ERROR!");
                connection.rollback();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.error("Transaction ERROR!");
        }
    }

    public void closeConnection() {
       try{
           connection.close();
           LOGGER.info("Connection is Closed");
       }
       catch (Exception ex){
           ex.printStackTrace();
           LOGGER.error("ERROR when Closing the connection");
       }
    }


}
