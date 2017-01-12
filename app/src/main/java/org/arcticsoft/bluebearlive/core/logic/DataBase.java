package org.arcticsoft.bluebearlive.core.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Map;

/**
 * Created by novak on 10.01.2017.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "test";

    public static final String USER_TABLE = "users";
    public static final String USER_ID = "user_id";
    public static final String LOGIN = "login";
    public static final String PASS = "pass";
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";
    public static final String NICK = "nick";
    public static final String AGE = "age";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String MYPHOTOS = "myphotos";
    public static final String PROFILEPHOTO = "profile_photo";
    public static final String LASTONLINE = "last_online";
    public static final String EMAIL = "email";
    public static final String FRIENDS = "friends";
    public static final String SESSION = "session";
    public static final String IP = "ip";   //TODO сделать эти долбаные константы!!!
    private static final String CREATE_USER_TABLE = "CREATE TABLE [main] (\n" +
            "  "+FNAME+" TEXT, \n" +
            "  "+LNAME+" TEXT, \n" +
            "  "+LOGIN+" TEXT, \n" +
            "  "+NICK+" TEXT, \n" +
            "  "+COUNTRY+" TEXT, \n" +
            "  "+CITY+" VARCHAR, \n" +
            "  "+MYPHOTOS+" TEXT, \n" +
            "  "+PROFILEPHOTO+" TEXT, \n" +
            "  "+IP+" VARCHAR, \n" +
            "  "+LASTONLINE+" TIMESTAMP, \n" +
            "  "+FRIENDS+" VARCHAR, \n" +
            "  "+EMAIL+" VARCHAR, \n" +
            "  "+SESSION+" VARCHAR, \n" +
            "  "+PASS+" VARCHAR, \n" +
            "  "+AGE+" INT, \n" +
            "  "+USER_ID+" INT, \n" +
            "  version DOUBLE, \n" +
            "  balance INT, \n" +
            "  dialog_photo VARCHAR, \n" +
            "  registration_data TIMESTAMP);\n" +
            "\n";

    public DataBase(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    public void setUser(DBUser u){
        getWritableDatabase().execSQL(String.format("DELETE FROM %s", USER_TABLE));
        ContentValues cv = new ContentValues();
        cv.put(LOGIN, u.getLOGIN());
        cv.put(FNAME, u.getFNAME());
        cv.put(LNAME, u.getLNAME());
        cv.put(NICK, u.getNICK());
        cv.put(AGE, u.getAGE());
        cv.put(COUNTRY, u.getCOUNTRY());
        cv.put(CITY, u.getCITY());
        cv.put(MYPHOTOS, u.getMYPHOTOS());
        cv.put(PROFILEPHOTO, u.getPROFILEPHOTO());
        cv.put(LASTONLINE, u.getLASTONLINE());
        cv.put(EMAIL, u.getEMAIL());
        cv.put(FRIENDS, u.getFRIENDS());
        cv.put(SESSION, u.getSESSION());
        cv.put(AGE, u.getAGE());
        cv.put(USER_ID, u.getId());
        getWritableDatabase().insert(USER_TABLE, null, cv);
    }

    public DBUser getUser() {
        DBUser dbUser=null;
        Cursor c = super.getReadableDatabase().query(USER_TABLE, null, null, null, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                dbUser = new DBUser(c.getString(c.getColumnIndex(LOGIN)),
                        c.getString(c.getColumnIndex(FNAME)),
                        c.getString(c.getColumnIndex(LNAME)),
                        c.getString(c.getColumnIndex(NICK)),
                        c.getString(c.getColumnIndex(COUNTRY)),
                        c.getString(c.getColumnIndex(CITY)),
                        c.getString(c.getColumnIndex(MYPHOTOS)),
                        c.getString(c.getColumnIndex(PROFILEPHOTO)),
                        c.getString(c.getColumnIndex(LASTONLINE)),
                        c.getString(c.getColumnIndex(EMAIL)),
                        c.getString(c.getColumnIndex(FRIENDS)),
                        c.getString(c.getColumnIndex(SESSION)),
                        c.getInt(c.getColumnIndex(AGE)),
                        c.getInt(c.getColumnIndex(USER_ID)));
            }
            c.close();
        } else {
            Log.d("DB", "Cursor is null");
        }
        return dbUser;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
