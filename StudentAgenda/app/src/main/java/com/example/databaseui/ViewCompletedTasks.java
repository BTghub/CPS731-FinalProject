package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ViewCompletedTasks extends AppCompatActivity {
    private CollectionReference mColRef = FirebaseFirestore.getInstance().collection("CompletedTasks");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_completed_tasks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView res = findViewById(R.id.txtResult);
        mColRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String restxt = "";
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                    restxt = restxt + "◘" + doc.getString("course") + ": " + doc.getString("description") + "\n";
                }
                res.setText(restxt);
            }
        });
    }
}