package org.arcticsoft.bluebearlive.core.logic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by novak on 10.01.2017.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "test";

    public static final String TABLE_NAME = "users";
    public static final String LOGIN = "login";
    public static final String PASS = "pass";
    public static final String NAME = "name";
    public static final String NICK = "nick";
    public static final String AGE = "age";
    public static final String COUNTRY = "country";
    public static final String CITY = "city";
    public static final String IP = "ip";   //TODO сделать эти долбаные константы!!!
    private static final String CREATE_TABLE = "CREATE TABLE [main] (\n" +
            "  id INTEGER PRIMARY KEY ON CONFLICT FAIL AUTOINCREMENT, \n" +
            "  "+NAME+" TEXT, \n" +
            "  "+LOGIN+" TEXT, \n" +
            "  "+NICK+" TEXT, \n" +
            "  "+AGE+" INT, \n" +
            "  "+COUNTRY+" TEXT, \n" +
            "  "+CITY+" VARCHAR, \n" +
            "  myphotos TEXT, \n" +
            "  profilephoto TEXT, \n" +
            "  ip VARCHAR, \n" +
            "  lastonline TIMESTAMP, \n" +
            "  friends VARCHAR, \n" +
            "  email VARCHAR, \n" +
            "  version DOUBLE, \n" +
            "  balance INT, \n" +
            "  dialog_photo VARCHAR, \n" +
            "  registration_data TIMESTAMP);\n" +
            "\n";

    public DataBase(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}
