package main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<UserEntity> getUsers() {
        List<UserEntity> userList = new ArrayList<>();

        try {
            // Загрузка драйвера JDBC для mysql
            Class.forName("com.mysql.jdbc.Driver");

            // Установление соединения с базой данных
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/my_bank", "root", "");

            // Создание объекта Statement
            Statement statement = connection.createStatement();

            // Выполнение SQL-запроса
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_new"); //   в место  селекто инсерт

            // Обработка результатов запроса
            while (resultSet.next()) {
                userList.add(new UserEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("passnum"),
                        resultSet.getLong("balans")
                ));
                System.out.println(resultSet.getString("name"));
            }

            // Закрытие соединения, объекта Statement и объекта ResultSet
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    //todo читаем юзера с бади поста и вставляем в таблицу
    public List<UserEntity> saveUser(String name, String passNum,String balance){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Установление соединения с базой данных
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/my_bank", "root", "");

            // Создание объекта Statement
            Statement statement = connection.createStatement();

            // Выполнение SQL-запроса " + val + "
            statement.executeUpdate("INSERT INTO user_new (name, passnum, balans) VALUES('" + name + "', '" + passNum + "', " + balance + ")");
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return getUsers();
    }
}
