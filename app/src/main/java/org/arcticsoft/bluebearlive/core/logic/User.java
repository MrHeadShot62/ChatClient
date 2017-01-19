package org.arcticsoft.bluebearlive.core.logic;

import com.mrheadshot62.api.PermissionLevel;
import com.mrheadshot62.api.types.UserDatas;
import com.mrheadshot62.api.types.answer.ServerAnswerAuthUserPacket;

import java.sql.Timestamp;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public class User{

    private static final String TAG = "USER_APPLICATION";

    private String fname;
    private String lname;
    private String friends;
    private String login;
    private String nickname;
    private String photos;
    private String profilePhotos;
    private String email;
    private int id;
    private int permissionLvl;
    private int age;
    private int contry;
    private int city;
    private int rating;
    private int isUpdated;
    private int counPhoto;
    private int balance;
    private byte online;
    private Timestamp lastoOnline;
    private Timestamp registration;
    private Timestamp lastAuth;

    private String session;

    public boolean isAuth=false;

    private boolean isLogin = false;
    private boolean isBanned  = false;

    private static User instance;

    private User(){}

    public static User initGuestUser(){
        getInstance();
        instance.fname = "N/A";
        instance.lname = "N/A";
        instance.friends = "N/A";
        instance.login = "N/A";
        instance.nickname = "N/A";
        instance.photos = "N/A";
        instance.profilePhotos = "N/A";
        instance.email = "N/A";
        instance.id = 0;
        instance.permissionLvl = PermissionLevel.GUEST;
        instance.age = 0;
        instance.contry = 0;
        instance.city = 0;
        instance.rating = 0;
        instance.isUpdated = 0;
        instance.counPhoto = 0;
        instance.balance = 0;
        instance.online = 0;
        instance.isAuth = false;
        instance.lastoOnline = new Timestamp(0);
        instance.registration = new Timestamp(0);
        instance.lastAuth = new Timestamp(0);

        instance.session = "0";

        return instance;
    }

    public static User initAuthUser(ServerAnswerAuthUserPacket serverAnswerAuthUserPacket) throws InstantiationException {
        getInstance();
        UserDatas user = serverAnswerAuthUserPacket.getUserDatas();
        instance.fname = user.getFname();
        instance.lname = user.getLname();
        instance.friends = user.getFriends();
        instance.login = user.getLogin();
        instance.nickname = user.getNickname();
        instance.photos = user.getPhotos();
        instance.profilePhotos = user.getProfilePhotos();
        instance.email = user.getEmail();
        instance.id = user.getId();
        instance.permissionLvl = user.getPermissionLvl();
        instance.age = user.getAge();
        instance.contry = user.getContry();
        instance.city = user.getCity();
        instance.rating = user.getRating();
        instance.isUpdated = user.getIsUpdated();
        instance.counPhoto = user.getCounPhoto();
        instance.balance = user.getBalance();
        instance.online = user.getOnline();
        instance.lastoOnline = user.getLastoOnline();
        instance.registration = user.getRegistration();
        instance.lastAuth = user.getLastAuth();

        instance.session = serverAnswerAuthUserPacket.getSession();

        instance.isAuth = true;

        DataBase.getInstance().setUser(user);
        return instance;
    }

    public static User initAuthUserFromDB(UserDatas user, String session) throws InstantiationException {
        getInstance();
        instance.fname = user.getFname();
        instance.lname = user.getLname();
        instance.friends = user.getFriends();
        instance.login = user.getLogin();
        instance.nickname = user.getNickname();
        instance.photos = user.getPhotos();
        instance.profilePhotos = user.getProfilePhotos();
        instance.email = user.getEmail();
        instance.id = user.getId();
        instance.permissionLvl = user.getPermissionLvl();
        instance.age = user.getAge();
        instance.contry = user.getContry();
        instance.city = user.getCity();
        instance.rating = user.getRating();
        instance.isUpdated = user.getIsUpdated();
        instance.counPhoto = user.getCounPhoto();
        instance.balance = user.getBalance();
        instance.online = user.getOnline();
        instance.lastoOnline = user.getLastoOnline();
        instance.registration = user.getRegistration();
        instance.lastAuth = user.getLastAuth();

        instance.session = session;

        instance.isAuth = true;

        DataBase.getInstance().setUser(user);
        return instance;
    }

    public static User initTestUser(){
        getInstance();
        instance.fname = "Dmitriy";
        instance.lname = "Andreevich";
        instance.friends = "23";
        instance.login = "TestLogin";
        instance.nickname = "#Hennessy";
        instance.photos = "22";
        instance.profilePhotos = "id32";
        instance.email = "donsy2002@gmail.com";
        instance.id = 0;
        instance.permissionLvl = PermissionLevel.AUTH;
        instance.age = 20;
        instance.contry = 3;
        instance.city = 22;
        instance.rating = 3;
        instance.isUpdated = 0;
        instance.counPhoto = 22;
        instance.balance = 33;
        instance.online = 1;
        instance.isAuth = true;
        instance.lastoOnline = new Timestamp(0);
        instance.registration = new Timestamp(0);
        instance.lastAuth = new Timestamp(0);

        instance.session = "test";

        return instance;
    }

    public static synchronized User getInstance(){
        if(instance == null){
            return instance = new User();
        }else {
            return instance;
        }
    }

    public static boolean userIsAuth(){
        if(instance == null){
            getInstance();
            return false;
        }else {
            return instance.isAuth;
        }
    }

    public boolean isLogin() {
        return isLogin;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getFriends() {
        return friends;
    }

    public String getLogin() {
        return login;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhotos() {
        return photos;
    }

    public String getProfilePhotos() {
        return profilePhotos;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getPermissionLvl() {
        return permissionLvl;
    }

    public int getAge() {
        return age;
    }

    public int getContry() {
        return contry;
    }

    public int getCity() {
        return city;
    }

    public int getRating() {
        return rating;
    }

    public int getIsUpdated() {
        return isUpdated;
    }

    public int getCounPhoto() {
        return counPhoto;
    }

    public int getBalance() {
        return balance;
    }

    public byte getOnline() {
        return online;
    }

    public Timestamp getLastoOnline() {
        return lastoOnline;
    }

    public Timestamp getRegistration() {
        return registration;
    }

    public Timestamp getLastAuth() {
        return lastAuth;
    }

    public String getSession() {
        return session;
    }

    public boolean isAuth() {
        return isAuth;
    }
}
