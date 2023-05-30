package com.example.mop125;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MatchRate extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    Member member;

    private String university;
    private int userAge;
    private int userSleepTime;
    private int userWakeUpTime;
    private int userShowertime;
    private int userShowerwhen;
    private int userCleaning;
    private int userSleephabit;
    private int userAlarm;
    private int userHot;
    private int userCold;
    private int userSound;
    private int userSmoke;
    private int userPerfume;
    private int userSmell;
    private int userDrink;
    private int userDrinkfrequency;
    private int userSleeplight;
    private int userSleephear;
    private int userCallinside;
    private int userGame;
    private int userInviting;
    private int userDormitorymeal;
    private int userExercise;
    private int userExercisetype;
    private int userStudy;
    private int userStudyforotheruni;
    private int userRelationship;
    private int userEatatnight;
    private int userEatinside;
    private int userInsect;
    private int userHomefrequency;

    public int rate;
    public float rateArray[] = new float[100];
    public int indexArray[] = new int[100];
    private int index2;

    FirebaseAuth firebaseAuth;

    public void calc(String[] UidArray) {
        DatabaseReference databaseReference;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String uid = user.getUid();
        signup s = new signup();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("userAccount").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userAccount group = snapshot.getValue(userAccount.class);

                university = group.getUniversity();
                userAge = group.getUserAge();
                userSleepTime = group.getUserSleepTime();
                userShowertime = group.getUserShowertime();
                userShowerwhen = group.getUserShowerwhen();
                userCleaning = group.getUserCleaning();
                userSleephabit = group.getUserSleephabit();
                userAlarm = group.getUserAlarm();
                userHot = group.getUserHot();
                userCold = group.getUserCold();
                userSound = group.getUserSound();
                userSmoke = group.getUserSmoke();
                userPerfume = group.getUserPerfume();
                userSmell = group.getUserSmell();
                userDrink = group.getUserDrink();
                userDrinkfrequency = group.getUserDrinkfrequency();
                userSleeplight = group.getUserSleeplight();
                userSleephear = group.getUserSleephear();
                userCallinside = group.getUserCallinside();
                userGame = group.getUserGame();
                userInviting = group.getUserInviting();
                userDormitorymeal = group.getUserDormitorymeal();
                userExercise = group.getUserExercise();
                userExercisetype = group.getUserExercisetype();
                userStudy = group.getUserStudy();
                userStudyforotheruni = group.getUserStudyforotheruni();
                userRelationship = group.getUserRelationship();
                userEatatnight = group.getUserEatatnight();
                userEatinside = group.getUserEatinside();
                userInsect = group.getUserInsect();
                userHomefrequency = group.getUserHomefrequency();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        for (int i = 0; i < s.UidList.length; i++) {
            //String uidar = databaseReference.child("userAccount").getRef().child();
            //UidArray[i] = uidar;
            //Log.d("duid", "\n uid: " + uidar);
            Log.d("duid", "\ns.UidList[i]1: " + UidArray[i]);

                if (UidArray[i] != uid) {
                    Log.d("duid", "\n i: " + i);
                    Log.d("duid", "\ns.UidList[i]2: " + UidArray[i]);

                    int finalI = i;
                    databaseReference.child("userAccount").child(UidArray[i]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userAccount group = snapshot.getValue(userAccount.class);
                            rate = 0;
                            if (university.equals(group.getUniversity())) {

                                if (userAge == (group.getUserAge())) {
                                    rate++;
                                }

                                if (userSleepTime == (group.getUserSleepTime())) {
                                    rate++;
                                }

                                if (userShowertime == (group.getUserShowertime())) {
                                    rate++;
                                }

                                if (userShowerwhen == (group.getUserShowerwhen())) {
                                    rate++;
                                }

                                if (userCleaning == (group.getUserCleaning())) {
                                    rate++;
                                }

                                if (userSleephabit == (group.getUserSleephabit())) {
                                    rate++;
                                }

                                if (userAlarm == (group.getUserAlarm())) {
                                    rate++;
                                }

                                if (userHot == (group.getUserHot())) {
                                    rate++;
                                }

                                if (userCold == (group.getUserCold())) {
                                    rate++;
                                }

                                if (userSound == (group.getUserSound())) {
                                    rate++;
                                }

                                if (userPerfume == (group.getUserPerfume())) {
                                    rate++;
                                }

                                if (userSmell == (group.getUserSmell())) {
                                    rate++;
                                }

                                if (userDrink == (group.getUserDrink())) {
                                    rate++;
                                }

                                if (userDrinkfrequency == (group.getUserDrinkfrequency())) {
                                    rate++;
                                }

                                if (userAge == (group.getUserAge())) {
                                    rate++;
                                }

                                if (userSleeplight == (group.getUserSleeplight())) {
                                    rate++;
                                }

                                if (userSleephear == (group.getUserSleephear())) {
                                    rate++;
                                }

                                if (userCallinside == (group.getUserCallinside())) {
                                    rate++;
                                }

                                if (userGame == (group.getUserGame())) {
                                    rate++;
                                }

                                if (userInviting == (group.getUserInviting())) {
                                    rate++;
                                }

                                if (userDormitorymeal == (group.getUserDormitorymeal())) {
                                    rate++;
                                }

                                if (userExercise == (group.getUserExercise())) {
                                    rate++;
                                }

                                if (userExercisetype == (group.getUserExercisetype())) {
                                    rate++;
                                }

                                if (userStudy == (group.getUserStudy())) {
                                    rate++;
                                }

                                if (userStudyforotheruni == (group.getUserStudyforotheruni())) {
                                    rate++;
                                }

                                if (userRelationship == (group.getUserRelationship())) {
                                    rate++;
                                }

                                if (userEatatnight == (group.getUserEatatnight())) {
                                    rate++;
                                }

                                if (userEatinside == (group.getUserEatinside())) {
                                    rate++;
                                }

                                if (userInsect == (group.getUserInsect())) {
                                    rate++;
                                }

                                if (userHomefrequency == (group.getUserHomefrequency())) {
                                    rate++;
                                }

                                if (userSmoke == (group.getUserSmoke())) {
                                    rate++;
                                }

                                rateArray[index2] = rate / 30 * 100;
                                indexArray[index2] = finalI;
                                index2++;

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    continue;
                }
            }

            for (int j = 0; j < 100; j++) {
                UidArray[j] = s.UidList[j];
            }


            for (int k = 0; k < indexArray.length; k++) {
                for (int j = 0; j < indexArray.length - j; j++) {
                    if (rateArray[j] < rateArray[j + 1]) {
                        float temp = rateArray[j];
                        rateArray[j] = rateArray[j + 1];
                        rateArray[j + 1] = temp;

                        int temp1 = indexArray[j];
                        indexArray[j] = indexArray[j + 1];
                        indexArray[j + 1] = temp1;

                        String temp2 = UidArray[j];
                        UidArray[j] = UidArray[j + 1];
                        UidArray[j + 1] = temp2;

                    }
                }
            }

            for (int l = 0; l < indexArray.length; l++)
                Log.d("duid", "\narray: " + UidArray[l]);

        }

    }

