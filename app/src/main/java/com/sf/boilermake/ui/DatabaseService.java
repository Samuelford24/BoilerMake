package com.sf.boilermake.ui;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseService {

    CollectionReference placesCollection = FirebaseFirestore.getInstance().collection("places");


    public void addRatingToFirestore(Rating rating) {
        placesCollection.add(rating);
    }

    public void receiveRatingFromFirestore(String id) {
        DocumentReference docRef = placesCollection.document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Rating rating = documentSnapshot.toObject(Rating.class);
            }
        });
    }

}
