package org.arcticsoft.bluebearlive.core.logic;

import org.arcticsoft.bluebearlive.core.aLogic.AUser;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public class User extends AUser {

    private String loginUser;
    private String nameUser;
    private String countryUser;
    private Session session;
    private int permissionLevel;

    private int id = 0;

    private boolean isLogin = false;
    private boolean isBanned  = false;

    private static User instance = null;

    public static User authUser(String loginUser, String nameUser, String countryUser, String sessionKey, int permissionLevel) {
        if(instance != null){
            instance.loginUser = loginUser;
            instance.nameUser = nameUser;
            instance.countryUser = countryUser;
            instance.session = new Session(sessionKey);
            instance.permissionLevel = permissionLevel;
            return getInstance();
        }else {
            instance = new User(loginUser, nameUser, countryUser, sessionKey , permissionLevel);
            return getInstance();
        }
    }

    public static User guestUser(String loginUser, String nameUser, String countryUser){
        if(instance == null){
            instance = new User(loginUser, nameUser, countryUser, "guest" , PermissionLevel.AUTH);
            return getInstance();
        }else {
            return getInstance();
        }
    }

    public static synchronized User getInstance(){
        return instance;
    }

    private User(String loginUser, String nameUser, String countryUser, String sessionKey, int permissionLevel){
        this.loginUser = loginUser;
        this.nameUser = nameUser;
        this.countryUser = countryUser;
        this.session = new Session(sessionKey);
        this.permissionLevel = permissionLevel;

        if(session.checkSession()){
            isLogin = true;
        }
    }


    @Override
    public boolean isLogin() {
        if(this.isLogin){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isBanned() {
        if(this.isBanned){
            return true;
        }else {
            return false;
        }
    }

    public String getLoginUser() {
        return loginUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getCountryUser() {
        return countryUser;
    }

    public String getSessionKey() {
        return session.getSessionKey();
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public Session getSession() {
        return session;
    }

    public int getId() {
        return id;
    }

}
