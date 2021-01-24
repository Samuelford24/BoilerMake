package com.sf.boilermake.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sf.boilermake.R;
import com.sf.boilermake.ui.Rating;

public class RatingActivity extends AppCompatActivity {
    private CheckBox checkBox,checkBox2,checkBox3,checkBox4,checkBox5,checkbox6;
    private Button submit;
    private Rating rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        checkBox = findViewById(R.id.b1);
        checkBox2 = findViewById(R.id.b2);
        checkBox3 = findViewById(R.id.b3);
        checkBox4 = findViewById(R.id.b4);
        checkBox5 = findViewById(R.id.b5);
        checkbox6 = findViewById(R.id.b6);
        submit = findViewById(R.id.b7);
        checkBox5.setVisibility(View.INVISIBLE);
        checkbox6.setVisibility(View.INVISIBLE);
         rating = (Rating) getIntent().getSerializableExtra("rating");
         System.out.println("41" + rating.getId());
            FirebaseFirestore.getInstance().
                    collection("places").document(rating.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        Rating rating1 = task.getResult().toObject(Rating.class);
                        checkBox.setChecked(rating.isMaskRequired());
                        checkBox2.setChecked(rating.isLocationSafe());
                        checkBox3.setChecked(rating.isHandSanitizer());
                        checkBox4.setChecked(rating.isRestaurantLocation());
                        if (rating.isRestaurantLocation()) {
                            checkBox5.setVisibility(View.VISIBLE);
                            checkbox6.setVisibility(View.VISIBLE);
                        }
                        submit.setText("Update");
                    } else {
                        System.out.println("No prior review");


                    }
                }
            });
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        FirebaseFirestore.getInstance().collection("places").document(rating.getId()).set(getRating()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                AlertDialog.Builder builder;
                                builder = new AlertDialog.Builder(RatingActivity.this);//get context
//builder.setIcon(R.drawable.open_browser);
                                builder.setTitle("Your Covid Safe review has successfully been submitted");
                                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(RatingActivity.this, HomeFragment.class);
                                        startActivity(intent);
                                        dialog.dismiss();

                                    }
                                });

                                builder.setCancelable(true);
                                builder.show();
                            }
                        });

                }
            });


    }

    public Rating getRating() {

        if (checkBox.isChecked()) {

            rating.setMaskRequired(true);
        } else {
            rating.setMaskRequired(false);
        }
        if (checkBox.isChecked()) {

            rating.setLocationSafe(true);
        } else {
            rating.setLocationSafe(false);
        }
        if (checkBox.isChecked()) {

            rating.setHandSanitizer(true);
        } else {
            rating.setHandSanitizer(false);
        }
        if (checkBox.isChecked()) {

            rating.setRestaurantLocation(true);
        } else {
            rating.setRestaurantLocation(false);
        }
        if (checkBox.isChecked()) {

            rating.setIndoorSeating(true);
        } else {
            rating.setIndoorSeating(false);
        }
        if (checkBox.isChecked()) {

            rating.setOutdoorSeating(true);
        } else {
            rating.setOutdoorSeating(false);
        }
        return rating;
    }
}