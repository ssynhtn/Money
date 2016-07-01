package com.ssynhtn.money.custom;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Garment on 2016/4/25.
 */
public class MultipleAdapterWrapperAdapter extends BaseAdapter {
    private List<BaseAdapter> mAdapters;
    private Map<String, BaseAdapter> mAdapterMap;

    private static class AdapterPositionPair {
        private final BaseAdapter mAdapter;
        private final int mPosition;

        public AdapterPositionPair(BaseAdapter adapter, int position) {
            mAdapter = adapter;
            mPosition = position;
        }
    }


    public MultipleAdapterWrapperAdapter(List<BaseAdapter> adapters) {
        this(adapters, null);
    }

    public MultipleAdapterWrapperAdapter(List<BaseAdapter> adapters, Map<String, BaseAdapter> adapterMap) {
        mAdapters = adapters;
        mAdapterMap = adapterMap == null ? new HashMap<String, BaseAdapter>() : adapterMap;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        for (BaseAdapter adapter : mAdapters) {
            adapter.registerDataSetObserver(observer);
        }
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        for (BaseAdapter adapter : mAdapters) {
            adapter.unregisterDataSetObserver(observer);
        }
    }

    @Override
    public boolean hasStableIds() {
        for (BaseAdapter adapter : mAdapters) {
            if (!adapter.hasStableIds()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int getItemViewType(int position) {
        AdapterPositionPair adapterPositionPair = getAdapterPositionPair(position);
        int type = adapterPositionPair.mAdapter.getItemViewType(adapterPositionPair.mPosition);

        int index = mAdapters.indexOf(adapterPositionPair.mAdapter);
        for (int i = 0; i < index; i++) {
            type += mAdapters.get(i).getViewTypeCount();
        }

        return type;
    }

    @Override
    public int getViewTypeCount() {
        int count = 0;
        for (BaseAdapter adapter : mAdapters) {
            count += adapter.getViewTypeCount();
        }

        return count;
    }

    @Override
    public int getCount() {
        int count = 0;
        for (BaseAdapter adapter : mAdapters) {
            count += adapter.getCount();
        }
        return count;
    }

    private AdapterPositionPair getAdapterPositionPair(int position) {
        int index = 0;
        BaseAdapter adapter = mAdapters.get(0);

        while (position >= adapter.getCount()) {
            position -= adapter.getCount();
            index++;
            adapter = mAdapters.get(index);
        }

        return new AdapterPositionPair(adapter, position);
    }

    @Override
    public Object getItem(int position) {
        AdapterPositionPair adapterPositionPair = getAdapterPositionPair(position);
        return adapterPositionPair.mAdapter.getItem(adapterPositionPair.mPosition);
    }

    @Override
    public long getItemId(int position) {
        AdapterPositionPair adapterPositionPair = getAdapterPositionPair(position);
        return adapterPositionPair.mAdapter.getItemId(adapterPositionPair.mPosition);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterPositionPair adapterPositionPair = getAdapterPositionPair(position);
        return adapterPositionPair.mAdapter.getView(adapterPositionPair.mPosition, convertView, parent);
    }

    public void swapCursor(Cursor cursor, int index) {
        CursorAdapter cursorAdapter = (CursorAdapter) mAdapters.get(index);
        cursorAdapter.swapCursor(cursor);
    }

    public void swapCursor(Cursor cursor, String tag) {
        CursorAdapter cursorAdapter = (CursorAdapter) mAdapterMap.get(tag);
        cursorAdapter.swapCursor(cursor);
    }
}
