package com.dorsolo.architecture_components.utilites;

import android.widget.Toast;

import androidx.annotation.IntDef;

import static com.dorsolo.architecture_components.utilites.Constants.Background.DELETE;
import static com.dorsolo.architecture_components.utilites.Constants.Background.DELETE_ALL;
import static com.dorsolo.architecture_components.utilites.Constants.Background.INSERT;
import static com.dorsolo.architecture_components.utilites.Constants.Background.UPDATE;

public @interface Annotations {

    /**
     *
     */
    @interface General {

        /**
         *
         */
        @IntDef(value = {Toast.LENGTH_SHORT, Toast.LENGTH_LONG})
        @interface ToastLength {

        }
    }

    @interface Background {
        @IntDef({INSERT, DELETE, UPDATE, DELETE_ALL})
        @interface ActionType {

        }

    }
}