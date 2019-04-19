package com.dorsolo.architecture_components.builders;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

public class RuntimeExceptionBuilder {

    @NonNull
    public static <T extends RuntimeException> T buildException(@NonNull Class<T> exceptionClass, @NonNull @Size(min = 1) String msg) {
        /*try {
            Constructor<T> constructor = exceptionClass.getConstructor(exceptionClass.getClass());
            return constructor.newInstance(new Object(){});
        } catch (Exception e) {
            return null;
        }*/
        return null;
    }
}
