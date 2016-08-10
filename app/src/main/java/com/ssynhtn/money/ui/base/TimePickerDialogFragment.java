package com.ssynhtn.money.ui.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

import com.ssynhtn.money.R;

import org.joda.time.DateTime;

/**
 * Created by Garment on 2016/7/5.
 */
public class TimePickerDialogFragment extends DialogFragment {

    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_TIME = "EXTRA_TIME";

    private TimePicker mTimePicker;
    private TimePickerDialogFragmentCallback mCallback;

    public static interface TimePickerDialogFragmentCallback {
        void onPickTime(String tag, int hour, int minute);
        void onPickCancelled(String tag);
    }

    public static TimePickerDialogFragment newInstance(String title, long timeMillis) {
        TimePickerDialogFragment fragment = new TimePickerDialogFragment();
        Bundle args = new Bundle();

        args.putString(EXTRA_TITLE, title);
        args.putLong(EXTRA_TIME, timeMillis);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof TimePickerDialogFragmentCallback) {
            mCallback = (TimePickerDialogFragmentCallback) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCallback = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(getArguments().getString(EXTRA_TITLE));

        View view = View.inflate(getActivity(), R.layout.time_picker_dialog_view, null);
        mTimePicker = (TimePicker) view.findViewById(R.id.time_picker);

        DateTime dateTime = new DateTime(getArguments().getLong(EXTRA_TIME));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(dateTime.getHourOfDay());
            mTimePicker.setMinute(dateTime.getMinuteOfHour());
        } else {
            mTimePicker.setCurrentHour(dateTime.getHourOfDay());
            mTimePicker.setCurrentMinute(dateTime.getMinuteOfHour());
        }

        builder.setView(view);
        builder.setPositiveButton(getString(R.string.button_comfirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mCallback != null) {
                    // sometimes these deprecations are so ugly, they just change the method name
                    mCallback.onPickTime(getTag(), mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute());
                }
            }
        }).setNegativeButton(getString(R.string.button_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mCallback != null) {
                    mCallback.onPickCancelled(getTag());
                }
            }
        });


        return builder.create();
    }

}
