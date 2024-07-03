package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.TransactionType;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class TransactionDTO {
    private Integer cardId;
    private Integer terminalId;
    private String cardNumber;
    private String profileName;
    private String profileSurname;
    private String terminalNumber;
    private String terminalAddress;
    private Long amount;
    private LocalDateTime transactionDate;
    private TransactionType type;

}
