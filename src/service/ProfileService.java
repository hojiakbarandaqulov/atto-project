package service;

import container.ComponentContainer;
import controller.AdminController;
import controller.UserController;
import dto.Profile;
import enums.ProfileStatus;
import enums.ProfileRole;
import repository.ProfileRepository;

import java.time.LocalDateTime;

public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService() {
        profileRepository = new ProfileRepository();
    }

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
            UserController userController = new UserController();
            userController.start();
        } else {
            AdminController adminController = new AdminController();
            adminController.start();
        }
    }


}
