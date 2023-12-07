package org.example;

import org.example.usermanagement.User;
import org.example.usermanagement.UserManager;

public class Main {
    public static void main(String[] args) {

//        User user = new User("test3", "45167", "test@example.com");
//
//        // User 객체를 DB에 저장
//        user.saveToDB();
//
//        // 저장된 User 객체를 ID를 기준으로 조회
//        User loadedUser = User.loadFromDB("test3");
//
//        // 조회 결과 출력
//        if (loadedUser != null) {
//            System.out.println("Loaded User:");
//            System.out.println("ID: " + loadedUser.getId());
//            System.out.println("Password: " + loadedUser.getPassword());
//            System.out.println("Email: " + loadedUser.getEmail());
//        } else {
//            System.out.println("User not found.");
//        }


        UserManager manager = new UserManager("root", "0000");

       if (manager.loginUser("abcd","12345") == true){
           System.out.println("로그인 성공");
       }else {
           System.out.println("로그인 실패");
       }

    }
}