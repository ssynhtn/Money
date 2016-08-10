package com.ssynhtn.money.ui.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.ssynhtn.money.R;

import java.lang.reflect.Field;
import java.net.URL;

public class TestUserAgentActivity extends AppCompatActivity {

    private static final String TAG = TestUserAgentActivity.class.getSimpleName();

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_user_agent);

        mTextView = (TextView) findViewById(R.id.text_view_user_agent);

        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("http.agent")).append("\n");

        boolean streamHandlerFactorySet = checkIfStreamHandlerFactorySet();
        if (streamHandlerFactorySet) {
            sb.append("set");
        } else {
            sb.append("not set");
        }

        String urlStreamHandler = getUrlStreamHandler();
        sb.append("\n").append(urlStreamHandler);

        mTextView.setText(sb.toString());
    }

    private String getUrlStreamHandler() {
        try {
            URL url = new URL("http://www.baidu.com/");
            Field streamHandlerField = url.getClass().getDeclaredField("streamHandler");
            streamHandlerField.setAccessible(true);
            Object streamHandler = streamHandlerField.get(url);

            if (streamHandler != null) {
                return streamHandler.getClass().getCanonicalName();
            }

        } catch (Exception e) {
            Log.w(TAG, "bad", e);
        }

        return "no stream handler";
    }

    private boolean checkIfStreamHandlerFactorySet() {
        try {
            Field field = URL.class.getDeclaredField("streamHandlerFactory");
            field.setAccessible(true);
            Object factory = field.get(null);

            return factory != null;

        } catch (Exception e) {
            Log.w(TAG, "bad", e);
        }

        return false;
    }
}
