package com.dorsolo.architecture_components.base;

import com.dorsolo.architecture_components.MainListener;
import com.dorsolo.architecture_components.utilites.AppUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends PagedListAdapter<T, VH> {

    private AppUtils appUtils;
    private MainListener mainListener;

    protected BaseRecyclerAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback, @NonNull FragmentActivity activity, @NonNull MainListener mainListener) {
        super(diffCallback);
        appUtils = new AppUtils(activity);
        this.mainListener = mainListener;
    }

    public AppUtils getAppUtils() {
        return appUtils;
    }

    public MainListener getMainListener() {
        return mainListener;
    }
}
