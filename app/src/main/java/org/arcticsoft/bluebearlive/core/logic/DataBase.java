package org.arcticsoft.bluebearlive.core.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mrheadshot62.api.types.UserDatas;

import java.sql.Timestamp;
import java.util.Map;

public class DataBase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "test3.db";

    public static final String USER = "users";
    public static final String USER_LOGIN = "login";
    public static final String USER_FNAME = "fname";
    public static final String USER_LNAME = "lname";
    public static final String USER_NICK = "nick_name";
    public static final String USER_AGE = "age";
    public static final String USER_COUNTRY = "country";
    public static final String USER_CITY = "city";
    public static final String USER_RATING = "rating";
    public static final String USER_PROFILE_PHOTO = "profile_photo";
    public static final String USER_LAST_ONLINE = "last_online";
    public static final String USER_EMAIL = "email";
    public static final String USER_FRIENDS = "friends";
    public static final String USER_PERMISSIONLVL = "permissionLevel";
    public static final String USER_COUNT_PHOTO = "count_photo";
    public static final String USER_BALANCE = "balance";
    public static final String USER_ONLINE = "online";
    public static final String USER_ID = "id";
    public static final String USER_REGISTRATION = "registration";
    public static final String USER_LAST_AUTH = "last_auth";
    public static final String USER_PHOTOS = "photos";
    public static final String USER_IS_UPDATED = "is_updated";
    private static final String CREATE_USER_TABLE = "CREATE TABLE ["+USER+"] (\n" +
            "  "+USER_LOGIN+" VARCHAR, \n" +
            "  "+USER_FNAME+" VARCHAR, \n" +
            "  "+USER_LNAME+" VARCHAR, \n" +
            "  "+USER_NICK+" VARCHAR, \n" +
            "  "+USER_AGE+" INTEGER, \n" +
            "  "+USER_ID+" INTEGER, \n" +
            "  "+USER_COUNTRY+" INTEGER, \n" +
            "  "+USER_CITY+" INTEGER, \n" +
            "  "+USER_RATING+" INTEGER, \n" +
            "  "+USER_PROFILE_PHOTO+" VARCHAR, \n" +
            "  "+USER_LAST_ONLINE+" TIMESTAMP, \n" +
            "  "+USER_EMAIL+" VARCHAR, \n" +
            "  "+USER_FRIENDS+" TEXT, \n" +
            "  "+USER_PERMISSIONLVL+" INTEGER, \n" +
            "  "+USER_COUNT_PHOTO+" INTEGER, \n" +
            "  "+USER_BALANCE+" INTEGER, \n" +
            "  "+USER_ONLINE+" INTEGER, \n" +
            "  "+USER_IS_UPDATED+" INTEGER, \n" +
            "  "+USER_REGISTRATION+" TIMESTAMP,\n" +
            "  "+USER_LAST_AUTH+" TIMESTAMP);";
    private static DataBase instance;

    public DataBase(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
        if (instance == null){
            DataBase.instance = this;
        }
    }

    public static DataBase getInstance() throws InstantiationException{
        if (instance !=null){
            return instance;
        }else{
            throw new InstantiationException("DataBase not create");
        }
    }

    public void setUser(UserDatas u){
        deleteUser();
        ContentValues cv = new ContentValues();
        cv.put(USER_LOGIN, u.getLogin());
        cv.put(USER_FNAME, u.getFname());
        cv.put(USER_LNAME, u.getLname());
        cv.put(USER_NICK, u.getNickname());
        cv.put(USER_AGE, u.getAge());
        cv.put(USER_COUNTRY, u.getContry());
        cv.put(USER_CITY, u.getCity());
        cv.put(USER_RATING, u.getRating());
        cv.put(USER_PROFILE_PHOTO, u.getProfilePhotos());
        //cv.put(USER_LAST_ONLINE, );
        cv.put(USER_EMAIL, u.getEmail());
        cv.put(USER_FRIENDS, u.getFriends());
        cv.put(USER_PERMISSIONLVL, u.getPermissionLvl());
        cv.put(USER_FRIENDS, u.getFriends());
        cv.put(USER_FRIENDS, u.getFriends());
        cv.put(USER_ID, u.getId());
        getWritableDatabase().insert(USER, null, cv);
        for (Map.Entry<String, Object> c:cv.valueSet()) {
            Log.d("DB", c.getKey()+": "+c.getValue());
        }
    }

    public UserDatas getUser() {
        UserDatas user=null;
        Cursor c = super.getReadableDatabase().query(USER, null, null, null, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                Log.d("DB", String.valueOf(c.getColumnIndex(USER_COUNT_PHOTO)));
                user = new UserDatas(c.getInt(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex(USER_LOGIN)),
                        c.getString(c.getColumnIndex(USER_FNAME)),
                        c.getString(c.getColumnIndex(USER_LNAME)),
                        c.getString(c.getColumnIndex(USER_FRIENDS)),
                        c.getString(c.getColumnIndex(USER_NICK)),
                        c.getString(c.getColumnIndex(USER_PHOTOS)),
                        c.getString(c.getColumnIndex(USER_PROFILE_PHOTO)),
                        c.getString(c.getColumnIndex(USER_EMAIL)),
                        c.getInt(c.getColumnIndex(USER_PERMISSIONLVL)),
                        c.getInt(c.getColumnIndex(USER_AGE)),
                        c.getInt(c.getColumnIndex(USER_COUNTRY)),
                        c.getInt(c.getColumnIndex(USER_CITY)),
                        c.getInt(c.getColumnIndex(USER_RATING)),
                        c.getInt(c.getColumnIndex(USER_IS_UPDATED)),
                        c.getInt(c.getColumnIndex(USER_COUNT_PHOTO)),
                        c.getInt(c.getColumnIndex(USER_BALANCE)),
                        (byte)c.getInt(c.getColumnIndex(USER_ONLINE)),
                        Timestamp.valueOf(c.getString(c.getColumnIndex(USER_LAST_ONLINE))),
                        Timestamp.valueOf(c.getString(c.getColumnIndex(USER_REGISTRATION))),
                        Timestamp.valueOf(c.getString(c.getColumnIndex(USER_LAST_AUTH)))
                        );
            }
            c.close();
        } else {
            Log.d("DB", "Cursor is null");
        }
        return user;
    }

    public void deleteUser(){
        getWritableDatabase().execSQL(String.format("DELETE FROM %s", USER));
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
