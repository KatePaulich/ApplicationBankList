package net.proselyte.servletstutorial;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class DB_BankList extends HttpServlet {
    private final String user = "root";
    private final String url = "jdbc:mysql://localhost:3306/Bank_list?useUnicode=true&characterEncoding=UTF-8";
    private final String password = "root";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DB_BankList() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.toString();
        }
    }

    private void closeDB() {
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String selectUser="select * from users";

        String title = "Database";
        String docType = "<!DOCTYPE html>";
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeBatch();
            resultSet = statement.executeQuery(selectUser);

            writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
            writer.println("<h1>Users</h1>");
            writer.println("<br/>");

            while (resultSet.next()) {
                int userId = resultSet.getInt("userid");
                String name = resultSet.getString("nameuser");
                String surName = resultSet.getString("surNameuser");

                writer.println("ID: " + userId + "<br/>");
                writer.println("Name: " + name + "<br/>");
                writer.println("SurName: " + surName + "<br/>");
            }
        } catch (SQLException e) {
        } finally {
            closeDB();
        }

        String selectAccount="select * from account";
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeBatch();
            resultSet = statement.executeQuery(selectAccount);

            writer.println("<h1>Account</h1>");
            writer.println("<br/>");

            while (resultSet.next()) {
                int account = resultSet.getInt("account");
                int userID = resultSet.getInt("userid");

                writer.println("Account: " + account + "<br/>");
                writer.println("User ID: " + userID + "<br/>");
            }
        } catch (SQLException e) {
        } finally {
            closeDB();
        }
        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public int Sum(){
        int sum = 0;
        String selectSum="select sum(account) from account;";
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeBatch();
            resultSet = statement.executeQuery(selectSum);

            while (resultSet.next()) {
                sum = resultSet.getInt("sum(account)");
            }
        } catch (SQLException e) {
        } finally {
            closeDB();
        }
        System.out.println(sum);
        return sum;
    }

    public String RichestUser(){
        int max =0;
        String selectMax="select userid from account where account=(select max(account) from account);";
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeBatch();
            resultSet = statement.executeQuery(selectMax);

            while (resultSet.next()) {
                max = resultSet.getInt("userid");
            }
        } catch (SQLException e) {
        } finally {
            closeDB();
        }
        String richestUser="";
        String selectRichestUser="select nameuser, surNameuser from users where userid = "+ max + ";";
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeBatch();
            resultSet = statement.executeQuery(selectRichestUser);

            while (resultSet.next()) {
                richestUser = resultSet.getString("nameuser") + resultSet.getString("surNameuser");

            }
        } catch (SQLException e) {
        } finally {
            closeDB();
        }

        System.out.println(richestUser);
        return richestUser;
    }
}
