package com.ssynhtn.money.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ssynhtn.money.R;
import com.ssynhtn.money.utils.ToastUtils;

/**
 * Created by Garment on 2016/6/28.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();


    protected void initToolbar() {
        if (getSupportActionBar() == null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShowHomeAsUp());
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
}
