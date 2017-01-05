package org.arcticsoft.bluebearlive.core.logic;

import android.support.v4.util.ArrayMap;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public class PermissionLevel {

    public static final int BANNED = -1;
    public static final int GUEST = 0;
    public static final int REG = 1;
    public static final int NOACTIVE = 5;
    public static final int AUTH = 10;
    public static final int USER = 15;
    public static final int GETFEEDLINE = 20;
    public static final int SENDCOMMAND = 25;
    public static final int SENDIMAGE = 30;
    public static final int VIP = 50;
    public static final int MODERATOR = 75;
    public static final int ADMIN = 100;
}
