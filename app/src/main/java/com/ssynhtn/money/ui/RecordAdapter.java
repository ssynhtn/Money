package com.ssynhtn.money.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.model.Record;

/**
 * Created by Garment on 2016/6/28.
 */
public class RecordAdapter extends CursorAdapter {
    public RecordAdapter(Context context) {
        super(context, null, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.record_item_view, parent, false);
        view.setTag(new ViewHolder(view));

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        Record record = Record.fromCursor(cursor);
        viewHolder.mTextView.setText("created: " + record.mCreateTime);
        viewHolder.mRecord = record;
    }

    public static class ViewHolder {
        public Record mRecord;
        public TextView mTextView;

        public ViewHolder(View view) {
            mTextView = (TextView) view.findViewById(R.id.text_view);
        }


    }
}
