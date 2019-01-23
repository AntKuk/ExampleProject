package com.exampleproject.database;

import java.util.Queue;

/**
 * Interface for implement work with database.
 */
public interface DataBase<T> {

    /**
     * Execute select clause.
     * @param query Query.
     * @param parameters Input parameters.
     * @return Return specific object.
     */
    T select(String query, Queue<? super Object> parameters);

    /**
     * Execute query (update, delete, merge and etc).
     * @param query Query.
     * @param parameters Input parameters
     * @return Success.
     */
    boolean execute(String query, Queue<? super Object> parameters);
}
