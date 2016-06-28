package com.ssynhtn.money.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ssynhtn.money.utils.ToastUtils;

/**
 * Created by Garment on 2016/6/28.
 */
public class BaseFragment extends Fragment {

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
}
