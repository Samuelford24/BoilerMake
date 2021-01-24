package com.sf.boilermake;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UserInformation extends AppCompatActivity {

    private TextView mTextView;
    private EditText name,address;
    private Button submit;
    private double latitude,longtitude;

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

                } else {
                    //@TODO check if location is valid
                    //convert address to latitude and longtitude
                    Geocoder geoCoder = new Geocoder(UserInformation.this, Locale.getDefault());

                    try {
                        List<Address> addresses =
                                geoCoder.getFromLocationName(address.getText().toString(), 1);
                        if (addresses.size() >  0) {
                            latitude = addresses.get(0).getLatitude(); longtitude =
                                    addresses.get(0).getLongitude(); }

                    } catch (IOException e) { // TODO Auto-generated catch block
                        e.printStackTrace();
                    return;}

                    Map<String, Object> map = new HashMap<>();
                    map.put("uid",FirebaseAuth.getInstance().getUid());
                    map.put("name",name.getText().toString());
                    map.put("addressString",address.getText().toString());
                    System.out.println(latitude);
                    System.out.println(longtitude);
                    map.put("latitude",latitude);
                    map.put("longtitude",longtitude);

                    FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getUid()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(UserInformation.this,"Success",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(UserInformation.this,MainActivity.class);
                            startActivity(intent);

                        }
                    });
                }
            }
        });

    }
}
