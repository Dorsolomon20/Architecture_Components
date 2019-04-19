package com.dorsolo.architecture_components.data.db;

public final class Queries {

    /**
     * Queries constants for the "note" table
     */
    public static final class NOTE {
        public static final String DELETE_ALL = "DELETE FROM note";
        public static final String GET_ALL_BY_PRIORITY = "SELECT * FROM note ORDER BY priority DESC";
    }
}
