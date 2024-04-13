package service;

import dto.Card;
import dto.Terminal;
import enums.CardStatus;
import enums.TerminalStatus;
import repository.TerminalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TerminalService {
    private TerminalRepository terminalRepository = new TerminalRepository();
    private Scanner scanner = new Scanner(System.in);
    public void createTerminal(Terminal terminal) {
//        Terminal exist = terminalRepository.getTerminalById(terminal);

//        if (exist != null) {
//            System.out.println("Bu terminal avvaldan bor");
//            return null;
//        }

        int n = terminalRepository.createTerminal(terminal);
        if (n == 1) {
            System.out.println("Terminal added successfully");
        } else {
            System.out.println("Error terminal add");
        }
    }

    public void terminalList() {
        List<Terminal> terminalList = (List<Terminal>) terminalRepository.terminalList();
        for (Terminal terminal : terminalList) {
            System.out.println(terminal.getId()+" "+terminal.getCode() + " " + terminal.getStatus() + " " + terminal.getAddress() + " " + terminal.getStatus() + " " + terminal.getCreatedDate());
        }
    }

    public void updateTerminal() {
        System.out.println("Enter id: ");
        Integer id=scanner.nextInt();
        Terminal exist = terminalRepository.getTerminalByNumber(id);

        if (exist == null) {
            System.out.println("Bu Terminal yoq");
            return;
        }
        System.out.println("Enter code: ");
        String code = scanner.next();
        System.out.println("Enter address: ");
        String address = scanner.next();
        Integer effectedRow = terminalRepository.updateTerminal(code, address);
        if (effectedRow == 1) {
            System.out.println("Card added successfully");
        } else {
            System.out.println("Card no added");
        }
    }
    public void TerminalChangeStatus(String code){
        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setStatus(TerminalStatus.ACTIVE);
        terminal.isVisible();
        LocalDate localDate = LocalDate.now();
        terminal.setCreatedDate(localDate.atStartOfDay());
        terminalRepository.changeStatus(terminal);
    }
    public void deleteTerminal(String code){
        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setStatus(TerminalStatus.ACTIVE);
        terminal.isVisible();
        LocalDate localDate = LocalDate.now();
        terminal.setCreatedDate(localDate.atStartOfDay());
        terminalRepository.deleteTerminal(terminal);
    }
}
