package org.arcticsoft.bluebearlive.core.logic;


/**
 * Created by novak on 11.01.2017.
 */

public class DBUser {
    private String LOGIN, FNAME, LNAME, NICK, COUNTRY, CITY, MYPHOTOS, PROFILEPHOTO, LASTONLINE, EMAIL, FRIENDS, SESSION;
    private int AGE, id;

    public DBUser(String LOGIN, String FNAME, String LNAME, String NICK, String COUNTRY, String CITY, String MYPHOTOS, String PROFILEPHOTO, String LASTONLINE, String EMAIL, String FRIENDS, String SESSION, int AGE, int id) {
        this.LOGIN = LOGIN;
        this.FNAME = FNAME;
        this.LNAME = LNAME;
        this.NICK = NICK;
        this.COUNTRY = COUNTRY;
        this.CITY = CITY;
        this.MYPHOTOS = MYPHOTOS;
        this.PROFILEPHOTO = PROFILEPHOTO;
        this.LASTONLINE = LASTONLINE;
        this.EMAIL = EMAIL;
        this.FRIENDS = FRIENDS;
        this.SESSION = SESSION;
        this.AGE = AGE;
        this.id = id;
    }


    public String getLOGIN() {
        return LOGIN;
    }

    public String getFNAME() {
        return FNAME;
    }

    public String getLNAME() {
        return LNAME;
    }

    public String getNICK() {
        return NICK;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public String getCITY() {
        return CITY;
    }

    public String getMYPHOTOS() {
        return MYPHOTOS;
    }

    public String getPROFILEPHOTO() {
        return PROFILEPHOTO;
    }

    public String getLASTONLINE() {
        return LASTONLINE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getFRIENDS() {
        return FRIENDS;
    }

    public String getSESSION() {
        return SESSION;
    }

    public int getAGE() {
        return AGE;
    } // GETTERS

    public int getId() {
        return id;
    }
}
