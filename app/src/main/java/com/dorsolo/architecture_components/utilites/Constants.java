package com.dorsolo.architecture_components.utilites;

public final class Constants {

    /**
     * Database Constants
     */
    public static final class Database {
        public static final String DATABASE_NAME = "room_view_model_live_data.db";
        public static final int VERSION = 1;
        public static final boolean EXPORT_SCHEMA = false;
        public static final String NOTE_TABLE_NAME = "note";
    }

    /**
     * Background constants
     */
    public static final class Background {
        public static final int THREAD_POOL_MIN = 0;
        public static final int THREAD_POOL_MAX = 100;
        public static final int THREAD_POOL_DEFAULT_SIZE = 5;
        public static final int INSERT = 0;
        public static final int DELETE = 1;
        public static final int UPDATE = 2;
        public static final int DELETE_ALL = 3;
    }

    /**
     * FileUtils constants
     */
    static final class Files {
        static final int MIN_QUALITY = 0;
        static final int MAX_QUALITY = 100;
    }

    /**
     * Note paging constants
     */
    public static final class NotePaging {
        public static final boolean ENABLE_PLACEHOLDERS = false;
        public static final int INITIAL_LOAD_SIZE = 10;
        public static final int PAGE_SIZE = 10;
    }
}
