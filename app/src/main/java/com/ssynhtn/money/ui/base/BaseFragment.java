package com.ssynhtn.money.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ssynhtn.money.utils.ToastUtils;

/**
 * Created by Garment on 2016/6/28.
 */
public class BaseFragment extends Fragment {

    private static final String TAG_DIALOG = "TAG_DIALOG_";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    public void makeToast(String text) {
        ToastUtils.getInstance().makeToast(getActivity(), text);
    }

    public void makeToast(Throwable error) {
        ToastUtils.getInstance().makeToast(getActivity(), error);
    }

    protected void showDialog(DialogFragment dialogFragment) {
        showDialog(dialogFragment, buildTagForDialog(dialogFragment.getClass()));
    }
    protected void showDialog(DialogFragment dialogFragment, String tag) {
        dismissDialogIfExists(tag);
        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        dialogFragment.show(ft, tag);
    }

    protected void dismissDialogIfExists(String tag) {
        FragmentManager fm = getFragmentManager();

        DialogFragment dialogFragment = (DialogFragment) fm.findFragmentByTag(tag);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    protected String buildTagForDialog(Class<? extends DialogFragment> clazz) {
        return TAG_DIALOG + clazz.getCanonicalName();
    }
}
