package com.marj4n.sqllite_learn;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marj4n.sqllite_learn.adapter.Adapter;
import com.marj4n.sqllite_learn.helper.DbHelper;
import com.marj4n.sqllite_learn.model.Data;


import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Adapter adapter;
    private DbHelper helper;
    private final ArrayList<Data> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
            startActivity(intent);
        });

        helper = new DbHelper(this);
        ListView listView = findViewById(R.id.list_view);
        adapter = new Adapter(itemList);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            final String id = itemList.get(i).getId();
            final String name = itemList.get(i).getName();
            final String address = itemList.get(i).getAddress();

            final CharSequence[] dialogItem = {"Edit", "Hapus"};
            new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(true)
                    .setItems(dialogItem, (dialogInterface, i1) -> {
                        switch (i1) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                intent.putExtra("address", address);
                                startActivity(intent);
                                break;
                            case 1:
                                int rowAffected = helper.delete(Integer.parseInt(id));
                                if (rowAffected > 0) {
                                    Toast.makeText(MainActivity.this,
                                            "Delete successed",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this,
                                            "Delete failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }).show();

            return true;
        });
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = helper.getAllData();
        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get("id");
            String name = row.get(i).get("name");
            String address = row.get(i).get("address");

            Data data = new Data(id, name, address);
            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getAllData();
    }
}