import controller.AdminController;
import controller.MainController;
import controller.TerminalController;
import controller.UserController;
import db.DatabaseUtil;
import repository.UserRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatabaseUtil.initAdmin();
        DatabaseUtil.createTable();
        boolean b = true;
        while (b) {
            menu();
            switch (getAction()) {
                case 1:
                    MainController mainController = new MainController();
                    mainController.start();
                    break;
                case 2:
                    UserController userController = new UserController();
                    userController.start();
            }
        }
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