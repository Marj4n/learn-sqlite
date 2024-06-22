package com.marj4n.sqllite_learn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.marj4n.sqllite_learn.helper.DbHelper;

public class AddEditActivity extends AppCompatActivity {

    private DbHelper helper;
    private EditText txt_name, txt_address;
    private String id, name, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        txt_name = findViewById(R.id.text_name);
        txt_address = findViewById(R.id.text_address);

        //terima data jika ada
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            name = extras.getString("name");
            address = extras.getString("address");

            txt_name.setText(name);
            txt_address.setText(address);
        }

        Button btn_submit = findViewById(R.id.btn_submit);
        Button btn_cancel = findViewById(R.id.btn_cancel);

        helper = new DbHelper(this);

        btn_submit.setOnClickListener(view -> {
            if (id != null) {
                edit();
                finish();
            } else {
                save();
                finish();
            }
        });

        btn_cancel.setOnClickListener(view -> finish());
    }

    private void save() {
        name = txt_name.getText().toString();
        address = txt_address.getText().toString();
        if (name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Name atau address tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            long rowId = helper.insert(name, address);
            if (rowId == -1) {
                Toast.makeText(this, "Insert gagal", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Insert berhasil", Toast.LENGTH_SHORT).show();
                blank();
            }
        }
    }

    private void blank() {
        txt_name.setText(null);
        txt_address.setText(null);
    }

    private void edit() {
        if (name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Name atau address tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            String newName = txt_name.getText().toString();
            String newAddress = txt_address.getText().toString();
            long rowAffected = helper.update(Integer.parseInt(id), newName, newAddress);
            if (rowAffected <= 0) {
                Toast.makeText(this, "Update gagal", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update berhasil", Toast.LENGTH_SHORT).show();
            }
        }
    }
}