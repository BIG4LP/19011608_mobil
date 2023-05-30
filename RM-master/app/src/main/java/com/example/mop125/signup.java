package com.example.mop125;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private EditText email_join;
    private EditText pwd_join;
    private EditText age_join,name_join;
    private Button btn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    private FirebaseFirestore mStore;

    public String UidList[] = new String[100];
    public int index=0;
    public String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email_join = (EditText) findViewById(R.id.signupEmail);
        pwd_join = (EditText) findViewById(R.id.signupPassword);
        btn = (Button) findViewById(R.id.signupButton);


        firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mStore = FirebaseFirestore.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = email_join.getText().toString().trim();
                final String pwd = pwd_join.getText().toString().trim();


                firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                   FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                    userAccount account = new userAccount ();
                                    account.setIdToken(firebaseUser.getUid());
                                    account.setEmailId(firebaseUser.getEmail());
                                    account.setPassword(pwd);

                                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).setValue(account);

                                        Map<String,Object> userMap = new HashMap<>();
                                        userMap.put(FirebaseID.documentID,firebaseUser.getUid());
                                        userMap.put(FirebaseID.postEmail,email);
                                        userMap.put(FirebaseID.postPassword,pwd);
                                        mStore.collection(FirebaseID.user).document(firebaseUser.getUid()).set(userMap, SetOptions.merge());

                                    Toast.makeText(signup.this, "Üyeliğiniz Tamamlandı.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(signup.this, login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(signup.this, "Kayıt Hatası!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });


    }
}