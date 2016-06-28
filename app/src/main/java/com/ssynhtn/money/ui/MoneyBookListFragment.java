package com.ssynhtn.money.ui;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.database.MoneyBookTable;
import com.ssynhtn.money.database.MoneyProvider;
import com.ssynhtn.money.ui.base.BaseFragment;


public class MoneyBookListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private static final int LOADER_ID_MONEY_LIST = 0;

    private static final String TAG = MoneyBookListFragment.class.getSimpleName();
    private ListView mListView;
    private MoneyBookAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money_book_list, container, false);

        findViews(view);

        mAdapter = new MoneyBookAdapter(getActivity(), null);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        getLoaderManager().initLoader(LOADER_ID_MONEY_LIST, null, this);

        return view;
    }

    private void findViews(View view) {
        mListView = (ListView) view.findViewById(R.id.list_view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_money_book_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_money_book) {
            showDialog(new AddMoneyBookDialogFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = MoneyBookTable.getInstance().getFullColumns();
        Log.d(TAG, "projection: " + projection);
        String selection = null;
        String[] selectionArgs = null;
        String order = MoneyBookTable.COL_MONEY_BOOK_ID;

        return new CursorLoader(getActivity(), MoneyProvider.CONTENT_URI_MONEY_BOOKS, projection, selection, selectionArgs, order);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            Log.d(TAG, "cursor count: " + data.getCount());
        }
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MoneyBookAdapter.ViewHolder viewHolder = (MoneyBookAdapter.ViewHolder) view.getTag();

        RecordListActivity.start(getActivity(), viewHolder.mMoneyBook);
    }
}
