package org.arcticsoft.bluebearlive.core.logic;

import com.mrheadshot62.api.PermissionLevel;
import com.mrheadshot62.api.types.AuthPacket;
import com.mrheadshot62.api.types.answer.ServerAnswerAuthUserPacket;

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
    public boolean isAuth=false;
    private int id = 0;
    public static final Object sync = new Object();

    private boolean isLogin = false;
    private boolean isBanned  = false;

    private static User instance = null;

    public static User authUser(ServerAnswerAuthUserPacket serverAnswerAuthUserPacket) {
        try {
            PacketManager.PacketGenerator(instance, new AuthPacket("guest", "guest"));
            com.mrheadshot62.api.types.User user = serverAnswerAuthUserPacket.getUser();
            instance.loginUser = user.getLogin();
            instance.nameUser = user.getFname();
            instance.countryUser = user.getCountry();
            instance.session = new Session(serverAnswerAuthUserPacket.getSession());
            instance.permissionLevel = 10;
            instance.isAuth = true;
            instance.id = user.getId();
            DataBase.getInstance().setUser(user);
        } catch (Exception e){
            guestUser("guest", "TestName", "TestCountry");
            e.printStackTrace();
        }

        return getInstance();
    }

    public static User authUser() {
//        try {
//            PacketManager.PacketGenerator(instance, new AuthPacket("guest", "guest"));
//            com.mrheadshot62.api.types.User user = serverAnswerAuthUserPacket.getUser();
//            instance.loginUser = user.getLogin();
//            instance.nameUser = user.getFname();
//            instance.countryUser = user.getCountry();
//            instance.session = new Session(serverAnswerAuthUserPacket.getSession());
//            instance.permissionLevel = 10;
//            instance.isAuth = true;
//            instance.id = user.getId();
//            DataBase.getInstance().setUser(user);
//        } catch (Exception e){
//            guestUser("guest", "TestName", "TestCountry");
//            e.printStackTrace();
//        }

        return getInstance();
    }

    public static User guestUser(String loginUser, String nameUser, String countryUser){
        instance = new User(loginUser, nameUser, countryUser, "guest" , PermissionLevel.GUEST);

        return getInstance();
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
        return isLogin;
    }

    @Override
    public boolean isBanned() {
        return isBanned;
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
