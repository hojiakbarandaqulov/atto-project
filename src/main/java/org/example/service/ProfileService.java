package org.example.service;

import lombok.Setter;
import org.example.container.ComponentContainer;
import org.example.controller.AdminController;
import org.example.controller.UserController;
import org.example.dto.Profile;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
import org.example.repository.CardRepository;
import org.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AdminController adminController;
    @Autowired
    private UserController userController;

    public void create(Profile profile) {
        Profile exists = profileRepository.getProfileByPhone(profile.getPhone());
        if (exists != null) {
            System.out.println("Profile exist");
            return;
        }
        profile.setRole(ProfileRole.USER);
        profile.setCreatedDate(LocalDateTime.now());
        profile.setVisible(true);
        profile.setStatus(ProfileStatus.ACTIVE);
        profileRepository.create(profile);
        System.out.println("Success");
    }

    public void login(String phone, String password) {
        Profile profile = profileRepository.getProfileByPhone(phone);
        if (profile == null) {
            System.out.println("Profile not found");
            return;
        }
        if (!profile.getPassword().equals(password)) {
            System.out.println("Profile not found");
            return;
        }
        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            System.out.println("Status not active");
            return;
        }
        ComponentContainer.currentProfile = profile;
        if (profile.getRole().equals(ProfileRole.USER)) {
            userController.start();
        } else {
            adminController.start();
        }
    }

    public void profileList() {
        List<Profile> profiles = profileRepository.ProfileList();
        for (Profile profile : profiles) {
            System.out.println(profile.toString());
        }
    }

}
