package controller;

import db.DatabaseUtil;
import dto.Profile;
import enums.ProfileStatus;
import service.ProfileService;

import java.util.Scanner;

public class MainController {
    private Scanner scannerStr;
    private ProfileService profileService;
    private Scanner scannerInt;

    public MainController() {
        scannerInt = new Scanner(System.in);
        scannerStr = new Scanner(System.in);
        profileService = new ProfileService();
    }

    public void start() {
        DatabaseUtil.createTable();
        DatabaseUtil.initAdmin();
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:
                    login();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
//                    makePayment();
                    break;
                case 0:
                    System.out.println("Exit");
                    b = false;
                    break;
                default:
                    b = false;
            }
        }
    }
    private void registerUser() {
        System.out.print("Enter name: ");
        String name = scannerStr.nextLine();
        System.out.print("Enter Surname: ");
        String surname = scannerStr.nextLine();
        System.out.print("Enter phone: ");
        String phone = scannerStr.nextLine();
        System.out.print("Enter password: ");
        String password = scannerStr.nextLine();

        Profile profile = new Profile();
        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        profile.setPassword(password);
        profileService.create(profile);
    }

    private void login() {
        System.out.print("Enter phone: ");
        String phone = scannerStr.next();
        System.out.print("Enter password: ");
        String password = scannerStr.next();
        profileService.login(phone, password);
    }

    public void showMenu() {
        System.out.println("*** Menu *** ");
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("3. Make Payment");
        System.out.println("0. Exit");
    }

    public int getAction() {
        System.out.print("Enter action: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }
//    public void makePayment() {
//        System.out.print("Enter card number:");
//        String cardNumber = scannerStr.next();
//
//        System.out.print("Enter terminal code:");
//        String terminalCode = scannerStr.next();
//
//        transactionService.payment(cardNumber, terminalCode);
//    }
}
