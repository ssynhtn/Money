package com.ssynhtn.money.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.model.MoneyBook;
import com.ssynhtn.money.model.Record;
import com.ssynhtn.money.ui.base.BaseActivity;
import com.ssynhtn.money.ui.base.DatePickerDialogFragment;
import com.ssynhtn.money.ui.base.TimePickerDialogFragment;
import com.ssynhtn.money.utils.TimeUtils;

public class EditRecordActivity extends BaseActivity implements DatePickerDialogFragment.DatePickerDialogFragmentCallback, TimePickerDialogFragment.TimePickerDialogFragmentCallback {


    private static final String EXTRA_MONEY_BOOK = "EXTRA_MONEY_BOOK";
    private static final String EXTRA_RECORD = "EXTRA_RECORD";
    private static final String TAG_DATE_PICKER_DIALOG = "TAG_DATE_PICKER_DIALOG";
    private static final String TAG_TIME_PICKER_DIALOG = "TAG_TIME_PICKER_DIALOG";

    private static final String TAG = EditRecordActivity.class.getSimpleName();

    private MoneyBook mMoneyBook;
    private Record mRecord;

    private TextView mDateTextView;
    private TextView mTimeTextView;

    private EditText mMoneyEditText;

    private Spinner mMoneyInOrOutSpinner;
    private Spinner mOwnerSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        initToolbar();
        findViews();

        Intent intent = getIntent();
        mMoneyBook = (MoneyBook) intent.getSerializableExtra(EXTRA_MONEY_BOOK);
        if (intent.hasExtra(EXTRA_RECORD)) {
            mRecord = (Record) intent.getSerializableExtra(EXTRA_RECORD);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_RECORD)) {
            mRecord = (Record) savedInstanceState.getSerializable(EXTRA_RECORD);
        }

        if (mRecord == null) {
            mRecord = Record.newInstance(mMoneyBook);
        }

        mDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DatePickerDialogFragment.newInstance("选择日期", mRecord.mCreateTime, -1, -1, false), TAG_DATE_PICKER_DIALOG);
            }
        });

        mTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TimePickerDialogFragment.newInstance("选择时间", mRecord.mCreateTime), TAG_TIME_PICKER_DIALOG);
            }
        });

        updateViews();

    }



    private void updateViews() {
        // TODO: 2016/7/5
        mDateTextView.setText(TimeUtils.simpleDate(mRecord.mCreateTime, "."));
        mTimeTextView.setText(TimeUtils.simpleTime(mRecord.mCreateTime));
    }

    private void findViews() {
        mDateTextView = (TextView) findViewById(R.id.text_view_date);
        mTimeTextView = (TextView) findViewById(R.id.text_view_time);

    }

    public static void start(Context context, MoneyBook moneyBook) {
        Intent intent = new Intent(context, EditRecordActivity.class);
        intent.putExtra(EXTRA_MONEY_BOOK, moneyBook);

        context.startActivity(intent);
    }

    private void saveRecord(Record record) {}
    private void updateRecord(Record record) {}

    @Override
    public void onPickDate(String tag, int year, int month, int dayOfMonth) {
        if (TAG_DATE_PICKER_DIALOG.equals(tag)) {
            dismissDialogIfExists(TAG_DATE_PICKER_DIALOG);
            mRecord.setDate(year, month, dayOfMonth);
            updateViews();
        }
    }

    @Override
    public void onPickTime(String tag, int hour, int minute) {
        if (TAG_TIME_PICKER_DIALOG.equals(tag)) {
            mRecord.setTime(hour, minute);
            updateViews();
        }
    }

    @Override
    public void onPickCancelled(String tag) {
        // do nothing
    }
}
