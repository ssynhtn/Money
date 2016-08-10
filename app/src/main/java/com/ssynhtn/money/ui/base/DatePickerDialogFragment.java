package com.ssynhtn.money.ui.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import com.ssynhtn.money.R;

import java.util.Calendar;

/**
 * Created by Garment on 2016/6/1.
 */
public class DatePickerDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String EXTRA_DIALOG_TITLE = "EXTRA_DIALOG_TITLE";
    private static final String EXTRA_DEFAULT_DATE = "EXTRA_DEFAULT_DATE";
    private static final String EXTRA_MAX_DATE = "EXTRA_MAX_DATE";
    private static final String EXTRA_MIN_DATE = "EXTRA_MIN_DATE";
    private static final String EXTRA_FORCE_PICK = "EXTRA_FORCE_PICK";
    private static final String TAG = DatePickerDialogFragment.class.getSimpleName();

    private DatePickerDialogFragmentCallback mDatePickerDialogFragmentCallback;
    private DatePicker mDatePicker;

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_NEGATIVE) {
            if (mDatePickerDialogFragmentCallback != null) {
                mDatePickerDialogFragmentCallback.onPickCancelled(getTag());
            }
        }
    }

    public static interface DatePickerDialogFragmentCallback {
        void onPickDate(String tag, int year, int month, int dayOfMonth);
        void onPickCancelled(String tag);
    }

    public static DatePickerDialogFragment newInstance(String title, long defaultDate, long minDate, long maxDate, boolean forcePick) {
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_DIALOG_TITLE, title);
        args.putLong(EXTRA_DEFAULT_DATE, defaultDate);
        args.putLong(EXTRA_MAX_DATE, maxDate);
        args.putLong(EXTRA_MIN_DATE, minDate);
        args.putBoolean(EXTRA_FORCE_PICK, forcePick);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean forcePick = getArguments().getBoolean(EXTRA_FORCE_PICK, false);
        if (forcePick) {
            setCancelable(false);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mDatePickerDialogFragmentCallback = (DatePickerDialogFragmentCallback) activity;
        } catch (ClassCastException e) {
            Log.d(TAG, "activity " + activity.getClass().getSimpleName() + " should implement interface " + TAG);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mDatePickerDialogFragmentCallback = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(args.getString(EXTRA_DIALOG_TITLE))
                .setPositiveButton(getString(R.string.button_comfirm), null);

        if (!args.getBoolean(EXTRA_FORCE_PICK, false)) {
            builder.setNegativeButton(getString(R.string.button_cancel), this);
        }



        View view = LayoutInflater.from(getActivity()).inflate(R.layout.date_picker_dialog_view, null);
        builder.setView(view);

        mDatePicker = (DatePicker) view.findViewById(R.id.datePicker);

        long defaultDate = args.getLong(EXTRA_DEFAULT_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(defaultDate);

        long maxDate = args.getLong(EXTRA_MAX_DATE);
        long minDate = args.getLong(EXTRA_MIN_DATE);

        Log.d(TAG, "minDate: " + minDate + ", defaultDate: " + defaultDate + ", maxDate: " + maxDate + ", min <= default: " + (minDate <= defaultDate) + ", default <= max: " + (defaultDate <= maxDate));

        if (maxDate != -1) {
            mDatePicker.setMaxDate(maxDate);
        }

        if (minDate != -1) {
            mDatePicker.setMinDate(minDate);
        }

        // 乐视手机有bug，这里必须将updateDate放在setMinDate后面
        mDatePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                AlertDialog alertDialog = (AlertDialog) dialog;
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mDatePickerDialogFragmentCallback != null) {
                            mDatePickerDialogFragmentCallback.onPickDate(getTag(), mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
                        }
                    }
                });
            }
        });

        return dialog;
    }
}
