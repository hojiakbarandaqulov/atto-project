package service;

import container.ComponentContainer;
import dto.Card;
import dto.Terminal;
import enums.CardStatus;
import enums.TransactionType;
import repository.CardRepository;
import repository.TerminalRepository;
import repository.Transaction;

public class TransactionService {
    private CardRepository cardRepository;
    private TerminalRepository terminalRepository;
    private TransactionService transactionRepository;

    public TransactionService() {
        cardRepository = new CardRepository();
        terminalRepository = new TerminalRepository();
    }

    /*public void payment(String cardNumber, String terminalCode) {
        Card card = cardRepository.getCardByNumber(cardNumber);
        if (card == null) {
            System.out.println("Card not found");
            return;
        }
        Terminal terminal = terminalRepository.getTerminalByNumber(Integer.valueOf(terminalCode));
        if (terminal == null) {
            System.out.println("Card not found");
            return;
        }
        if (!card.getStatus().equals(CardStatus.ACTIVE)) {
            System.out.println("Not allowed");
            return;
        }
        if (card.getBalance() < ComponentContainer.fare) {
            System.out.println("Not enough balance");
            return;
        }
        //
        card.setBalance(card.getBalance() - ComponentContainer.fare); // withdraw
        cardRepository.updateCard(card.getId(), card.getBalance()); // update balance
        cardRepository.(ComponentContainer.companyCardId, ComponentContainer.fare);
        //
        Transaction transaction = new Transaction();
        transaction.set(card.getId());
        transaction.setTerminalId(terminal.getId());
        transaction.setTransactionType(TransactionType.Payment);
        transaction.setAmount(ComponentContainer.fare);
        transaction.setCreatedDate(LocalDateTime.now());
        transactionRepository.create(transaction);
        //
    }*/
}
