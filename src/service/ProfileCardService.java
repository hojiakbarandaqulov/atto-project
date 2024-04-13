package service;

import dto.Card;
import dto.Profile;
import dto.ProfileCard;
import enums.CardStatus;
import enums.ProfileStatus;
import repository.ProfileRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class ProfileCardService {
    private Scanner scanner=new Scanner(System.in);
    private ProfileRepository profileRepository=new ProfileRepository();
    private UserRepository userRepository=new UserRepository();
    public void addProfile(){
        System.out.print("Enter number: ");
        String number=scanner.next();

        System.out.println("Enter exp date: ");
        String exp_date=scanner.next();
        userRepository.addCard(number,exp_date);
    }
    public void ProfileCardList(){
        ProfileCard profileCard = userRepository.ProfileCardList();
        System.out.println(profileCard.toString());
    }
    public void changeProfileCardService(){
        ProfileCard profileCard=new ProfileCard();
        System.out.print("Enter id: ");
        Integer id=scanner.nextInt();
        profileCard.setId(id);
        profileCard.setBalance(0);
        profileCard.isVisible();
        profileCard.setStatus(ProfileStatus.ACTIVE);
        LocalDate localDate = LocalDate.now();
        profileCard.setCreated_date(localDate.atStartOfDay());
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
