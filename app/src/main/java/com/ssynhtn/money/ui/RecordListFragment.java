package com.ssynhtn.money.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.database.BaseTable;
import com.ssynhtn.money.database.MoneyProvider;
import com.ssynhtn.money.database.RecordTable;
import com.ssynhtn.money.model.MoneyBook;
import com.ssynhtn.money.ui.base.BaseFragment;

/**
 * Created by Garment on 2016/6/28.
 */
public class RecordListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String EXTRA_MONEY_BOOK = "EXTRA_MONEY_BOOK";
    private static final int LOADER_ID_RECORDS = 0;

    private MoneyBook mMoneyBook;

    private ListView mListView;
    private RecordAdapter mRecordAdapter;

    private FloatingActionButton mFloatingActionButton;

    public static RecordListFragment newInstance(MoneyBook moneyBook) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MONEY_BOOK, moneyBook);

        RecordListFragment fragment = new RecordListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMoneyBook = (MoneyBook) getArguments().getSerializable(EXTRA_MONEY_BOOK);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record_list, container, false);

        mListView = (ListView) view.findViewById(R.id.list_view);
        mRecordAdapter = new RecordAdapter(getActivity());
        mListView.setAdapter(mRecordAdapter);

        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRecordActivity.start(getActivity(), mMoneyBook);
            }
        });

        getLoaderManager().initLoader(LOADER_ID_RECORDS, null, this);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = BaseTable.getFullColumns(RecordTable.class);
        String selection = RecordTable.COL_MONEY_BOOK_ID + " = ?";
        String[] selectionArgs = {Integer.toString(mMoneyBook.mId)};
        String sortOrder = RecordTable.COL_CREATE_TIME + " desc";

        return new CursorLoader(getActivity(), MoneyProvider.CONTENT_URI_RECORDS, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mRecordAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mRecordAdapter.swapCursor(null);
    }
}
