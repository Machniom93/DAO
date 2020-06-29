package pl.codcerslab.entity;

import pl.codcerslab.auxiliary.BCrypt;
import pl.codcerslab.auxiliary.DbUtil;

import java.sql.*;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT username, email, password FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?;";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";

    public User create(User user) {
       try (Connection conn = DbUtil.getConnection()) {
           PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
           statement.setString(1, user.getUserName());
           statement.setString(2, user.getEmail());
           statement.setString(3, hashPassword(user.getPassword()));
           statement.executeUpdate();
           ResultSet resultSet = statement.getGeneratedKeys();
           if (resultSet.next()) {
               user.setId(resultSet.getInt(1));
           }
           return user;
       } catch (SQLException e) {
           e.printStackTrace();
           return null;
       }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User read(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_USER_QUERY);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
