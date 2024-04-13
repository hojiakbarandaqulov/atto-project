package controller;

import dto.Card;
import dto.Terminal;
import repository.ProfileRepository;
import repository.TerminalRepository;
import repository.Transaction;
import service.CardService;
import service.ProfileCardService;
import service.TerminalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import repository.CardRepository;


public class AdminController {
    private Scanner scannerInt;
    private Scanner scannerStr;
    private CardService cardService;
    private CardRepository cardRepository;
    private TerminalRepository terminalRepository;
    private Terminal terminal;
    private TerminalService terminalService = new TerminalService();
    private ProfileRepository profileRepository;
    private ProfileCardService profileCardService;
    private Transaction transaction = new Transaction();

    public AdminController() {
        scannerInt = new Scanner(System.in);
        cardRepository = new CardRepository();
        scannerStr = new Scanner(System.in);
        cardService = new CardService();
        terminalRepository = new TerminalRepository();
        terminal = new Terminal();
        profileRepository = new ProfileRepository();
        profileCardService = new ProfileCardService();
    }

    public void start() {
        boolean b = true;
        while (b) {
            showMenuAdmin();
            int action = getAction();
            switch (action) {
                case 1:
                    createCard();
                    break;
                case 2:
                    cardList();
                    break;
                case 3:
                    updateCard();
                    break;
                case 4:
                    changeCardStatus();
                    break;
                case 5:
                    Delete();
                    break;
                case 6:
                    System.out.println("Table terminal");
                    createTerminal();
                    break;
                case 7:
                    System.out.println("Terminal list");
                    terminalService.terminalList();
                    break;
                case 8:
                    terminalService.updateTerminal();
                    break;
                case 9:
                    terminalService.TerminalChangeStatus(changeTerminalStatus());
                    break;
                case 10:
                    DeleteTerminal();
                    break;
                case 11:
                    profileRepository.ProfileList();
                    break;
                case 12:
                    profile_id();
                    break;
                case 13:
                    transaction.listTransactions();
                    break;
                case 14:
                    transaction.viewCompanyCardBalance(cardId());
                    break;
                case 15:
                    transaction.listTodayTransactions();
                    break;
                case 16:
                    transaction.listDailyTransactions(getLocalDate());
                    break;
                case 17:
//                    transaction.listRangeTransactions();
                    break;
                case 18:
                    transaction.listTransactionsByTerminal(byTerminal());
                    break;
                case 19:
                    transaction.listTransactionsByCard(listTransactionByCard());
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

    public String listTransactionByCard() {
        System.out.print("Enter by card: ");
        String byCard = scannerStr.next();
        return byCard;
    }

    public LocalDate getLocalDate() {
        System.out.println("Enter LocalDate: ");
        LocalDate localDate = LocalDate.now();
        String local = scannerStr.next(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return LocalDate.parse(local);
    }

    public int byTerminal() {
        System.out.println("Enter Terminal: ");
        int byTerminal = scannerInt.nextInt();
        return byTerminal;
    }

    public void profile_id() {
        System.out.println("Enter profile_id: ");
        Integer profile_id = scannerInt.nextInt();
        profileCardService.profileCardStatus(profile_id);
    }

    public int cardId() {
        System.out.println("Enter cardId: ");
        Integer cardId = scannerInt.nextInt();
        return cardId;
    }

    public void createTerminal() {
        System.out.print("Enter code: ");
        String code = scannerStr.next();
        System.out.print("Enter address: ");
        String addressStr = scannerStr.next();

        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setAddress(addressStr);
        terminalService.createTerminal(terminal);
    }

    public void Delete() {
        System.out.print("Enter card number: ");
        String number = scannerStr.next();
        cardService.deleteCard(number);
    }

    public void DeleteTerminal() {
        System.out.print("Enter terminal code: ");
        String code = scannerStr.next();
        terminalService.deleteTerminal(code);
    }

    public void changeCardStatus() {
        System.out.print("Enter card number: ");
        String number = scannerStr.next();
        cardService.ChangeCardStatus(number);
    }

    public String changeTerminalStatus() {
        System.out.print("Enter card code: ");
        String code = scannerStr.next();

        terminalService.TerminalChangeStatus(code);
        return code;
    }

    public void updateCard() {
        System.out.println("Enter id: ");
        Integer id = scannerInt.nextInt();
        System.out.print("Enter expDate:(exm:yyyy-MM-dd): ");
        String exp_dateStr = scannerStr.next();
        System.out.print("Enter number: ");
        String number = scannerStr.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate exp_date = LocalDate.parse(exp_dateStr, formatter);
        Card card = new Card();
        card.setExpDate(exp_date);
        card.setNumber(number);
        card.setId(id);
        cardService.updateCard(card);
    }

    public void cardList() {
        List<Card> cardList = cardRepository.cardList();
        for (Card card : cardList) {
            System.out.println(card.getId() + " " + card.getNumber() + " " + card.getExpDate() + " " + card.getBalance() + " " + card.getStatus() + " " + card.isVisible() + " " + card.getCreatedDate());
        }
    }

    public void profileList() {
//        Profile profile = new Profile();
//        profile.setId(profile.getId());
//        profile.setName("name");
//        profile.setSurname("surname");
//        profile.setPhone("phone");
//        profile.setStatus(ProfileStatus.ACTIVE);

//        for (Profile profile : profileList) {
//            System.out.println(profile.toString());
////            System.out.println(profile1.getId() + " " + profile1.getName() + " " + profile1.getSurname() + " " + profile1.getPassword() + " "+ profile1.getStatus() + " " + profile1.isVisible() + " " + profile1.getCreatedDate());
//        }
    }

    public void createCard() {
        System.out.print("Enter number: ");
        String number = scannerStr.next();
        System.out.print("Enter expDate:(exm:2027-07-07): ");
        String exp_dateStr = scannerStr.next();

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
