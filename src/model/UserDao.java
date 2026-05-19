package model;

import util.DataConnectionConfigure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> findAll(){
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try(Connection connection = DataConnectionConfigure.getConnection()){
            Statement statement = connection.createStatement();
            boolean isExecuted = statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){;
                int id = resultSet.getInt("id");
                String uuid = resultSet.getString("uuid");
                String userName = resultSet.getString("user_name");
                String email  =resultSet.getString("email");
                String password = resultSet.getString("password");
                String profile  = resultSet.getString("profile");
                User user = new User(id,uuid,userName,email,password,profile);
                // add user object to list
                users.add(user);
            }
        }catch (Exception exception){
            System.out.println("Connection failed");
        }
        return users;
    }

    public int remove(User user) {
        String sql = "DELETE FROM users WHERE uuid=?";
        try (Connection connection = DataConnectionConfigure.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set value for replace ? symbol in SQL Statement
            preparedStatement.setString(1, user.getUuid());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected<=0){
                throw new RuntimeException("Failed to update new data into table users");
            }
        } catch (Exception e) {
            throw new RuntimeException("Connection failed");
        }
        return 1;
    }

    public User update(User user) {
        String sql = """
                 UPDATE users
                SET user_name = ?, email = ?, password = ?, profile = ?
                WHERE uuid = ?
                """;
        try(Connection connection = DataConnectionConfigure.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set value for replace ? symbol in SQL Statement
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getProfile());
            preparedStatement.setString(5,user.getUuid());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected<=0){
                throw new RuntimeException("Failed to update new data into table users");
            }
            return user;
        }catch (Exception exception){
            System.out.println("Error during update user");
        }
        return user;
    }

    public User save(User user){
        String sql = """
                INSERT  INTO users(uuid, user_name, email, password, profile)
                VALUES (?,?,?,?,?)
                """;
        try(Connection connection = DataConnectionConfigure.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set value for replace ? symbol in SQL Statement
            preparedStatement.setString(1,user.getUuid());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getProfile());
            //
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected<=0){
                throw new RuntimeException("Failed to insert new data into table users");
            }
            return user;
        }catch (Exception exception){
            System.out.println("Error during insert new user");
        }
        return null;
    }
    public User searchByName(User user){
        String sql = """
                select * from users
                where user_name = ?
                """;
        try(Connection connection = DataConnectionConfigure.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set value for replace ? symbol in SQL Statement
            preparedStatement.setString(1,user.getName());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected<=0){
                throw new RuntimeException("Failed to update new data into table users");
            }
            return user;
        }catch (Exception exception){
            System.out.println("Error during update user");
        }
        return user;
    }
}
