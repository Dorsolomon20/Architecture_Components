package com.dorsolo.architecture_components.utilites;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dorsolo.architecture_components.R;
import com.dorsolo.architecture_components.base.BaseDialogFragment;
import com.dorsolo.architecture_components.base.BaseFragment;
import com.dorsolo.architecture_components.builders.RuntimeExceptionBuilder;
import com.dorsolo.architecture_components.utilites.Annotations.General.ToastLength;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.StringRes;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

@MainThread
public final class AppUtils {

    private FragmentActivity activity;
    private Fragment fragment;

    public AppUtils(FragmentActivity activity) {
        Precondition.checkCondition(RuntimeExceptionBuilder.buildException(RuntimeException.class, "Activity variable found to be null"), activity != null);
        this.activity = activity;
    }

    public AppUtils(FragmentActivity activity, Fragment fragment) {
        Precondition.checkCondition(RuntimeExceptionBuilder.buildException(RuntimeException.class, "One or more of the required params found to be null"), activity != null && fragment != null);
        this.activity = activity;
        this.fragment = fragment;
    }

    /**
     * Launch a new fragment to the foreground into the fragmentMainContainer FrameLayout
     *
     * @param fragment       the fragment to be launched
     * @param addToBackStack boolean whether or not to add the fragment to the backStack
     * @param tag            String which represent the tag of the launched fragment, can be used to find it and get a pointer to it
     * @param animations     int varargs which are the animations references to be added can only be nothing (0) for no animation 2 or 4
     */
    public final void launchFragment(@NonNull BaseFragment fragment, boolean addToBackStack, String tag, @AnimRes @AnimatorRes int... animations) {
        Precondition.checkCondition(RuntimeExceptionBuilder.buildException(RuntimeException.class, "Animations length must be one of the following (0, 2, 4)"),
                animations.length == 0 || animations.length == 2 || animations.length == 4);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if (addToBackStack)
            transaction.addToBackStack(null);
        if (animations.length == 2)
            transaction.setCustomAnimations(animations[0], animations[1]);
        else if (animations.length == 4)
            transaction.setCustomAnimations(animations[0], animations[1], animations[2], animations[3]);
        transaction.replace(R.id.fragmentMainContainer, fragment, tag).commit();
    }

    /**
     * Launch a new dialog fragment to the foreground
     *
     * @param dialogFragment BaseDialogFragment obj instance to be displayed
     * @param tag            String value which represent the tag of the launched dialog fragment
     */
    public final <T extends BaseDialogFragment> void launchDialogFragment(@NonNull T dialogFragment, @Nullable String tag) {
        dialogFragment.show(activity.getSupportFragmentManager(), tag);
    }

    /**
     * Clear text for an undefined amount of views that extends from TextView
     *
     * @param views The views to clear the text from
     * @param <T>   Generic Type object that must extends from the TextView class
     */
    @SafeVarargs
    public final <T extends TextView> void clearText(@NonNull T... views) {
        for (TextView view : views) {
            Precondition.checkCondition(RuntimeExceptionBuilder.buildException(RuntimeException.class, "View can't be null, please check the giving params"), view != null);
            view.setText("");
        }
    }

    /**
     * Checks whether there's a permission for an operation
     *
     * @param permission     String which represent the permission to check
     * @param permissionCode int which is the code for the permission request for the onRequestPermissionsResult
     * @param toFragment     boolean for whether to return the answer to the fragment or activity
     * @return boolean whether there's a permission or not, True - permission granted, false otherwise
     */
    public final boolean checkPermission(@NonNull String permission, int permissionCode, boolean toFragment) {
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED)
            return true;
        if (toFragment) {
            Precondition.checkCondition(RuntimeExceptionBuilder.buildException(RuntimeException.class, "Requested result for fragment, Fragment instance giving in constructor found to be null"), fragment != null);
            fragment.requestPermissions(new String[]{permission}, permissionCode);
        } else
            ActivityCompat.requestPermissions(activity, new String[]{permission}, permissionCode);
        return false;
    }

    /**
     * Launch an intent and retrieve the result to a fragment or activity based on the toFragment boolean
     *
     * @param intent      the intent to be launched
     * @param toFragment  boolean to whether return result to the fragment or activity
     * @param requestCode the request code for the onActivityResult
     */
    private void targetHandler(@NonNull Intent intent, boolean toFragment, int requestCode) {
        if (toFragment) {
            Precondition.checkCondition(RuntimeExceptionBuilder.buildException(RuntimeException.class, "Requested result for fragment, Fragment instance giving in constructor found to be null"), fragment != null);
            fragment.startActivityForResult(intent, requestCode);
        } else
            activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Invoked in order to populate a list of views with the same onClickListener
     *
     * @param listener OnClickListener obj instance which will be attached to every view in the provided array
     * @param views    Array of views which we attach the Listener to
     */
    public final void setOnClickListener(@NonNull View.OnClickListener listener, @NonNull @Size(min = 1) View[] views) {
        for (View view : views) {
            Precondition.checkCondition(RuntimeExceptionBuilder.buildException(RuntimeException.class, "View can't be null, please check the giving params"), view != null);
            view.setOnClickListener(listener);
        }
    }

    /**
     * Remove all of the fragments from the backStack if there are any
     */
    public final void clearBackStack() {
        activity.getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Invoked in order to pop one fragment of the fragments stack
     */
    public final void popFragment() {
        activity.getSupportFragmentManager().popBackStack();
    }

    /**
     * Toast a message to the user based on a reference to a string in the Strings.xml file
     *
     * @param msg    the reference to the Strings.xml file
     * @param length Integer value which represent the length of the toast
     */
    public final void toastMsg(@StringRes int msg, @ToastLength int length) {
        Toast.makeText(activity, msg, length).show();
    }

    /**
     * Toast a message to the user based on the provided string
     *
     * @param msg    String value which is the String value to Toast
     * @param length Integer value which represent the length of the toast
     */
    public final void toastMsg(@NonNull String msg, @ToastLength int length) {
        Toast.makeText(activity, msg, length).show();
    }
}
