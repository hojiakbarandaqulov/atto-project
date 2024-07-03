package org.example.service;

import lombok.Setter;
import org.example.dto.ProfileCard;
import org.example.enums.ProfileStatus;
import org.example.repository.ProfileRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
@Service
public class ProfileCardService {
    private Scanner scanner=new Scanner(System.in);
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    private ProfileCard profileCard;
    public void addProfile(){
        System.out.print("Enter number: ");
        String number=scanner.next();

        System.out.println("Enter exp date: ");
        String exp_date=scanner.next();
        userRepository.addCard(number,exp_date);
    }
    public void ProfileCardList(){
        List<ProfileCard> profileCardList = userRepository.ProfileCardList();
        for (ProfileCard profileCard: profileCardList) {
            System.out.println(profileCard.toString());
        }
    }
    public void changeProfileCardService(){
        System.out.print("Enter id: ");
        Integer id=scanner.nextInt();
      /*  profileCard.setId(id);
        profileCard.setBalance(0);
        profileCard.isVisible();
        profileCard.setStatus(ProfileStatus.ACTIVE);
        LocalDate localDate = LocalDate.now();
        profileCard.setCreated_date(localDate.atStartOfDay());*/
        userRepository.changeProfileCardStatus(profileCard);
    }
    public void ProfileCardDelete(){
        System.out.print("Enter id: ");
        Integer id=scanner.nextInt();
        ProfileCard profileCard = new ProfileCard();
        profileCard.setId(id);
        userRepository.delete(profileCard);
    }
    public void  profileCardStatus(Integer profile_id){
        ProfileCard profileCard=new ProfileCard();
        profileCard.setProfileId(profile_id);
        profileCard.setBalance(0);
        profileCard.setStatus(ProfileStatus.ACTIVE);
        profileCard.isVisible();
        LocalDate localDate = LocalDate.now();
        profileCard.setCreated_date(localDate.atStartOfDay());
        profileRepository.changeStatus(profileCard);
    }

}
