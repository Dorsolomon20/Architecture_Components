package com.dorsolo.architecture_components.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.dorsolo.architecture_components.MainListener;
import com.dorsolo.architecture_components.utilites.AppUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private AppUtils appUtils;
    private MainListener mainListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUtils = new AppUtils(requireActivity(), this);
    }

    public AppUtils getAppUtils() {
        return appUtils;
    }

    protected MainListener getMainListener() {
        return mainListener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mainListener = (MainListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            mainListener = (MainListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
