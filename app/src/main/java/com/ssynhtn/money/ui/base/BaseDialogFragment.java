package com.ssynhtn.money.ui.base;

import android.support.v4.app.DialogFragment;

import com.ssynhtn.money.utils.ToastUtils;

/**
 * Created by Garment on 2016/6/28.
 */
public class BaseDialogFragment extends DialogFragment {

    public void makeToast(String text) {
        ToastUtils.getInstance().makeToast(getActivity(), text);
    }

    public void makeToast(Throwable error) {
        ToastUtils.getInstance().makeToast(getActivity(), error);
    }
}
