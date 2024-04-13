package service;

import dto.Card;
import enums.CardStatus;
import repository.CardRepository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CardService {
    private List<Card>list=new LinkedList<>();
    private CardRepository cardRepository=new CardRepository();
    private Scanner scanner=new Scanner(System.in);
    public void createCard(Card card) {

        Card exist = cardRepository.getCardByNumber(card.getNumber());

        if (exist != null) {
            System.out.println("Bu Card avvaldan bor");
            return;
        }
        card.setBalance(0);
        card.setStatus(CardStatus.ACTIVE);
        card.isVisible();
        LocalDate localDate = LocalDate.now();
        card.setCreatedDate(localDate.atStartOfDay());
        cardRepository.createCard(card);
    }
    public void updateCard(Card card) {
//        Card exist = cardRepository.getCardByNumber(card.getId());
//
//        if (exist == null) {
//            System.out.println("Bu Card yoq");
//            return;
//        }

        Integer effectedRow = cardRepository.updateCard(card.getExpDate(),card.getNumber());
        if (effectedRow == 1) {
            System.out.println("Card added successfully");
        }else {
            System.out.println("Card no added");
        }
    }
    public void ChangeCardStatus(String number){
        Card card=new Card();
        card.setNumber(number);
        card.setExpDate(LocalDate.now());
        card.setBalance(0);
        card.setStatus(CardStatus.ACTIVE);
        card.isVisible();
        LocalDate localDate = LocalDate.now();
        card.setCreatedDate(localDate.atStartOfDay());
        cardRepository.changeStatus(card);
    }
    public void deleteCard(String number){
        Card card=new Card();
        card.setNumber(number);
        card.setExpDate(LocalDate.now());
        card.setBalance(0);
        card.setStatus(CardStatus.ACTIVE);
        card.isVisible();
        LocalDate localDate = LocalDate.now();
        card.setCreatedDate(localDate.atStartOfDay());
        cardRepository.delete(card);
    }
    public void ReFill(Integer number,Double userId){
        cardRepository.reFill(number,userId);
    }
}