package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.ProfileStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class ProfileCard {
    private Integer id;
    private Integer cardId;
//    private String number;
//    private LocalDate expDate;
    private ProfileStatus status;
    private Integer profileId;
    private Integer balance;
    private boolean visible;
    private LocalDateTime created_date;

}
