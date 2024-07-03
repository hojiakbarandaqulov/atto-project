package org.example.service;

import lombok.Setter;
import org.example.dto.Terminal;
import org.example.enums.TerminalStatus;
import org.example.repository.TerminalRepository;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
@Lazy
@Service
public class TerminalService {
    @Autowired
    private TerminalRepository terminalRepository;
    private Scanner scanner = new Scanner(System.in);

    public TerminalService() {
        System.out.println("Terminal Constructor");
    }

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
            System.out.println(terminal.getId() + " " + terminal.getCode() + " " + terminal.getStatus() + " " + terminal.getAddress() + " " + terminal.getStatus() + " " + terminal.getCreatedDate());
        }
    }

    public void updateTerminal() {
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        Terminal exist = terminalRepository.getTerminalByNumber(String.valueOf(id));

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

    public void TerminalChangeStatus(String code) {
        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setStatus(TerminalStatus.ACTIVE);
        terminal.isVisible();
        LocalDate localDate = LocalDate.now();
        terminal.setCreatedDate(localDate.atStartOfDay());
        terminalRepository.changeStatus(terminal);
    }

    public void deleteTerminal(String code) {
        Terminal terminal = new Terminal();
        terminal.setCode(code);
        terminal.setStatus(TerminalStatus.ACTIVE);
        terminal.isVisible();
        LocalDate localDate = LocalDate.now();
        terminal.setCreatedDate(localDate.atStartOfDay());
        terminalRepository.deleteTerminal(terminal);
    }
}
