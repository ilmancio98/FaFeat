package com.example.fafeat.Databases;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagerGestore {
    //Variables
    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;

    //Session names
    public static final String SESSION_USERSESSION = "userLoginSession";
    public static final String SESSION_REMEMMBERME = "rememberMe";

    //User session variables
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAME_RISTORANTE = "restaurant_name";
    public static final String KEY_ADDRESS_RISTORANTE = "restaurant_address";
    public static final String KEY_NUMBER_RISTORANTE = "restaurant_number";
    public static final String KEY_IMG_RISTORANTE = "restaurant_img";

    //Remember Me variables
    private static final String IS_REMEMBERME = "IsRememberMe";
    public static final String KEY_SESSIONUSERNAME = "username";
    public static final String KEY_SESSIONPASSWORD = "password";

    //Constructor
    public SessionManagerGestore(Context _context, String sessionName) {
        context = _context;
        usersSession = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = usersSession.edit();
    }

    /*
    Users
    Login Session
     */

    public void createLoginSession(String username,String password) {

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public HashMap<String, String> getUsersDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_FULLNAME, usersSession.getString(KEY_FULLNAME, null));
        userData.put(KEY_USERNAME, usersSession.getString(KEY_USERNAME, null));
        userData.put(KEY_EMAIL, usersSession.getString(KEY_EMAIL, null));
        userData.put(KEY_PASSWORD, usersSession.getString(KEY_PASSWORD, null));
        userData.put(KEY_NAME_RISTORANTE, usersSession.getString(KEY_NAME_RISTORANTE, null));
        userData.put(KEY_ADDRESS_RISTORANTE, usersSession.getString(KEY_ADDRESS_RISTORANTE, null));
        userData.put(KEY_NUMBER_RISTORANTE, usersSession.getString(KEY_NUMBER_RISTORANTE, null));
        userData.put(KEY_IMG_RISTORANTE, usersSession.getString(KEY_IMG_RISTORANTE, null));

        return userData;
    }

    public boolean checkLogin() {
        if (usersSession.getBoolean(IS_LOGIN, false)) {
            return true;
        } else
            return false;
    }

    public void logoutGestoreFromSession() {
        editor.clear();
        editor.commit();
    }

    /*
    Remember Me
    Session Functions
     */

    public void createRememberMeSession(String username, String password) {

        editor.putBoolean(IS_REMEMBERME, true);
        editor.putString(KEY_SESSIONUSERNAME, username);
        editor.putString(KEY_SESSIONPASSWORD, password);

        editor.commit();
    }

    public HashMap<String, String> getRemeberMeDetailsFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_SESSIONUSERNAME, usersSession.getString(KEY_SESSIONUSERNAME, null));
        userData.put(KEY_SESSIONPASSWORD, usersSession.getString(KEY_SESSIONPASSWORD, null));

        return userData;
    }

    public boolean checkRememberMe() {
        if (usersSession.getBoolean(IS_REMEMBERME, false)) {
            return true;
        } else
            return false;
    }

    public void clearRememberMeSession() {
        editor.clear();
        editor.commit();
    }
}
