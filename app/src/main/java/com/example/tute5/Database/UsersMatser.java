package com.example.tute5.Database;

import android.provider.BaseColumns;

public final class UsersMatser {
    //prevent instatntiations
    private UsersMatser() {

    }

    public static class Users implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
