package dto;

import enums.ProfileStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProfileCard {
    private Integer id;
    private Integer cardId;
    private String number;
    private LocalDate expDate;
    private ProfileStatus status;
    private Integer profileId;
    private Integer balance;
    private boolean visible;
    private LocalDateTime created_date;

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "ProfileCard{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", profileId=" + profileId +
                ", status=" + status +
                ", visible=" + visible +
                ", balance=" + balance +
                ", created_date=" + created_date +
                '}';
    }
}
