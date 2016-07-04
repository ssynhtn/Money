package com.ssynhtn.money.ui.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewAnimator;

import com.ssynhtn.money.R;

public class TestViewAnimatorActivity extends AppCompatActivity {

    private static final String TAG = TestViewAnimatorActivity.class.getSimpleName();

    private ViewAnimator mViewAnimator;
    private Button mButton;
    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_animator);

        mViewAnimator = (ViewAnimator) findViewById(R.id.viewFlipper);
        mViewAnimator.bringToFront();

        mButton = (Button) findViewById(R.id.button_flip);
        mContentView = findViewById(R.id.text_view_app);


        mViewAnimator.setInAnimation(this, R.anim.push_up_in);

        Animation pushDownOut = AnimationUtils.loadAnimation(this, R.anim.push_down_out);
        pushDownOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mViewAnimator.getCurrentView() != mContentView) {
                    mViewAnimator.setVisibility(View.GONE);
                }

                logViews();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mViewAnimator.setOutAnimation(pushDownOut);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewAnimator.setVisibility(View.VISIBLE);
                mViewAnimator.bringToFront();
                mViewAnimator.showNext();
//                mViewAnimator.setVisibility(mViewAnimator.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

    }

    private void logViews() {
        ViewGroup contentView = (ViewGroup) findViewById(R.id.frame_layout);

        for (int i = 0; i < contentView.getChildCount(); i++) {
            Log.d(TAG, "child view: " + contentView.getChildAt(i));
        }
    }
}
