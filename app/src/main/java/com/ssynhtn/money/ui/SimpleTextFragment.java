package com.ssynhtn.money.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssynhtn.money.R;

public class SimpleTextFragment extends Fragment {
    private static final String ARG_TEXT = "ARG_TEXT";

    private String mText;
    private String mParam2;


    public SimpleTextFragment() {
        // Required empty public constructor
    }

    public static SimpleTextFragment newInstance(String text) {
        SimpleTextFragment fragment = new SimpleTextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_text, container, false);

        TextView textView = (TextView) view.findViewById(R.id.text_view_simple);
        textView.setText(mText);

        return view;
    }

}
