package com.example.mop125;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private Button join;
    private Button login;
    private EditText email_login;
    private EditText pwd_login;
    private DatabaseReference mDataRef;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth;
    String idToken;
    Long myChecklistdone;
    Long rmChecklistdone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth

        mAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference();
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        join = (Button) findViewById(R.id.Signup);
        login = (Button) findViewById(R.id.login);
        email_login = (EditText) findViewById(R.id.loginEmail);
        pwd_login = (EditText) findViewById(R.id.loginPassword);
        firebaseAuth = firebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        //idToken = firebaseUser.getUid();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_login.getText().toString().trim();
                String pwd = pwd_login.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(com.example.mop125.login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(com.example.mop125.login.this, "Hoş geldiniz!", Toast.LENGTH_SHORT).show();


                                    mDataRef.child("userAccount").child(firebaseUser.getUid()).child("myChecklistdone").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Long value = dataSnapshot.getValue(Long.class);
                                            myChecklistdone = value;
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            //Log.e("MainActivity", String.valueOf(databaseError.toException()));
                                        }
                                    });

                                    mDataRef.child("userAccount").child(firebaseUser.getUid()).child("rmChecklistdone").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Long value = dataSnapshot.getValue(Long.class);
                                            rmChecklistdone = value;
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            //Log.e("MainActivity", String.valueOf(databaseError.toException()));
                                        }
                                    });

                                    Log.d("done", String.valueOf(myChecklistdone));
                                    Log.d("done", String.valueOf(rmChecklistdone));

                                    if (String.valueOf(myChecklistdone).equals("1") && String.valueOf(rmChecklistdone).equals("1")) {
                                        Intent intent = new Intent(com.example.mop125.login.this, Home.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Intent intent = new Intent(com.example.mop125.login.this, afterlogin.class);
                                        startActivity(intent);
                                    }
                                } else {
                                    Toast.makeText(com.example.mop125.login.this, "Giriş başarısız", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.mop125.login.this, signup.class);
                startActivity(intent);
            }
        });
    }
}