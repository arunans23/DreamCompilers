package com.example.arunan.dreamcompilers.data;

/**
 * Created by arunan on 12/11/16.
 */

//Disease Schema class
public class DiseaseDbSchema {
    public static final class DiseaseTable{
        public static final String NAME= "diseases";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String SYMPTOMS = "symptoms";
            public static final String DESCRIPTION = "description";
            public static final String VICTIMCOUNT = "victims";
            public static final String SYNCED = "synced";
            public static final String USER_EMAIL = "user_email";
            public static final String LOCATION = "location";
         }
    }
}
