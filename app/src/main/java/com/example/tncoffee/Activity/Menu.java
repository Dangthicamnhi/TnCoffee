package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tncoffee.R;

public class Menu extends AppCompatActivity {
    ListView lvDanhSachMon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menu");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        lvDanhSachMon.setAdapter(QLMon.adap);
        lvDanhSachMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Menu.this, ChiTietOrder.class);
                intent.putExtra("isAdded",true);
                intent.putExtra("item",QLMon.danhSach.get(position));
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        lvDanhSachMon = findViewById(R.id.lvDanhSachMon);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}