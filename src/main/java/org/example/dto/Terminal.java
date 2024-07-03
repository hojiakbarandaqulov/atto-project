package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.enums.TerminalStatus;
import org.example.enums.TerminalStatus;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class Terminal {
    private Integer id;
    private String code;
    private String address;
    private TerminalStatus status;
    private boolean visible;
    private LocalDateTime createdDate;
}