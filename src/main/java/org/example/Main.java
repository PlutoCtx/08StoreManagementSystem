package org.example;

import org.example.view.Login;
import org.example.view.MainFrame;

import java.awt.*;

public class Main {
//    public static void main(String[] args) {
////        System.out.println("Hello world!");
//        new MainFrame().setVisible(true);
//
//
//    }


    /**
     * Launch the application
     * @param args ignored
     */
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }

}