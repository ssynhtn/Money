package com.ssynhtn.money.ui.test;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.ssynhtn.money.R;

public class TestPopupWindowActivity extends AppCompatActivity {

    private static final String TAG = TestPopupWindowActivity.class.getSimpleName();

    private Button mButton;
    private View mBottomView;

    private EditText mEditTextOffsetX;
    private EditText mEditTextOffsetY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_popup_window);

        mBottomView = findViewById(R.id.bottom_view);

        mEditTextOffsetX = (EditText) findViewById(R.id.edit_text_offset_x);
        mEditTextOffsetY = (EditText) findViewById(R.id.edit_text_offset_y);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWindow(mButton);

//                animateView(mButton);
            }
        });
        mBottomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWindow(findViewById(android.R.id.content));
            }
        });


    }

    private void animateView(View view) {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.expand_then_shrink);
        animator.setTarget(view);
        animator.start();
    }

    private void showWindow(View anchorView) {
        PopupWindow window = new PopupWindow(this);

        View view = View.inflate(this, R.layout.popup_window, null);
        ViewGroup.LayoutParams params = view.getLayoutParams();

        Log.d(TAG, "param should be null: " + params);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);

        window.setContentView(view);
        window.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

//        Log.d(TAG, "button token: " + mButton.getWindowToken() + ", bottomViewToken: " + mBottomView.getWindowToken() + ", contentViewToken: " + findViewById(android.R.id.content).getWindowToken());
//        window.showAtLocation(mButton, Gravity.LEFT|Gravity.TOP, 0, 0);
//        window.showAsDropDown(mButton);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            window.showAsDropDown(mButton, 0, 0, Gravity.TOP);
//        } else {
//            window.showAsDropDown(mButton, 0, 0);
//        }

//        window.showAsDropDown(anchorView, getIntFromEditText(mEditTextOffsetX), getIntFromEditText(mEditTextOffsetY));
//        window.showAtLocation(anchorView, Gravity.TOP | Gravity.LEFT, 0, 0);
        window.setAnimationStyle(R.style.PopupAnimationPushUp);
        window.showAtLocation(anchorView, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
//        window.setAnimationStyle(android.R.style.Animation_D);
    }

    private int getIntFromEditText(EditText editText) {
        String text = editText.getText().toString();

        try {
            return Integer.parseInt(text);
        } catch (Exception ignored) {

        }

        return 0;
    }
}
