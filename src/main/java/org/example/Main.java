package org.example;

import org.example.usermanagement.User;

public class Main {
    public static void main(String[] args) {

        User user = new User("test3", "45167", "test@example.com");

        // User 객체를 DB에 저장
        user.saveToDB();

        // 저장된 User 객체를 ID를 기준으로 조회
        User loadedUser = User.loadFromDB("test3");

        // 조회 결과 출력
        if (loadedUser != null) {
            System.out.println("Loaded User:");
            System.out.println("ID: " + loadedUser.getId());
            System.out.println("Password: " + loadedUser.getPassword());
            System.out.println("Email: " + loadedUser.getEmail());
        } else {
            System.out.println("User not found.");
        }
    }
}