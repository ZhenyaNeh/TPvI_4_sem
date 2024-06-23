import DAO.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try{
            DAO db = new DAO();
            db.getConnection();

            System.out.println("SELECT №1");
            /* Найти все книги, вышедшие в текущем и прошлом году.*/
            ResultSet resultSet = db.ExecuteQuery("""
                    USE LIBRARY
                    SELECT*
                    FROM USERS""");

            while (resultSet.next()) {
                System.out.print("\t|\t");
                System.out.print(resultSet.getString(1) + "\t|\t");
                System.out.print(resultSet.getString(2) + "\t|\n");
            }

            System.out.println("\nSELECT №2");
            /*Вывести информацию об авторах.*/
            resultSet = db.ExecuteQuery("""
                    USE LIBRARY
                    SELECT AUTHOR.NAME, AUTHOR.COUNTRY, BOOK.NAME_BOOK
                    FROM BOOK
                    inner join AUTHOR ON BOOK.NAME_AUTHOR = AUTHOR.NAME
                    GROUP BY AUTHOR.NAME, AUTHOR.COUNTRY, BOOK.NAME_BOOK""");
            while(resultSet.next()) {
                System.out.print("\t|\t");
                System.out.print(resultSet.getString(1) + "\t|\t");
                System.out.print(resultSet.getString(2) + "\t|\t");
                System.out.print(resultSet.getString(3) + "\t|\n");
            }

            /*Вывести информацию об авторах, написавших как минимум n книг.*/
            System.out.println("\nSELECT №3");
            System.out.println("Prepared statement");
            resultSet = db.ExecutePrepareStatements(2);
            while(resultSet.next()) {
                System.out.print("\t|\t");
                System.out.print(resultSet.getString(1) + "\t|\t");
                System.out.print(resultSet.getString(2) + "\t|\t");
                System.out.print(resultSet.getString(3) + "\t|\n");
            }

            System.out.println("\nSELECT №3");
            System.out.println("Transaction statement");
            /*Удалить все книги, публикация которых была позднее заданного года*/
            db.ExecuteTransaction();
            db.closeConnection();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}