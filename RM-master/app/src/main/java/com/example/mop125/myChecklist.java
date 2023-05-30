package com.example.mop125;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myChecklist extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    static RadioGroup rg1_sleeptime;
    static RadioGroup rg2_wakeuptime;
    static RadioGroup rg3_showertime;
    static RadioGroup rg4_showerwhen;
    static RadioGroup rg5_cleaning;
    static RadioGroup rg6_sleephabit;
    static RadioGroup rg7_alarm;
    static RadioGroup rg8_hot;
    static RadioGroup rg9_cold;
    static RadioGroup rg10_sound;
    static RadioGroup rg11_smoke;
    static RadioGroup rg12_perfume;
    static RadioGroup rg13_smell;
    static RadioGroup rg14_drink;
    static RadioGroup rg15_drinkfrequency;
    static RadioGroup rg16_sleeplight;
    static RadioGroup rg17_sleephear;
    static RadioGroup rg18_callinside;
    static RadioGroup rg19_game;
    static RadioGroup rg20_inviting;
    static RadioGroup rg21_dormitorymeal;
    static RadioGroup rg22_exercise;
    static RadioGroup rg23_excercise_type;
    static RadioGroup rg24_study;
    static RadioGroup rg25_study_for_otheruni;
    static RadioGroup rg26_relationship;
    static RadioGroup rg27_eat_at_night;
    static RadioGroup rg28_eat_inside;
    static RadioGroup rg29_insect;
    static RadioGroup rg30_home_frequency;

    Button btn_my, btn_gotorm;
    FirebaseDatabase database;
    DatabaseReference reference;
    Member member;

    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabaseRef;
    int i = 0;

    final String[] items = {"19", "20", "21", "22", "23", "24", "25", "26","27","28"}; //나이
    final String[] items2 = {"YTÜ", "İTÜ", "Boğaziçi", "Marmara", "İstanbul Üni", "Bahçeşehir",
            "Beykent", "Nişantaşı","MEF","Koç"};
    final String[] items3 = {"Bilgisayar Müh.","Makine Müh.","Elektrik Müh.","Elektronik Hab. Müh.","Biyoenformatik","İnşaat Müh.","Kimya","Mat","Fizik","Tıp",
    "Diş","Mimarlık","İşletme","Ekonomi","Diğer"};
    final String[] items4 = {"ESTJ", "ESTP", "ESFJ", "ESFP", "ENTJ", "ENTP", "ENFJ",
    "ENFP", "ISTJ", "ISTP", "ISFJ", "ISFP", "INTJ", "INTP", "INFJ", "INFP"};
    String university;
    String userAge;
    String idToken;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_checklist);
        Spinner spinner = (Spinner)findViewById(R.id.spinner_age);
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner_university);
        Spinner spinner3 = (Spinner)findViewById(R.id.spinner_department);
        Spinner spinner4 = (Spinner)findViewById(R.id.spinner_mbti);
        initPreferences();
        loadState();

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        reference = database.getInstance().getReference().child("age");
        member = new Member();

        btn_my = findViewById(R.id.btn_my);
        btn_gotorm = findViewById(R.id.btn_gotorm);
        rg1_sleeptime = findViewById(R.id.rg1_sleeptime);
        rg2_wakeuptime = findViewById(R.id.rg2_wakeuptime);
        rg3_showertime = findViewById(R.id.rg3_showertime);
        rg4_showerwhen = findViewById(R.id.rg4_showerwhen);
        rg5_cleaning = findViewById(R.id.rg5_cleaning);
        rg6_sleephabit = findViewById(R.id.rg6_sleephabit);
        rg10_sound = findViewById(R.id.rg10_sound);
        rg11_smoke = findViewById(R.id.rg11_smoke);
        rg12_perfume = findViewById(R.id.rg12_perfume);
        rg15_drinkfrequency = findViewById(R.id.rg15_drinkfrequency);
        rg19_game = findViewById(R.id.rg19_game);
        rg20_inviting = findViewById(R.id.rg20_inviting);
        rg21_dormitorymeal = findViewById(R.id.rg21_dormitorymeal);
        rg22_exercise = findViewById(R.id.rg22_exercise);
        rg23_excercise_type = findViewById(R.id.rg23_exercise_type);
        rg24_study = findViewById(R.id.rg24_study);
        rg26_relationship = findViewById(R.id.rg26_relationship);


        //firebase
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

        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*member.setRg1(String.valueOf((int)rg1_sleeptime.getCheckedRadioButtonId()));
                reference.child(String.valueOf(i+1)).setValue(member);

                member.setRg2(String.valueOf((int)rg2_wakeuptime.getCheckedRadioButtonId()));
                reference.child(String.valueOf(i+1)).setValue(member);*/

                idToken = firebaseUser.getUid();
                Log.d("id13",idToken);

                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("myChecklistdone").setValue(1);
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("university").setValue(university);
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userAge").setValue(userAge);
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userSleepTime").setValue(String.valueOf(rg1_sleeptime.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userWakeUpTime").setValue(String.valueOf(rg2_wakeuptime.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userShowertime").setValue(String.valueOf(rg3_showertime.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userShowerwhen").setValue(String.valueOf(rg4_showerwhen.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userCleaning").setValue(String.valueOf(rg5_cleaning.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userSleephabit").setValue(String.valueOf(rg6_sleephabit.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userAlarm").setValue(String.valueOf(rg7_alarm.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userHot").setValue(String.valueOf(rg8_hot.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userCold").setValue(String.valueOf(rg9_cold.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userSound").setValue(String.valueOf(rg10_sound.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userSmoke").setValue(String.valueOf(rg11_smoke.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userPerfume").setValue(String.valueOf(rg12_perfume.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userSmell").setValue(String.valueOf(rg13_smell.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userDrink").setValue(String.valueOf(rg14_drink.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userDrinkfrequency").setValue(String.valueOf(rg15_drinkfrequency.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userSleeplight").setValue(String.valueOf(rg16_sleeplight.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userSleephear").setValue(String.valueOf(rg17_sleephear.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userCallinside").setValue(String.valueOf(rg18_callinside.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userGame").setValue(String.valueOf(rg19_game.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userInviting").setValue(String.valueOf(rg20_inviting.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userDormitorymeal").setValue(String.valueOf(rg21_dormitorymeal.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userExercise").setValue(String.valueOf(rg22_exercise.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userExercisetype").setValue(String.valueOf(rg23_excercise_type.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userStudy").setValue(String.valueOf(rg24_study.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userStudyforotheruni").setValue(String.valueOf(rg25_study_for_otheruni.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userRelationship").setValue(String.valueOf(rg26_relationship.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userEatatnight").setValue(String.valueOf(rg27_eat_at_night.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userEatinside").setValue(String.valueOf(rg28_eat_inside.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userInsect").setValue(String.valueOf(rg29_insect.getCheckedRadioButtonId()));
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("userHomefrequency").setValue(String.valueOf(rg30_home_frequency.getCheckedRadioButtonId()));



                Toast.makeText(com.example.mop125.myChecklist.this, "Kaydedildi!", Toast.LENGTH_SHORT).show();

            }
        });

        btn_gotorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).child("myChecklistdone").setValue(1);
                Intent intent = new Intent(com.example.mop125.myChecklist.this, roommateChecklist.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(sharedPreferences.getInt("spinner_age", 1));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int ageNum = spinner.getSelectedItemPosition();
                userAge = items[ageNum].trim();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                userAge = items[0].trim();
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items2
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(Integer.parseInt(sharedPreferences.getString("spinner_university","0")));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int universitiyNum = spinner2.getSelectedItemPosition();

                university = items2[universitiyNum].trim();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                university = items2[0].trim();
            }
        });

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items3
        );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, items4
        );
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);


    }

    @Override
    protected void onStop()
    {
        super.onStop();
        saveState();
    }

    private void initPreferences(){
        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
    }

    private void saveState(){
//        Spinner spinner = findViewById(R.id.spinner_age);
//        Spinner spinner2 = findViewById(R.id.spinner_university);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("rg1", String.valueOf((int)rg1.getCheckedRadioButtonId()));
//        editor.putString("rg2", String.valueOf((int)rg2.getCheckedRadioButtonId()));
//
//        editor.putInt("spinner_age", spinner.getSelectedItemPosition());
//        editor.putString("spinner_university", String.valueOf(spinner2.getSelectedItemPosition()));
//        editor.commit();
    }

    private void loadState(){
//        rg1_sleeptime = (RadioGroup)findViewById(R.id.rg1_sleeptime);
//        rg2_wakeuptime = (RadioGroup)findViewById(R.id.rg2_wakeuptime);
//        int id = 0, id2 = 0;
//        try {
//            id = Integer.parseInt(sharedPreferences.getString("rg1", "0"));
//            id2 = Integer.parseInt(sharedPreferences.getString("rg2", "0"));
//        } catch (NullPointerException e) { }
//
//        if (id == 0) ((RadioButton) findViewById(R.id.rg1_1)).setChecked(true);
//        else ((RadioButton) findViewById(id)).setChecked(true);
//
//        if (id2 == 0) ((RadioButton) findViewById(R.id.rg2_1)).setChecked(true);
//        else ((RadioButton) findViewById(id2)).setChecked(true);
    }
}
