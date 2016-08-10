package com.ssynhtn.money.ui.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRecyclerViewActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler_view);

        initToolbar();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MyAdapter(new ArrayList<>(Arrays.asList("hello world this is good but say ho do you do".split(" "))), this);

        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.getSpanSize(position);
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);


//        mRecyclerView.addItemDecoration(new MyItemDecoration());

    }

    private static class MyItemDecoration extends RecyclerView.ItemDecoration {

        private Paint mPaint;

        public MyItemDecoration() {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(Color.MAGENTA);
            mPaint.setStrokeWidth(10);
            mPaint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

            c.drawRect(0, 0, c.getWidth(), c.getHeight(), mPaint);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

//            c.drawRect(1, 1, c.getWidth() - 1 , c.getHeight() - 1, mPaint);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_top) {
            addTop();
            return true;
        } else if (id == R.id.action_add_bottom) {
            addBottom();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addBottom() {
        long now = System.currentTimeMillis() % 100;
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            data.add(Long.toString(now + i));
        }

        mAdapter.add(data, 5);
    }

    private void addTop() {
        long now = System.currentTimeMillis() % 100;
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            data.add(Long.toString(now + i));
        }

        mAdapter.add(data);

    }

    @Override
    public void onClick(View v) {
        BaseViewHolder viewHolder = (BaseViewHolder) v.getTag();
        if (viewHolder instanceof TextViewHolder) {
            switch (v.getId()) {
                case R.id.text_view_title: {
                    makeSnackBar("click on title: " + mAdapter.getItem(viewHolder.getAdapterPosition()));
                    break;
                }

                case R.id.text_view_description: {
                    makeSnackBar("click on description: " + mAdapter.getItem(viewHolder.getAdapterPosition()));
                    break;
                }
            }
        }
    }

    private void makeSnackBar(String text) {
        final Snackbar snackbar = Snackbar.make(mRecyclerView, text, Snackbar.LENGTH_SHORT);
        snackbar.setAction("action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestRecyclerViewActivity.this, "click on snackbar action", Toast.LENGTH_SHORT).show();
                snackbar.dismiss();
            }
        });

        snackbar.show();

    }

    private static abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindData(MyAdapter adapter, int position);
    }

    private static class TextViewHolder extends BaseViewHolder {
        public TextView mTextViewTitle;
        public TextView mTextViewDescription;

        public TextViewHolder(View itemView, View.OnClickListener listener) {
            super(itemView);

            mTextViewTitle = (TextView) itemView.findViewById(R.id.text_view_title);
            mTextViewDescription = (TextView) itemView.findViewById(R.id.text_view_description);

            mTextViewTitle.setOnClickListener(listener);
            mTextViewDescription.setOnClickListener(listener);

            itemView.setTag(this);
            mTextViewTitle.setTag(this);
            mTextViewDescription.setTag(this);
        }

        @Override
        public void bindData(MyAdapter adapter, int position) {
            String text = adapter.mData.get(position - adapter.mImageViewCount - adapter.mEditTextCount);
            mTextViewTitle.setText(text);
            mTextViewDescription.setText("Description: " + text);
        }
    }

    private static class EditTextViewHolder extends BaseViewHolder {


        public EditTextViewHolder(View itemView) {
            super(itemView);


        }

        @Override
        public void bindData(MyAdapter adapter, int position) {

        }
    }

    private static class ImageViewHolder extends BaseViewHolder {

        public ImageViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(MyAdapter adapter, int position) {

        }
    }

    private static class FooterViewHolder extends BaseViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(MyAdapter adapter, int position) {

        }
    }


    private class MyAdapter extends RecyclerView.Adapter<BaseViewHolder> {

        private static final int VIEW_TYPE_IMAGE = 0;
        private static final int VIEW_TYPE_EDIT_TEXT = 1;
        private static final int VIEW_TYPE_TEXT = 2;
        private static final int VIEW_TYPE_FOOTER = 3;

        private int mImageViewCount;
        private int mEditTextCount;
        private List<String> mData;
        private View.OnClickListener mListener;

        public MyAdapter(List<String> data, View.OnClickListener listener) {
            mData = data;
            mListener = listener;

            mImageViewCount = 5;
            mEditTextCount = 5;
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mImageViewCount) {
                return VIEW_TYPE_IMAGE;
            }

            if (position < mImageViewCount + mEditTextCount) {
                return VIEW_TYPE_EDIT_TEXT;
            }

            if (position < mImageViewCount + mEditTextCount + mData.size()) {
                return VIEW_TYPE_TEXT;
            }

            return VIEW_TYPE_FOOTER;
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case VIEW_TYPE_TEXT: {
                    View view = getLayoutInflater().inflate(R.layout.recycler_item_view, parent, false);
                    return new TextViewHolder(view, mListener);
                }

                case VIEW_TYPE_IMAGE: {
                    View view = getLayoutInflater().inflate(R.layout.recycler_item_view_image, parent, false);
                    return new ImageViewHolder(view);
                }

                case VIEW_TYPE_EDIT_TEXT: {
                    View view = getLayoutInflater().inflate(R.layout.recycler_item_view_edit, parent, false);
                    return new EditTextViewHolder(view);
                }

                case VIEW_TYPE_FOOTER: {
                    View view = getLayoutInflater().inflate(R.layout.recycler_item_view_footer, parent, false);
                    return new FooterViewHolder(view);
                }

            }

            return null;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            holder.bindData(this, position);
        }

        @Override
        public int getItemCount() {
            return mImageViewCount + mEditTextCount + mData.size() + 1;
        }

        public String getItem(int position) {
            return mData.get(position - mImageViewCount - mEditTextCount);
        }

        public void add(List<String> data) {
            add(data, 0);
        }

        public void addBottom(List<String> data) {
            add(data, mData.size());
        }

        private void add(List<String> data, int index) {
            for (int i = 0; i < data.size(); i++) {
                mData.add(i + index, data.get(i));
            }

            notifyItemRangeInserted(index, data.size());
        }

        public int getSpanSize(int position) {
            switch (getItemViewType(position)) {
                case VIEW_TYPE_IMAGE:
                    return 1;
            }

            return 3;
        }
    }

}
