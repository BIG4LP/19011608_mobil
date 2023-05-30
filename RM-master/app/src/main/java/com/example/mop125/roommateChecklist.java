package com.example.mop125;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class roommateChecklist extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    private CheckBox cg1_1,cg1_2,cg1_3,cg1_4,cg1_5,cg1_6,cg1_7,cg1_8,cg2_1,cg2_2,cg2_3
    ,cg3_1,cg3_2,cg3_3,cg3_4,cg3_5,cg3_6,cg4_1,cg4_2,cg4_3,cg5_1,cg5_2,cg5_3,cg6_1,
    cg6_2,cg6_3,cg7_1,cg7_2,cg7_3,cg7_4,cg8_1,cg8_2,cg8_3,cg8_4;
    Button btn_rm;
    FirebaseDatabase database;
    DatabaseReference reference;
    private DatabaseReference mDatabaseRef;
    FirebaseAuth firebaseAuth;
    Member member;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommate_checklist);
        initPreferences();
        loadState();

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        reference = database.getInstance().getReference().child("age");
        member = new Member();

        btn_rm = findViewById(R.id.btn_rm);
        cg1_1 = findViewById(R.id.cg1_1);
        cg1_2 = findViewById(R.id.cg1_2);
        cg1_3 = findViewById(R.id.cg1_3);
        cg1_4 = findViewById(R.id.cg1_4);
        cg1_5 = findViewById(R.id.cg1_5);
        cg1_6 = findViewById(R.id.cg1_6);
        cg1_7 = findViewById(R.id.cg1_7);
        cg1_8 = findViewById(R.id.cg1_8);
        cg2_1 = findViewById(R.id.cg2_1);
        cg2_2 = findViewById(R.id.cg2_2);
        cg2_3 = findViewById(R.id.cg2_3);
        cg3_1 = findViewById(R.id.cg3_1);
        cg3_2 = findViewById(R.id.cg3_2);
        cg3_3 = findViewById(R.id.cg3_3);
        cg3_4 = findViewById(R.id.cg3_4);
        cg3_5 = findViewById(R.id.cg3_5);
        cg3_6 = findViewById(R.id.cg3_6);
        cg4_1 = findViewById(R.id.cg4_1);
        cg4_2 = findViewById(R.id.cg4_2);
        cg4_3 = findViewById(R.id.cg4_3);
        cg5_1 = findViewById(R.id.cg5_1);
        cg5_2 = findViewById(R.id.cg5_2);
        cg5_3 = findViewById(R.id.cg5_3);
        cg6_1 = findViewById(R.id.cg6_1);
        cg6_2 = findViewById(R.id.cg6_2);
        cg6_3 = findViewById(R.id.cg6_3);
        cg7_1 = findViewById(R.id.cg7_1);
        cg7_2 = findViewById(R.id.cg7_2);
        cg7_3 = findViewById(R.id.cg7_3);
        cg7_4 = findViewById(R.id.cg7_4);
        cg8_1 = findViewById(R.id.cg8_1);
        cg8_2 = findViewById(R.id.cg8_2);
        cg8_3 = findViewById(R.id.cg8_3);
        cg8_4 = findViewById(R.id.cg8_4);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmChecklistdone").setValue(1);

                if(cg1_1.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmAge").setValue(String.valueOf(cg1_1.getId()));
                }
                if(cg1_2.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmAge").setValue(String.valueOf(cg1_2.getId()));
                }
                if(cg1_3.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmAge").setValue(String.valueOf(cg1_3.getId()));
                }
                if(cg1_4.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmAge").setValue(String.valueOf(cg1_4.getId()));
                }
                if(cg1_5.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmAge").setValue(String.valueOf(cg1_5.getId()));
                }
                if(cg1_6.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmAge").setValue(String.valueOf(cg1_6.getId()));
                }
                if(cg1_7.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmAge").setValue(String.valueOf(cg1_7.getId()));
                }
                if(cg1_8.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmAge").setValue(String.valueOf(cg1_8.getId()));
                }
                if(cg2_1.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmSmoke").setValue(String.valueOf(cg2_1.getId()));
                }
                if(cg2_2.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmSmoke").setValue(String.valueOf(cg2_2.getId()));
                }
                if(cg2_3.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmSmoke").setValue(String.valueOf(cg2_3.getId()));
                }
                if(cg3_1.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmDepartment").setValue(String.valueOf(cg3_1.getId()));
                }
                if(cg3_2.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmDepartment").setValue(String.valueOf(cg3_2.getId()));
                }
                if(cg3_3.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmDepartment").setValue(String.valueOf(cg3_3.getId()));
                }
                if(cg3_4.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmDepartment").setValue(String.valueOf(cg3_4.getId()));
                }
                if(cg3_5.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmDepartment").setValue(String.valueOf(cg3_5.getId()));
                }
                if(cg3_6.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmDepartment").setValue(String.valueOf(cg3_6.getId()));
                }
                if(cg4_1.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmSound").setValue(String.valueOf(cg4_1.getId()));
                }
                if(cg4_2.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmSound").setValue(String.valueOf(cg4_2.getId()));
                }
                if(cg4_3.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmSound").setValue(String.valueOf(cg4_3.getId()));
                }
                if(cg5_1.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmPerfume").setValue(String.valueOf(cg5_1.getId()));
                }
                if(cg5_2.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmPerfume").setValue(String.valueOf(cg5_2.getId()));
                }
                if(cg5_3.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmPerfume").setValue(String.valueOf(cg5_3.getId()));
                }
                if(cg6_1.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmReadyforotheruni").setValue(String.valueOf(cg6_1.getId()));
                }
                if(cg6_2.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmReadyforotheruni").setValue(String.valueOf(cg6_2.getId()));
                }
                if(cg6_3.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmReadyforotheruni").setValue(String.valueOf(cg6_3.getId()));
                }
                if(cg7_1.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmExercise").setValue(String.valueOf(cg7_1.getId()));
                }
                if(cg7_2.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmExercise").setValue(String.valueOf(cg7_2.getId()));
                }
                if(cg7_3.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmExercise").setValue(String.valueOf(cg7_3.getId()));
                }
                if(cg7_4.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmExercise").setValue(String.valueOf(cg7_4.getId()));
                }
                if(cg8_1.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmGame").setValue(String.valueOf(cg8_1.getId()));
                }
                if(cg8_2.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmGame").setValue(String.valueOf(cg8_2.getId()));
                }
                if(cg8_3.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmGame").setValue(String.valueOf(cg8_3.getId()));
                }
                if(cg8_4.isChecked()){
                    mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("rmGame").setValue(String.valueOf(cg8_4.getId()));
                }

                Intent intent = new Intent(com.example.mop125.roommateChecklist.this, GetMatches.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        saveState();
    }

    private void initPreferences(){
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
    }

    private void saveState(){
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("cg1_1", cg1_1.isChecked());
//        editor.putBoolean("cg1_2", cg1_2.isChecked());
//        editor.putBoolean("cg1_3", cg1_3.isChecked());
//        editor.putBoolean("cg1_4", cg1_4.isChecked());
//        editor.putBoolean("cg1_5", cg1_5.isChecked());
//        editor.putBoolean("cg1_6", cg1_6.isChecked());
//        editor.putBoolean("cg1_7", cg1_7.isChecked());
//        editor.putBoolean("cg1_8", cg1_8.isChecked());
//        editor.putBoolean("cg2_1", cg2_1.isChecked());
//        editor.putBoolean("cg2_2", cg2_2.isChecked());
//        editor.commit();
    }

    private void loadState(){
//        cg1_1 = (CheckBox)findViewById(R.id.cg1_1);
//        cg1_2 = (CheckBox)findViewById(R.id.cg1_2);
//        cg1_3 = (CheckBox)findViewById(R.id.cg1_3);
//        cg1_4 = (CheckBox)findViewById(R.id.cg1_4);
//        cg1_5 = (CheckBox)findViewById(R.id.cg1_5);
//        cg1_6 = (CheckBox)findViewById(R.id.cg1_6);
//        cg1_7 = (CheckBox)findViewById(R.id.cg1_7);
//        cg1_8 = (CheckBox)findViewById(R.id.cg1_8);
//        cg2_1 = (CheckBox)findViewById(R.id.cg2_1);
//        cg2_2 = (CheckBox)findViewById(R.id.cg2_2);
//
//        cg1_1.setChecked(sharedPreferences.getBoolean("cg1_1", false));
//        cg1_2.setChecked(sharedPreferences.getBoolean("cg1_2", false));
//        cg1_3.setChecked(sharedPreferences.getBoolean("cg1_3", false));
//        cg1_4.setChecked(sharedPreferences.getBoolean("cg1_4", false));
//        cg1_5.setChecked(sharedPreferences.getBoolean("cg1_5", false));
//        cg1_6.setChecked(sharedPreferences.getBoolean("cg1_6", false));
//        cg1_7.setChecked(sharedPreferences.getBoolean("cg1_7", false));
//        cg1_8.setChecked(sharedPreferences.getBoolean("cg1_8", false));
//        cg2_1.setChecked(sharedPreferences.getBoolean("cg2_1", false));
//        cg2_2.setChecked(sharedPreferences.getBoolean("cg2_2", false));
    }

}