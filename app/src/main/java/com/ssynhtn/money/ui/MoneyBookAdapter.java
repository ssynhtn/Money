package com.ssynhtn.money.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.model.MoneyBook;

/**
 * Created by Garment on 2016/6/21.
 */
public class MoneyBookAdapter extends CursorAdapter {
    public MoneyBookAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.money_book_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        MoneyBook moneyBook = MoneyBook.fromCursor(cursor);

        viewHolder.mNameTextView.setText(moneyBook.mName);
        viewHolder.mMoneyBook = moneyBook;
    }

    public static class ViewHolder {
        public MoneyBook mMoneyBook;
        public TextView mNameTextView;

        public ViewHolder(View view) {
            mNameTextView = (TextView) view.findViewById(R.id.text_view_name);
        }
    }
}
