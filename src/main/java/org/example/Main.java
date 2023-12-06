package org.example;

import org.example.usermanagement.User;

public class Main {
    public static void main(String[] args) {

        User user = new User("121e", "1256", "johndoe@example.com");
        user.setPassword("password123");

        // User 객체를 DB에 저장
        user.saveToDB();

        // 저장된 User 객체를 ID를 기준으로 조회
        User loadedUser = User.loadFromDB(1);

        // 조회 결과 출력
        if (loadedUser != null) {
            System.out.println("Loaded User:");
            System.out.println("ID: " + loadedUser.getId());
            System.out.println("Name: " + loadedUser.getPassword());
            System.out.println("Email: " + loadedUser.getEmail());
        } else {
            System.out.println("User not found.");
        }
    }
}