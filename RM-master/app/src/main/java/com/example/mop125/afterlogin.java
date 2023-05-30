package com.example.mop125;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class afterlogin extends AppCompatActivity {
    private Button GoTest;
    private Button GoTest2;
    private Button GoPost;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        GoTest = (Button) findViewById(R.id.nextButton_myChecklist);
        GoTest2 = (Button) findViewById(R.id.nextButton_roommateChecklist);
        GoPost = (Button) findViewById(R.id.postButton);

        GoTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.mop125.afterlogin.this, myChecklist.class);
                startActivity(intent);
            }
        });

        GoTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.mop125.afterlogin.this, roommateChecklist.class);
                startActivity(intent);

            }
        });

        GoPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.mop125.afterlogin.this,PostListActivity.class);
                startActivity(intent);
            }
        });



    }
}
