package com.ssynhtn.money.ui.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ScrollView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.base.BaseActivity;

public class TestFloatingActionButtonActivity extends BaseActivity {

    private FloatingActionButton mFloatingActionButton;
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_floating_action_button);

        initToolbar();

        mScrollView = (ScrollView) findViewById(R.id.scroll_view);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ViewCompat.canScrollVertically(mScrollView, -1)) {
                    mScrollView.smoothScrollTo(0, 0);
                } else {
                    mScrollView.smoothScrollTo(0, mScrollView.getHeight());     // getHeight 貌似不对，不过至少应该有滚动效果
                }
            }
        });
    }
}
