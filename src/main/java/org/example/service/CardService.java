package org.example.service;

import lombok.Setter;
import org.example.dto.Card;
import org.example.enums.CardStatus;
import org.example.repository.CardRepository;
import org.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
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
        Card exist = cardRepository.getCardByNumber(card.getNumber());

        if (exist == null) {
            System.out.println("Bu Card yoq");
            return;
        }

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

    public void cardList(){
        List<Card> cards = cardRepository.cardList();
        if (cards==null){
            System.out.println("List of Null");
            return;
        }
        for (Card card: cards){
            System.out.println(card.toString());
        }
    }


}