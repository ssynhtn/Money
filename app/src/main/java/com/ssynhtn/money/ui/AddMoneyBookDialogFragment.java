package com.ssynhtn.money.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.ssynhtn.money.R;
import com.ssynhtn.money.database.DatabaseHelper;
import com.ssynhtn.money.ui.base.BaseDialogFragment;

/**
 * Created by Garment on 2016/6/28.
 */
public class AddMoneyBookDialogFragment extends BaseDialogFragment implements DialogInterface.OnClickListener {

    private EditText mEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_money_book, null);
        mEditText = (EditText) view.findViewById(R.id.edit_text);

        builder.setView(view);
        builder.setTitle(R.string.title_new_money_book);
        builder.setPositiveButton("确定", this);
        builder.setNegativeButton("取消", this);


        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            String name = mEditText.getText().toString();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(name.trim())) {
                makeToast("账本名字不能为空");
                return;
            }

            createMoneyBook(name);
        }
    }

    private void createMoneyBook(String name) {
        DatabaseHelper.getInstance().addMoneyBook(name);
    }
}
