package com.ssynhtn.money.ui.test;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.ssynhtn.money.R;

public class TestAnimationDrawableActivity extends AppCompatActivity {

    private static final String TAG = TestAnimationDrawableActivity.class.getSimpleName();
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation_drawable);

        mImageView = (ImageView) findViewById(R.id.image_view);


        mImageView.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
                animationDrawable.start();
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Log.d(TAG, "hasFocus: " + hasFocus);
//        if (hasFocus) {
//
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            AnimationDrawable animationDrawable = (AnimationDrawable) mImageView.getDrawable();
            animationDrawable.start();
            return true;
        }

        return super.onTouchEvent(event);
    }
}
