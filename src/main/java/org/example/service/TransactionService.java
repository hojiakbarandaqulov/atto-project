package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Card;
import org.example.dto.Terminal;
import org.example.dto.TransactionDTO;
import org.example.enums.CardStatus;
import org.example.enums.TransactionType;
import org.example.repository.CardRepository;
import org.example.repository.TerminalRepository;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Service
public class TransactionService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionRepository transaction1;
    @Autowired
    private TerminalRepository terminalRepository;
    private TransactionService transactionRepository;

//    public TransactionService(CardRepository cardRepository,
//                              TransactionRepository transaction1,
//                              TerminalRepository terminalRepository,
//                              TransactionService transactionRepository) {
//        this.cardRepository = cardRepository;
//        this.transaction1 = transaction1;
//        this.terminalRepository = terminalRepository;
//        this.transactionRepository = transactionRepository;
//    }

    public void payment(String cardNumber, String terminalCode) {
        Card card = cardRepository.getCardByNumber(cardNumber);
        if (card == null) {
            System.out.println("Card not found");
            return;
        }
        Terminal terminal = terminalRepository.getTerminalByCode(terminalCode);
        if (terminal == null) {
            System.out.println("Card not found");
            return;
        }
        if (!card.getStatus().equals(CardStatus.ACTIVE)) {
            System.out.println("Not allowed");
            return;
        }
//        if (card.getBalance() < ComponentContainer.fare) {
//            System.out.println("Not enough balance");
//            return;
//        }
//        //
//        card.setBalance((int) (card.getBalance() - ComponentContainer.fare)); // withdraw
//        cardRepository.updateCard(card.getBalance()); // update balance
//        cardRepository.reFill(ComponentContainer.companyCardId, Double.valueOf(ComponentContainer.fare));
//        //
        TransactionDTO transaction = new TransactionDTO();
        transaction.setCardId(card.getId());
        transaction.setTerminalId(terminal.getId());
        transaction.setType(TransactionType.Payment);
        transaction.setAmount(ComponentContainer.fare);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction1.payment(transaction);
        //
    }

    public void listTransactions(){
        transactionRepository.listTransactions();
    }

    public void listRangeTransactions(LocalDate parse, LocalDate toDate) {
        transactionRepository.listRangeTransactions(parse,toDate);
    }
    public void viewCompanyCardBalance(){
        transactionRepository.viewCompanyCardBalance();
    }
    public void listTodayTransactions(){
        transactionRepository.listTodayTransactions();
    }
    public void listDailyTransactions(){
        transactionRepository.listDailyTransactions();
    }
}