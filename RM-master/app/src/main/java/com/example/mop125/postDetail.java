package com.example.mop125;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class postDetail extends AppCompatActivity {

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private TextView dTitle, dContents;

    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        dTitle = findViewById(R.id.detailTitle);
        dContents = findViewById(R.id.detailContents);

        Intent getIntent = getIntent();
        id = getIntent().getStringExtra(FirebaseID.documentID);
        Log.d("data",id);

        mStore.collection(FirebaseID.post).document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().exists()){
                            if(task.getResult() != null){
                                Map<String, Object> snap = task.getResult().getData();
                                String title = String.valueOf(snap.get(FirebaseID.title));
                                String contents = String.valueOf(snap.get(FirebaseID.contents));

                                dTitle.setText(title);
                                dContents.setText(contents);
                            }
                        }else {
                                Toast.makeText(postDetail.this,".",Toast.LENGTH_SHORT).show();
                            }}
                    }
                });


    }
}