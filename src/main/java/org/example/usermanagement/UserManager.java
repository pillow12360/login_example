package org.example.usermanagement;

import java.sql.*;

public class UserManager {
    private Connection connection;

    public UserManager(String id, String pw) {
        try {
            // 데이터베이스 연결
            connection = DriverManager.getConnection("jdbc:mysql://localhost/user_management", id, pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(User user) {
        try {
            // 회원 정보 유효성 검사
            if (isUserExists(user.getId())) {
                System.out.println("이미 존재하는 아이디입니다.");
                return false;
            }

            // 회원 가입 처리
            String sql = "INSERT INTO users (id, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();

            System.out.println("회원 가입이 완료되었습니다.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean loginUser(String id, String password) {
        try {
            // 아이디와 비밀번호 검증
            String sql = "SELECT * FROM users WHERE id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("로그인 성공");
                return true;
            }

            System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void logoutUser() {
        // 로그아웃 처리
        System.out.println("로그아웃 되었습니다.");
    }

    private boolean isUserExists(String id) {
        try {
            // 아이디 중복 검사
            String sql = "SELECT COUNT(*) FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
