package controller;

import container.ComponentContainer;
import dto.Profile;
import repository.TerminalRepository;
import repository.Transaction;
import repository.UserRepository;
import service.CardService;
import service.ProfileCardService;

import java.util.Scanner;

public class UserController {
    private ProfileCardService profileCardService;
    private Transaction transaction;
    private CardService cardService;
    private Scanner scannerInt ;
    private Scanner scannerStr ;

    public UserController() {
        profileCardService =new ProfileCardService();
        cardService=new CardService();
        scannerInt=new Scanner(System.in);
        scannerStr=new Scanner(System.in);
    }

    public void start() {
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:
                    System.out.println("ywy");
                    profileCardService.addProfile();
                    break;
                case 2:
                    System.out.println("qq");
                    profileCardService.ProfileCardList();
                    break;
                case 3:
                    System.out.println("3");
                    profileCardService.changeProfileCardService();
                    break;
                case 4:
                    System.out.println("4");
                    profileCardService.ProfileCardDelete();
                    break;
                case 5:
//                    reefCard(ComponentContainer.currentProfile.getId());
                    reefCard();
                    break;
                case 6:
                    System.out.println("6");
                    transaction.viewLastTransaction(cardNumber());
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
    private int cardNumber(){
        System.out.print("Enter card number: ");
        Integer cardNumber=scannerInt.nextInt();
        return cardNumber;
    }
    private void reefCard() {
        System.out.println("Enter card number:");
        Integer number = scannerInt.nextInt();
        System.out.println("Enter balance: ");
        Double balance=scannerInt.nextDouble();
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
