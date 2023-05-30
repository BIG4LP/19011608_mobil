package com.example.mop125;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostListActivity extends AppCompatActivity implements RecyclerViewClick.OnItemClickListener {

    private FirebaseUser user;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;
    private PostAdapter mAdapter;
    private List<Post> mDataList;

    private FloatingActionButton editButton;

    @Override
    protected void onStart() {
        super.onStart();
        mDataList = new ArrayList<>();
        mStore.collection(FirebaseID.post)
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value != null) {
                            mDataList.clear();
                            for (DocumentSnapshot snap : value.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String ID = String.valueOf(shot.get(FirebaseID.documentID));
                                String title = String.valueOf(snap.get(FirebaseID.title));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                Post data = new Post(ID, title, contents);
                                mDataList.add(data);
                            }

                            mAdapter = new PostAdapter(mDataList);
                            mPostRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        mPostRecyclerView = findViewById(R.id.PostListRecyclerView);
        editButton=findViewById(R.id.PostEdit);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(com.example.mop125.PostListActivity.this,writePostActivity.class));
            }
        });
        mPostRecyclerView.addOnItemTouchListener(new RecyclerViewClick(this,mPostRecyclerView, this));
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(com.example.mop125.PostListActivity.this,postDetail.class);
        intent.putExtra(FirebaseID.documentID,mDataList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(PostListActivity.this);
        dialog.setMessage("Sil?");
        dialog.setPositiveButton("Sil", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                mStore.collection(FirebaseID.post).document(mDataList.get(position).getId()).delete();
                Toast.makeText(PostListActivity.this,"Silindi!",Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(PostListActivity.this,"Silme İşlemini Geri Al",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setTitle("Bildirimleri sil");
        dialog.show();
    }
}