package com.ssynhtn.money.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.ssynhtn.money.R;

public class ToastUtils {

    private static ToastUtils sInstance;

    private Toast mCurrentToast;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public void makeToast(final Context context, final String text) {
        if(TextUtils.isEmpty(text)) return;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(mCurrentToast != null) mCurrentToast.cancel();
                mCurrentToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                mCurrentToast.show();
            }
        });

    }

    public void clearToast() {
        if(mCurrentToast != null) {
            mCurrentToast.cancel();
        }
    }

    public void makeToast(Context context, Throwable error) {
//        if (error instanceof VolleyError) {
//            makeVolleyErrorToast(context, (VolleyError) error);
//            return;
//        }

        String message = error != null ? error.getMessage() : null;
        if (message != null && message.trim().length() > 0) {
            makeToast(context, context.getString(R.string.toast_error_msg, message.trim()));
        } else {
            makeToast(context, context.getString(R.string.toast_universal_error_msg));
        }
    }

//    private void makeVolleyErrorToast(Context context, VolleyError volleyError) {
//        if (volleyError == null) {
//            makeToast(context, context.getString(R.string.toast_universal_error_msg));
//            return;
//        }
//
//        int statusCode = -1;
//        if (volleyError.networkResponse != null) {
//            statusCode = volleyError.networkResponse.statusCode;
//        }
//
//        String errorMessage = volleyError.getMessage();
//        String shortMessage = null;
//
//        if (volleyError instanceof AuthFailureError) {
//            shortMessage = context.getString(R.string.toast_auth_failed);
//        } else if (volleyError instanceof NetworkError) {
//            if (volleyError instanceof NoConnectionError) {
//                shortMessage = context.getString(R.string.toast_no_connection);
//            } else {
//                shortMessage = context.getString(R.string.toast_network_error);
//            }
//
//        } else if (volleyError instanceof ParseError) {
//            shortMessage = context.getString(R.string.toast_parse_error);
//        } else if (volleyError instanceof ServerError) {
//            if (statusCode >= 400 && statusCode <= 499) {
//                shortMessage = context.getString(R.string.toast_client_error);
//            } else {
//                shortMessage = context.getString(R.string.toast_server_error);
//            }
//
//        } else if (volleyError instanceof TimeoutError) {
//            shortMessage = context.getString(R.string.toast_time_out);
//        } else if (volleyError.getCause() instanceof SixinServerException) {
//            shortMessage = ((SixinServerException) volleyError.getCause()).getErrorMessage().getErrorMessage();
//        } else {
//            shortMessage = context.getString(R.string.toast_error);
//        }
//
//        makeVolleyToast(context, shortMessage, statusCode, errorMessage);
//    }
//
//    private void makeVolleyToast(Context context, String shortMessage, int statusCode, String detailedMessage) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(shortMessage);
//
//        if (statusCode != -1) {
//            sb.append(", ").append(context.getString(R.string.status_code, statusCode));
//        }
//
//        if (!TextUtils.isEmpty(detailedMessage)) {
//            sb.append(", ").append(detailedMessage);
//        }
//
//        makeToast(context, sb.toString());
//    }


    public static synchronized ToastUtils getInstance() {
        if(sInstance == null) {
            sInstance = new ToastUtils();
        }

        return sInstance;
    }
}
