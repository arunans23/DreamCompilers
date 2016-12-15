package com.example.arunan.dreamcompilers.data;

/**
 * Created by arunan on 12/13/16.
 */

//user database schema
public class UserDbSchema {
    public static final class UserTable{
        public static final String NAME= "users";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String FULLNAME = "fullname";
            public static final String EMAIL = "email";
            public static final String PASSWORD = "password";
            public static final String LOCATION = "location";
            public static final String DATE = "date";
            public static final String ROLE_ID = "roleID";
        }
    }
}
