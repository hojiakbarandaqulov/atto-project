package org.example.controller;

import lombok.Setter;
import org.example.repository.TransactionRepository;
import org.example.service.CardService;
import org.example.service.ProfileCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
@Setter
@Controller
public class UserController {
    @Autowired
    private ProfileCardService profileCardService;
    @Autowired
    private TransactionRepository transaction;
    @Autowired
    private CardService cardService;
    private Scanner scanner =new Scanner(System.in);

//    public UserController() {
//        scanner=new Scanner(System.in);
//    }

    public void start() {
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1 -> {
                    System.out.println("ywy");
                    profileCardService.addProfile();
                }
                case 2 -> {
                    System.out.println("qq");
                    profileCardService.ProfileCardList();
                }
                case 3 -> {
                    System.out.println("3");
                    profileCardService.changeProfileCardService();
                }
                case 4 -> {
                    System.out.println("4");
                    profileCardService.ProfileCardDelete();
                }
                case 5 -> reefCard();
                case 6 -> {
                    System.out.println("6");
                    transaction.viewLastTransaction(cardNumber());
                }
                case 0 -> {
                    System.out.println("Exit");
                    b = false;
                }
                default -> b = false;
            }
        }
    }
    private int cardNumber(){
        System.out.print("Enter card number: ");
        Integer cardNumber=scanner.nextInt();
        return cardNumber;
    }
    private void reefCard() {
        System.out.println("Enter card number:");
        Integer number = scanner.nextInt();
        System.out.println("Enter balance: ");
        Double balance=scanner.nextDouble();
        cardService.ReFill(number,balance);
    }
    public void showMenu() {
        System.out.println("*** User Menu *** ");
        System.out.println("1. Add Card");
        System.out.println("2. Card List");
        System.out.println("3. Card Change Status");
        System.out.println("4. Delete Card");
        System.out.println("5. Refill");
        System.out.println("6. Transaction");
        System.out.println("0. Exit Profile");
    }
    public int getAction() {
        System.out.print("Enter action: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
