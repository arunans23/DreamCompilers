package com.example.arunan.dreamcompilers.data;

/**
 * Created by arunan on 12/13/16.
 */

//user database schema
public class UserDbSchema {
    public static final class UserTable{
        public static final String NAME= "users";

        public static final class Cols{

            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String ROLE_ID = "roleID";
            public static final String LOGIN = "login";
        }
    }

    public static final class UserDetailTable{
        public static final String NAME = "user_details";

        public static final class Cols{
            public static final String EMAIL = "email";
            public static final String USERNAME = "username";
            public static final String FIRSTNAME = "first_name";
            public static final String MIDDLENAME = "middle_name";
            public static final String LASTNAME = "last_name";
            public static final String PHONENUMBER = "phone_number";
        }
    }

    public static final class UserRoleTable{
        public static final String NAME = "user_role";

        public static final class Cols{
            public static final String ROLEID = "roleID";
            public static final String ROLENAME = "role_name";
            public static final String ADMINLEVEL = "admin_level";
        }
    }
}
