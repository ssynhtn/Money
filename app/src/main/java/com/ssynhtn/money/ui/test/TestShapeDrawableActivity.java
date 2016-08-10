package com.ssynhtn.money.ui.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ssynhtn.money.R;
import com.ssynhtn.money.ui.test.view.ShapeDrawableView;

public class TestShapeDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shape_drawable);

        ShapeDrawableView view = (ShapeDrawableView) findViewById(R.id.view_with_shape);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
            }
        });

    }
}
