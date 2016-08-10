package com.ssynhtn.money.ui.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.base.BaseActivity;

import java.util.Set;

public class TestLinkifyActivity extends BaseActivity {

    private static final String TAG = TestLinkifyActivity.class.getSimpleName();

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_linkify);

        initToolbar();


        test();

        mTextView = (TextView) findViewById(R.id.text_view_link);

        String text = "电话4564654564；手机11022225252；邮箱gongzhenrong@garmentoffice.com";

        mTextView.setText(text);

        Linkify.addLinks(mTextView, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);

        try {
            final Spannable spannable = (Spannable) mTextView.getText();

            URLSpan[] urlSpens = spannable.getSpans(0, spannable.length(), URLSpan.class);
            if (urlSpens != null) {
                for (final URLSpan span : urlSpens) {
                    Uri uri = Uri.parse(span.getURL());
                    if (uri.getScheme().equals("mailto")) {
                        int start = spannable.getSpanStart(span);
                        final int end = spannable.getSpanEnd(span);

                        spannable.removeSpan(span);
                        spannable.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                mailToSafely(span.getURL());
                                Selection.setSelection(spannable, end, end);
                            }
                        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }

        } catch (Exception e) {
            Log.w(TAG, "bad", e);
        }
    }

    private void test() {
        Uri uri = Uri.parse("comhaodingdansixin:///enquiry_contact_info?contact_info=some_info");
        String scheme = uri.getScheme();
        Log.d(TAG, "scheme: " + scheme);
        Log.d(TAG, "path: " + TextUtils.join("/", uri.getPathSegments()));
        Set<String> params = uri.getQueryParameterNames();

        for (String key : params) {
            String value = uri.getQueryParameter(key);
            Log.d(TAG, "key: " + key + ", value: " + value);
        }

    }

    private void mailToSafely(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(uri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            makeToast("no email client found for mail: " + url);
        }
    }
}
