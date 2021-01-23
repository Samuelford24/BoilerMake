package com.sf.boilermake;

import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserInformation extends AppCompatActivity {

    private TextView mTextView;
    private EditText name,address;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        name = findViewById(R.id.nameInput);
        address = findViewById(R.id.addressInput);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || address.getText().toString().equals("")) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(UserInformation.this);
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("     Please enter your name and address!");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();

                }
            }
        });

    }
}