package com.dorsolo.architecture_components.utilites;

import androidx.annotation.NonNull;

public final class Precondition {

    /**
     * Invoked in order to check a condition for True, in cases it found to be false, the provided Exception (RunTimeException) will be thrown
     *
     * @param exception Generic obj instance which must extend the RuntimeException class
     * @param condition Boolean value which represent the condition to check (1 < 2 etc.)
     * @param <T>       Deceleration of the Generic value
     */
    public static <T extends RuntimeException> void checkCondition(@NonNull T exception, boolean condition) {
        if (!condition)
            throw exception;
    }

    /**
     * Invoked in order to check more then one condition (VarArgs of conditions), the number of conditions is undefined, If one of the condition
     * The provided exception is thrown
     *
     * @param exception  Generic obj instance which must extend the RuntimeException class
     * @param conditions VarArgs of boolean value which represent all of the conditions to check
     * @param <T>        Deceleration of the Generic value
     */
    public static <T extends RuntimeException> void checkCondition(@NonNull T exception, boolean... conditions) {
        for (boolean condition : conditions) {
            if (!condition)
                throw exception;
        }
    }
}
