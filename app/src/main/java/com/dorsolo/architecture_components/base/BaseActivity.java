package com.dorsolo.architecture_components.base;

import android.os.Bundle;

import com.dorsolo.architecture_components.utilites.AppUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private AppUtils appUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUtils = new AppUtils(this);
    }

    public AppUtils getAppUtils() {
        return appUtils;
    }
}
