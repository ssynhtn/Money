package com.ssynhtn.money.ui.test;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.base.BaseActivity;

public class TestAnimationActivity extends BaseActivity {

    private TextView mTextView;
    private Button mButton;

    private TextView mTextViewBig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.text_view);
        mTextViewBig = (TextView) findViewById(R.id.text_view_big);

        mTextView.setVisibility(View.GONE);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateView();
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(mTextView.getText().toString());
            }
        });

        mTextViewBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(mTextViewBig.getText().toString());
            }
        });
    }

    private void animateView() {
        if (mTextView.getVisibility() != View.VISIBLE) {
            mTextView.setVisibility(View.VISIBLE);
            mTextView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_up_in));
        } else {
            Animation out = AnimationUtils.loadAnimation(this, R.anim.push_down_out);
            out.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mTextView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mTextView.startAnimation(out);
        }
    }
}
