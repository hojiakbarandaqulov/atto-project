package dto;

import enums.TerminalStatus;

import java.time.LocalDateTime;

public class Terminal {
    private Integer id;
    private String code;
    private String address;
    private TerminalStatus status;
    private boolean visible;
    private LocalDateTime createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TerminalStatus getStatus() {
        return status;
    }

    public void setStatus(TerminalStatus status) {
        this.status = status;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}