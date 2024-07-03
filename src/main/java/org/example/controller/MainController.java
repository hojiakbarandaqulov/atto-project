package org.example.controller;

import lombok.Setter;
import org.example.db.DatabaseUtil;
import org.example.dto.Profile;
import org.example.db.DatabaseUtil;
import org.example.repository.CardRepository;
import org.example.repository.ProfileRepository;
import org.example.service.ProfileService;
import org.example.service.TerminalService;
import org.example.service.TransactionService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Scope("singleton")
public class MainController implements InitializingBean {
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    private ProfileService profileService;
    @Autowired(required = false)
    private TransactionService transactionService;

//    public MainController(ProfileService profileService, TransactionService transactionService) {
//        this.profileService = profileService;
//        this.transactionService = transactionService;
//    }
    public void  start() {
        DatabaseUtil.createTable();
        DatabaseUtil.initAdmin();
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1 -> login();
                case 2 -> registerUser();
                case 3 -> makePayment();
                case 0 -> {
                    System.out.println("Exit");
                    b = false;
                }
                default -> b = false;
            }
        }
    }

    private void registerUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Profile profile = new Profile();
        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        profile.setPassword(password);
        profileService.create(profile);
    }

    private void login() {
        System.out.print("Enter phone: ");
        String phone = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
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

    public void makePayment() {
        System.out.print("Enter card number:");
        String cardNumber = scanner.next();

        System.out.print("Enter terminal code:");
        String terminalCode = scanner.next();

        transactionService.payment(cardNumber, terminalCode);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.print("mainController ");
    }
}
