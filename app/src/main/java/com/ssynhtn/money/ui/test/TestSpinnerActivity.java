package com.ssynhtn.money.ui.test;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ssynhtn.money.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSpinnerActivity extends AppCompatActivity {

    private static final String TAG = TestSpinnerActivity.class.getSimpleName();

    private TextView mTextView;
    private Spinner mSpinner;
    private BaseAdapter mAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_spinner);
        findViews();

        mAdapter = new SimpleSpinnerAdapter(this, createData());
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "parent: " + parent + ", view: " + view + ", position: " + position + ", childCount: " + mSpinner.getChildCount() + ", itemCount: " + mAdapter.getCount());

                if (view != null) {
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        if (parent.getChildAt(i) == view) {
                            Log.d(TAG, "view is ith child: " + i);
                            break;
                        }
                    }
                }

                mTextView.setText(mAdapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTextView.setText(null);
            }
        });

        mSpinner.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mSpinner.setDropDownVerticalOffset(mSpinner.getHeight());
                    mSpinner.setDropDownHorizontalOffset(100);     // (mSpinner.getWidth() - mSpinner.getDropDownWidth()) / 2
                    Log.d(TAG, "spinner width: " + mSpinner.getWidth() + ", dropDownWidth: " + mSpinner.getDropDownWidth() + ", textViewWidth: " + mTextView.getWidth());
                }
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpinner.performClick();
            }
        });

    }

    private List<String> createData() {
        List<String> data = new ArrayList<>();

        data.addAll(Arrays.asList("hello world google chrome android some_what_long java ruby cat".split(" ")));   // someverylongtextthatisstrangeandweirdandletussee

        return data;
    }

    private void findViews() {
        mTextView = (TextView) findViewById(R.id.text_view);
        mSpinner = (Spinner) findViewById(R.id.spinner);
    }

    private static class SimpleSpinnerAdapter extends BaseAdapter {

        private final Context mContext;
        private final List<String> mData;

        public SimpleSpinnerAdapter(Context context, List<String> data) {

            mContext = context;
            mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            }

            TextView textView = (TextView) convertView;
            textView.setText(getItem(position).toString());
            return convertView;
        }
    }

}
