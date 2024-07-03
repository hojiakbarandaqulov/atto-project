package org.example.controller;

import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.dto.Terminal;
import org.example.enums.ProfileStatus;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
//@Setter
@Controller
@Scope("singleton")
public class AdminController {
    private Scanner scanner;
    @Autowired
    @Qualifier("cardService")
    private CardService cardService;
    @Autowired
    @Qualifier("terminalService")
    private TerminalService terminalService;
    @Autowired
    @Qualifier("profileCardService")
    private ProfileCardService profileCardService;
    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;
    @Autowired
    private TransactionService transactionService;
    public AdminController() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean b = true;
        while (b) {
            showMenuAdmin();
            int action = getAction();
            switch (action) {
                case 1 -> createCard();
                case 2 -> cardList();
                case 3 -> updateCard();
                case 4 -> changeCardStatus();
                case 5 -> Delete();
                case 6 -> {
                    System.out.println("Table terminal");
                    createTerminal();
                }
                case 7 -> {
                    System.out.println("Terminal list");
                    terminalList();
                }
                case 8 -> terminalService.updateTerminal();
                case 9 -> terminalService.TerminalChangeStatus(changeTerminalStatus());
                case 10 -> DeleteTerminal();
                case 11 -> profileList();
                case 12 -> profile_id();
                case 13 -> listTransactions();
                case 14 -> viewCompanyCardBalance(cardId());
                case 15 -> listTodayTransactions();
                case 16 -> listDailyTransactions(getLocalDate());
                case 17 -> listRangeTransaction();
                case 18 -> byTerminal();
                case 19 -> listTransactionByCard();
                case 0 -> {
                    System.out.println("Exit");
                    b = false;
                }
                default -> b = false;
            }
        }
    }

    public void listDailyTransactions(LocalDate localDate) {
        transactionService.listDailyTransactions();
    }

    public void listTodayTransactions() {
        transactionService.listTodayTransactions();
    }

    public void viewCompanyCardBalance(int i) {
        transactionService.viewCompanyCardBalance();
    }

    public void listTransactions() {
        transactionService.listTransactions();
    }

    public void terminalList() {
        terminalService.terminalList();
    }

    public void listRangeTransaction(){
        System.out.println("Enter localDate: ");
        LocalDate localDate= LocalDate.parse(scanner.next());
        System.out.println("Enter toDate: ");
        LocalDate toDate= LocalDate.parse(scanner.next());

        LocalDate localDate1=LocalDate.from(localDate.atStartOfDay());
        transactionService.listRangeTransactions(LocalDate.parse(String.valueOf(localDate1)),toDate);
    }
    public String listTransactionByCard() {
        System.out.print("Enter by card: ");
        String byCard = scanner.next();
        return byCard;
    }

    public LocalDate getLocalDate() {
        System.out.println("Enter LocalDate: ");
        LocalDate localDate = LocalDate.now();
        String local = scanner.next(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return LocalDate.parse(local);
    }

    public int byTerminal() {
        System.out.println("Enter Terminal: ");
        int byTerminal = scanner.nextInt();
        return byTerminal;
    }

    public void profile_id() {
        System.out.println("Enter profile_id: ");
        Integer profile_id = scanner.nextInt();
        profileCardService.profileCardStatus(profile_id);
    }

    public int cardId() {
        System.out.println("Enter cardId: ");
        Integer id= scanner.nextInt();
        return id;
    }

    public void createTerminal() {
        System.out.print("Enter code: ");
        String code = scanner.next();
        System.out.print("Enter address: ");
        String addressStr = scanner.next();

        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setAddress(addressStr);
        terminalService.createTerminal(terminal);
    }

    public void Delete() {
        System.out.print("Enter card number: ");
        String number = scanner.next();
        cardService.deleteCard(number);
    }

    public void DeleteTerminal() {
        System.out.print("Enter terminal code: ");
        String code = scanner.next();
        terminalService.deleteTerminal(code);
    }

    public void changeCardStatus() {
        System.out.print("Enter card number: ");
        String number = scanner.next();
        cardService.ChangeCardStatus(number);
    }

    public String changeTerminalStatus() {
        System.out.print("Enter card code: ");
        String code = scanner.next();

        terminalService.TerminalChangeStatus(code);
        return code;
    }

    public void updateCard() {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        System.out.print("Enter expDate:(exm:yyyy-MM-dd): ");
        String exp_dateStr = scanner.next();
        System.out.print("Enter number: ");
        String number = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate exp_date = LocalDate.parse(exp_dateStr, formatter);
        Card card = new Card();
        card.setExpDate(exp_date);
        card.setNumber(number);
        card.setId(id);
        cardService.updateCard(card);
    }

    public void cardList() {
       cardService.cardList();
    }

    public void profileList() {
        Profile profile = new Profile();
        profile.setId(profile.getId());
        profile.setName("name");
        profile.setSurname("surname");
        profile.setPhone("phone");
        profile.setStatus(ProfileStatus.ACTIVE);

       profileService.profileList();
    }

    public void createCard() {
        System.out.print("Enter number: ");
        String number = scanner.next();
        System.out.print("Enter expDate:(exm:2027-07-07): ");
        String exp_dateStr = scanner.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate exp_date = LocalDate.parse(exp_dateStr, formatter);

        Card card = new Card();
        card.setNumber(number);
        card.setExpDate(exp_date);
        cardService.createCard(card);
    }

    public void showMenuAdmin() {
        System.out.println("*** Admin Menu *** ");
        System.out.println("1. Create Card");
        System.out.println("2. Card List");
        System.out.println("3. Update Card");
        System.out.println("4. Change Status Card");
        System.out.println("5. Delete Card");
        System.out.println("6. Create terminal");
        System.out.println("7. Terminal List");
        System.out.println("8. Update Terminal");
        System.out.println("9. Change Terminal Status");
        System.out.println("10. Delete Terminal");
        System.out.println("11. Profile List");
        System.out.println("12. Profile Card Status");
        System.out.println("13. Transaction list");
        System.out.println("14. Company Card Balance");
        System.out.println("15. listTodayTransactions ");
        System.out.println("16. listDailyTransactions ");
        System.out.println("17. listRangeTransactions");
        System.out.println("18. viewCompanyCardBalance ");
        System.out.println("19. Transaction By Card ");
        System.out.println("0. Exit Profile");
    }

    public int getAction() {
        System.out.print("Enter action: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
