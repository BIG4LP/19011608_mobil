package com.example.mop125;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class writePostActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mContents;
    private Button saveButton;

    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        mTitle = findViewById(R.id.postTitle);
        mContents = findViewById(R.id.postContent);
        saveButton = findViewById(R.id.postSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser()!=null){
                    String postID = mStore.collection(FirebaseID.post).document().getId();
                    Map<String,Object> data = new HashMap<>();
                    data.put(FirebaseID.documentID,postID);
                    data.put(FirebaseID.title,mTitle.getText().toString());
                    data.put(FirebaseID.contents, mContents.getText().toString());
                    data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
                    mStore.collection(FirebaseID.post).document(postID).set(data, SetOptions.merge());
                    finish();
                }
            }
        });
    }
}