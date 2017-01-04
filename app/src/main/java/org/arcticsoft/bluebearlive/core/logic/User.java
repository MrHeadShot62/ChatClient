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
    private int id = 1;

    private boolean isLogin = false;
    private boolean isBanned  = false;


    public User(String loginUser, String nameUser, String countryUser){

        this.loginUser = loginUser;
        this.nameUser = nameUser;
        this.countryUser = countryUser;
        this.session = new Session();

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

    public Session getSession() {
        return session;
    }

}
