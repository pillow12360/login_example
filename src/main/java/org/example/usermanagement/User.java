package org.example.usermanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String id;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    // User 객체를 DB에 저장하는 메서드
    public void saveToDB() {
        String url = "jdbc:mysql://localhost/user_management";
        String username = "root";
        String password1 = "0000";

        try (Connection conn = DriverManager.getConnection(url, username, password1)) {
            String query = "INSERT INTO users (id, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ID를 기준으로 DB에서 User 객체를 조회하는 메서드
    public static User loadFromDB(String id) {
        User user = null;

        String url = "jdbc:mysql://localhost/user_management";
        String username = "root";
        String password = "0000";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT id, password, email FROM users WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userId = resultSet.getString("id");
                String userPassword = resultSet.getString("password");
                String userEmail = resultSet.getString("email");
                user = new User(userId, userPassword, userEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
