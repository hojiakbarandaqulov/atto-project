package org.example;

import org.example.config.Spring_Config;
import org.example.controller.MainController;
import org.example.controller.UserController;
import org.example.db.DatabaseUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseUtil.initAdmin();
        DatabaseUtil.createTable();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Spring_Config.class);
        MainController mainController = (MainController) applicationContext.getBean("mainController");
        mainController.start();
    }


    public static void menu() {
        System.out.println("*** Menu ***");
        System.out.println("1. Main Controller");
        System.out.println("2. User Controller");
    }

    public static int getAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter action: ");
        int action = scanner.nextInt();
        return action;
    }

}