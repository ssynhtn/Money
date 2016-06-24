package com.ssynhtn.money.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.test.TestSearchViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garment on 2016/6/24.
 */
public class TestFragment extends Fragment {

    public static class TestItem {
        public String mTitle;
        public Intent mIntent;

        public TestItem(String title, Intent intent) {
            mTitle = title;
            mIntent = intent;
        }

    }

    private ListView mListView;
    private TestAdapter mTestAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        mListView = (ListView) view.findViewById(R.id.list_view);
        mTestAdapter = new TestAdapter(getActivity(), createData());
        mListView.setAdapter(mTestAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TestItem item = (TestItem) mTestAdapter.getItem(position);
                if (item.mIntent != null && item.mIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(item.mIntent);
                }
            }
        });

        return view;
    }

    private List<TestItem> createData() {
        List<TestItem> data = new ArrayList<>();

        data.add(new TestItem(TestSearchViewActivity.class.getSimpleName(), new Intent(getActivity(), TestSearchViewActivity.class)));

        return data;
    }

    private static class TestAdapter extends BaseAdapter {

        private final Context mContext;
        private final List<TestItem> mData;

        public TestAdapter(Context context, List<TestItem> data) {
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
                convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            TestItem item = mData.get(position);
            textView.setText(item.mTitle);

            return convertView;
        }
    }
}
