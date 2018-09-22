package com.poly.dell.qunlsch.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.poly.dell.qunlsch.R;

public class ThongKeActivtiy extends AppCompatActivity {
    Toolbar toolbarThongKe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_activtiy);

        toolbarThongKe = findViewById(R.id.toolbarThongKe);
        setSupportActionBar(toolbarThongKe);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarThongKe.setTitleTextColor(Color.WHITE);
        toolbarThongKe.setTitle("Thống Kê");
        toolbarThongKe.setNavigationIcon(R.drawable.undo);

        toolbarThongKe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
