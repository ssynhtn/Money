package com.ssynhtn.money.ui.test;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.base.BaseActivity;
import com.ssynhtn.money.ui.test.view.FrameLayoutWithTranslationFraction;

public class TestAnimatorActivity extends BaseActivity {

    private TextView mTextView;
    private FrameLayoutWithTranslationFraction mFrameLayoutWithTranslationFraction;
    private Button mButton;

    private TextView mTextViewBig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animator);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.text_view);
        mFrameLayoutWithTranslationFraction = (FrameLayoutWithTranslationFraction) findViewById(R.id.frame_layout);
        mTextViewBig = (TextView) findViewById(R.id.text_view_big);

        mFrameLayoutWithTranslationFraction.setVisibility(View.GONE);

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
        if (mFrameLayoutWithTranslationFraction.getVisibility() != View.VISIBLE) {
            mFrameLayoutWithTranslationFraction.setVisibility(View.VISIBLE);
            Animator pushUp = AnimatorInflater.loadAnimator(this, R.animator.push_up_in);

            pushUp.setTarget(mFrameLayoutWithTranslationFraction);
            pushUp.start();
        } else {
            Animator pushDown = AnimatorInflater.loadAnimator(this, R.animator.push_down_out);

            pushDown.setTarget(mFrameLayoutWithTranslationFraction);
            pushDown.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    mFrameLayoutWithTranslationFraction.setVisibility(View.GONE);
                }
            });
            pushDown.start();
        }
    }
}
