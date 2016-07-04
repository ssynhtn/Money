package com.ssynhtn.money.custom;

import android.util.Log;
import android.view.View;

/**
 * Created by Garment on 2016/3/21.
 */
public class OnMultipleClickListener implements View.OnClickListener {

    private static final long CONSECUTIVE_CLICK_INTERVAL = 400L;
    private static final String TAG = OnMultipleClickListener.class.getSimpleName();

    public static interface OnConsecutiveClickCallback {
        /**
         * @param view
         * @param listener
         */
        void onConsecutiveClick(View view, OnMultipleClickListener listener, int numberOfClicks, int numberOfClicksRequired);
    }

    private int mCurrentNumberOfClicks;
    private int mNumberOfClicksRequired;
    private long mLastClickTime;

    private View mView;
    private OnConsecutiveClickCallback mCallback;

    public OnMultipleClickListener(View view, int numberOfClicksRequired, OnConsecutiveClickCallback callback) {
        mNumberOfClicksRequired = numberOfClicksRequired;
        mCallback = callback;
        mView = view;

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        long currentTime = System.currentTimeMillis();

        if (mCurrentNumberOfClicks <= 0 || mCurrentNumberOfClicks >= mNumberOfClicksRequired) {
            Log.d(TAG, "first click");
            mCurrentNumberOfClicks = 1;
        } else {
            if (currentTime - mLastClickTime > CONSECUTIVE_CLICK_INTERVAL) {
                Log.d(TAG, "last click " + mLastClickTime + " too old, now " + currentTime + ", time elapsed: " + (currentTime - mLastClickTime) + ", resetting click count to 1");
                mCurrentNumberOfClicks = 1;
            } else {
                Log.d(TAG, "consecutive click, last was: " + mCurrentNumberOfClicks + ", now: " + (mCurrentNumberOfClicks + 1));
                mCurrentNumberOfClicks++;
            }
        }

        mLastClickTime = currentTime;

        if (mCallback != null) {
            mCallback.onConsecutiveClick(mView, this, mCurrentNumberOfClicks, mNumberOfClicksRequired);
        }
    }

    public int getCurrentNumberOfClicks() {
        return mCurrentNumberOfClicks;
    }

    public int getNumberOfClicksRequired() {
        return mNumberOfClicksRequired;
    }
}
