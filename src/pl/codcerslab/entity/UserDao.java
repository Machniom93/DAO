package pl.codcerslab.entity;

import pl.codcerslab.auxiliary.BCrypt;
import pl.codcerslab.auxiliary.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?;";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USER_QUERY = "SELECT * FROM users";

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
                user.setId(resultSet.getInt("id"));
            } else {
                return null;
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] findAll() {
        try (Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USER_QUERY);
            User[] users = new User[0];
            while (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
                users = addToArray(user,users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

}
