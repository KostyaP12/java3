package main.java.lesson_b;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();
            createTable();
            addRecord("Bob"); // String Name
            show();
            deleteWhereId(15); // Int ID
            dropTable();
            disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void connect () throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:java3_lesson2.db");
        statement = connection.createStatement();
    }

    private static void disconnect() throws Exception {
        connection.close();
    }

    private static void createTable () throws Exception{
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS WorkTable\n" +
                "(\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "  Name STRING NOT NULL\n" +
                ");");
    }

    private static void addRecord (String name) throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO WorkTable (Name) values (?);");
        preparedStatement.setString(1, name);
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }

    private static void show () throws Exception{
        ResultSet rs = statement.executeQuery("SELECT * FROM WorkTable");
        while (rs.next()){
            System.out.println(rs.getString("Name"));
        }
    }

    private static void deleteWhereId(int Id)throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM WorkTable WHERE ID =(?)");
        preparedStatement.setInt(1, Id);
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
    }

    private static void dropTable() throws Exception{
        statement.executeUpdate("DROP TABLE WorkTable");
    }
}

