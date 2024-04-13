package db;

import db.DatabaseConnection;
import dto.Profile;
import enums.ProfileStatus;
import enums.ProfileRole;
import repository.ProfileRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    public static void createTable() {
        String profile = "create table if not exists profile( " +
                " id serial primary key, " +
                " name varchar(25) not null, " +
                " surname varchar(25) not null , " +
                " phone varchar(13) not null, " +
                " password  varchar(255) not null, " +
                " created_date timestamp default(now()), " +
                " visible boolean default(true), " +
                " status varchar(255) default('ACTIVE'), " +
                " role varchar(255) default('USER') " +
                ")";
        execute(profile);

        String card = "create table if not exists card( " +
                " id serial primary key , " +
                " number varchar(16) not null unique , " +
                " exp_date date not null, " +
                " balance numeric default(0), " +
                " status varchar(255) default('ACTIVE'), " +
                " visible boolean default true, " +
                " created_date timestamp not null default(now()) " +
                ")";
        execute(card);
        String terminal = "create table  if not exists terminal( " +
                " id serial primary key , " +
                " code varchar(25) not null unique , " +
                " address varchar(25) not null, " +
                " status varchar(255) default('ACTIVE'), " +
                " visible boolean default true, " +
                " created_date timestamp not null default(now()) " +
                ")";
        execute(terminal);
        String profileCard = "create table if not exists profile_card(  " +
                " id serial primary key," +
                " cardId bigint," +
                " profileId bigint," +
                " status varchar(255) default('ACTIVE')," +
                " balance numeric default(0)," +
                " visible boolean default(true)," +
                " created_date timestamp not null default(now())" +
                ")";
        execute(profileCard);
        String transactions = "create table if not exists transactions" +
                "(" +
                "    id  serial primary key," +
                "    cardId bigint," +
                "    amount numeric(10,2)," +
                "    type varchar(255)," +
                "    terminal_id bigint," +
                "    created_date timestamp not null default (now())" +
                ")";
        execute(transactions);
    }

    private static void execute(String sql) {
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void initAdmin() {
        ProfileRepository profileRepository = new ProfileRepository();
        Profile exist = profileRepository.getProfileByPhone("995092376");
        if (exist != null) {
            return;
        }
        Profile admin = new Profile();
        admin.setPhone("995092376");
        admin.setName("Admin");
        admin.setSurname("Admnov");
        admin.setPassword("1234"); // MD5
        admin.setStatus(ProfileStatus.ACTIVE);
        admin.setRole(ProfileRole.ADMIN);
        profileRepository.create(admin);
    }
}
