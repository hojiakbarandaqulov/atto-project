package dto;

import enums.ProfileStatus;
import enums.ProfileRole;

import java.time.LocalDateTime;

public class Profile {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private LocalDateTime createdDate;
    private boolean visible;
    private ProfileStatus status;
    private ProfileRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ProfileRole getRole() {
        return role;
    }

    public void setRole(ProfileRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", visible=" + visible +
                ", status=" + status +
                ", role=" + role +
                '}';
    }
}
