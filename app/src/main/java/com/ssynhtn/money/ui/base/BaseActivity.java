package com.ssynhtn.money.ui.base;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ssynhtn.money.R;
import com.ssynhtn.money.utils.ToastUtils;

/**
 * Created by Garment on 2016/6/28.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private static final String TAG_DIALOG = "TAG_DIALOG_";


    protected void initToolbar() {
        initToolbar(null);
    }

    protected void initToolbar(CharSequence title) {
        if (getSupportActionBar() == null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShowHomeAsUp());

            if (title != null) {
                getSupportActionBar().setTitle(title);
            }
        }
    }


    public void makeToast(String text) {
        ToastUtils.getInstance().makeToast(this, text);
    }

    public void makeToast(Throwable error) {
        ToastUtils.getInstance().makeToast(this, error);
    }

    protected boolean isShowHomeAsUp() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void showDialog(DialogFragment dialogFragment, String tag) {
        dismissDialogIfExists(tag);
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        dialogFragment.show(ft, tag);
    }

    protected void dismissDialogIfExists(String tag) {
        FragmentManager fm = getSupportFragmentManager();

        DialogFragment dialogFragment = (DialogFragment) fm.findFragmentByTag(tag);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    protected String buildTagForDialog(Class<? extends DialogFragment> clazz) {
        return TAG_DIALOG + clazz.getCanonicalName();
    }

    protected void hideSoftKeyboard() {
        View focusedView = getCurrentFocus();

        if (focusedView != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

}
