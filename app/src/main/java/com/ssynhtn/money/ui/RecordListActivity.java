package com.ssynhtn.money.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ssynhtn.money.R;
import com.ssynhtn.money.model.MoneyBook;
import com.ssynhtn.money.ui.base.BaseActivity;

public class RecordListActivity extends BaseActivity {

    private static final String EXTRA_MONEY_BOOK = "EXTRA_MONEY_BOOK";
    private MoneyBook mMoneyBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        mMoneyBook = (MoneyBook) getIntent().getSerializableExtra(EXTRA_MONEY_BOOK);

        initToolbar(mMoneyBook.mName);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, RecordListFragment.newInstance(mMoneyBook)).commit();
        }
    }

    public static void start(Context context, MoneyBook moneyBook) {
        Intent intent = new Intent(context, RecordListActivity.class);
        intent.putExtra(EXTRA_MONEY_BOOK, moneyBook);
        context.startActivity(intent);
    }
}
