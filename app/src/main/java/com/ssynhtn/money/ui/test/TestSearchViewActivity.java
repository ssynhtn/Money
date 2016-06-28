package com.ssynhtn.money.ui.test;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.base.BaseActivity;

public class TestSearchViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_search_view);

        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_test_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            makeToast("click on action item: " + item.getTitle());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
