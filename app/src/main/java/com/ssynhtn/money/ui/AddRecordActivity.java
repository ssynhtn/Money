package com.ssynhtn.money.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.model.MoneyBook;
import com.ssynhtn.money.ui.base.BaseActivity;

public class AddRecordActivity extends BaseActivity {


    private static final String EXTRA_MONEY_BOOK = "EXTRA_MONEY_BOOK";

    private MoneyBook mMoneyBook;

    private TextView mDateTextView;
    private TextView mTimeTextView;

    private EditText mMoneyEditText;

    private Spinner mMoneyInOrOutSpinner;
    private Spinner mOwnerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        initToolbar();

        mMoneyBook = (MoneyBook) getIntent().getSerializableExtra(EXTRA_MONEY_BOOK);

    }

    public static void start(Context context, MoneyBook moneyBook) {
        Intent intent = new Intent(context, AddRecordActivity.class);
        intent.putExtra(EXTRA_MONEY_BOOK, moneyBook);

        context.startActivity(intent);
    }
}
