package com.example.mop125;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Chatting extends AppCompatActivity {

    private String chatRoomUid;
    private String myuid;
    private String destUid;
    private RecyclerView recyclerView;
    private Button button;
    private EditText editText;
    private FirebaseDatabase firebaseDatabase;
    private User destUser;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private int index;
    private String array[] = new String[100];

    public class User {
        public String name;
        public String profileImgUrl;
        public String uid;
        public String pushToken;
    }

    public static class ChatModel {
        public Map<String, Boolean> users = new HashMap<>();
        public Map<String, Comment> comments = new HashMap<>();

        public static class Comment {
            public String uid;
            public String message;
            public Object timestamp;
        }
    }

    /*@Override
    public void onBackPressed() {
        Intent intent = new Intent(Chatting.this, Card_MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK

                | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        init();
        sendMsg();

        Button gesipan = (Button) findViewById(R.id.gesipan);
        gesipan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.example.mop125.Chatting.this,PostListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        Card_MainActivity test = new Card_MainActivity();
        MatchRate test2 = new MatchRate();

        index = test.indexNum;

        //test2.calc(array);

        myuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        destUid = myuid;

        recyclerView = (RecyclerView) findViewById(R.id.message_recyclerview);
        button = (Button) findViewById(R.id.message_btn);
        editText = (EditText) findViewById(R.id.message_editText);

        firebaseDatabase = FirebaseDatabase.getInstance();

        if (editText.getText().toString() == null) button.setEnabled(false);
        else button.setEnabled(true);

        checkChatRoom();
    }

    private void sendMsg() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatModel chatModel = new ChatModel();
                chatModel.users.put(myuid, true);
                chatModel.users.put(destUid, true);


                if (chatRoomUid == null) {
                    Toast.makeText(Chatting.this, "Sohbet odası oluştur", Toast.LENGTH_SHORT).show();
                    button.setEnabled(false);
                    firebaseDatabase.getReference().child("chatrooms").push().setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) { checkChatRoom(); }
                    });
                } else {
                    sendMsgToDataBase();
                }
            }
        });
    }

    private void sendMsgToDataBase() {
        if (!editText.getText().toString().equals("")) {
            ChatModel.Comment comment = new ChatModel.Comment();
            comment.uid = myuid;
            comment.message = editText.getText().toString();
            comment.timestamp = ServerValue.TIMESTAMP;
            firebaseDatabase.getReference().child("chatrooms").child(chatRoomUid).child("comments").push().setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    editText.setText("");
                }
            });
        }
    }

    private void checkChatRoom() {
        /* chatModel
        public Map<String,Boolean> users = new HashMap<>();
        public Map<String, ChatModel.Comment> comments = new HashMap<>();
        */
        firebaseDatabase.getReference().child("chatrooms").orderByChild("users/" + myuid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    ChatModel chatModel = dataSnapshot.getValue(ChatModel.class);
                    if (chatModel.users.containsKey(destUid)) {
                        chatRoomUid = dataSnapshot.getKey();
                        button.setEnabled(true);

                        recyclerView.setLayoutManager(new LinearLayoutManager(Chatting.this));
                        recyclerView.setAdapter(new RecyclerViewAdapter());

                        sendMsgToDataBase();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        List<ChatModel.Comment> comments;
        public RecyclerViewAdapter() {
            comments = new ArrayList<>();
            getDestUid();
        }

        private void getDestUid() {
            firebaseDatabase.getReference().child("chatrooms").child(chatRoomUid).child("users").child(myuid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //destUser = snapshot.getValue(User.class);
                    getMessageList();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        private void getMessageList() {
            FirebaseDatabase.getInstance().getReference().child("chatrooms").child(chatRoomUid).child("comments").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    comments.clear();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        comments.add(dataSnapshot.getValue(ChatModel.Comment.class));
                    }
                    notifyDataSetChanged();

                    recyclerView.scrollToPosition(comments.size() - 1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        @NonNull
        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messagebox, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
            ViewHolder viewHolder = ((ViewHolder) holder);

            if (comments.get(position).uid.equals(myuid))
            {
                viewHolder.textViewMsg.setText(comments.get(position).message);
                viewHolder.linearLayoutDest.setVisibility(View.INVISIBLE);
                viewHolder.linearLayoutRoot.setGravity(Gravity.RIGHT);
                viewHolder.linearLayoutTime.setGravity(Gravity.RIGHT);
            } else {
                 Glide.with(holder.itemView.getContext())
                        .load(destUser.profileImgUrl)
                        .apply(new RequestOptions().circleCrop())
                        .into(holder.imageViewProfile);
                viewHolder.textViewName.setText(destUser.name);
                viewHolder.linearLayoutDest.setVisibility(View.VISIBLE);
                viewHolder.textViewMsg.setText(comments.get(position).message);
                viewHolder.linearLayoutRoot.setGravity(Gravity.LEFT);
                viewHolder.linearLayoutTime.setGravity(Gravity.LEFT);
            }
            viewHolder.textViewTimeStamp.setText(getDateTime(position));

        }

        public String getDateTime(int position) {
            long unixTime = (long) comments.get(position).timestamp;
            Date date = new Date(unixTime);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Istanbul"));
            String time = simpleDateFormat.format(date);
            return time;
        }

        @Override
        public int getItemCount() {
            return comments.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewMsg;
            public TextView textViewName;
            public TextView textViewTimeStamp;
            public ImageView imageViewProfile;
            public LinearLayout linearLayoutDest;
            public LinearLayout linearLayoutRoot;
            public LinearLayout linearLayoutTime;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textViewMsg = (TextView) itemView.findViewById(R.id.item_messagebox_textview_msg);
                textViewName = (TextView) itemView.findViewById(R.id.item_messagebox_TextView_name);
                textViewTimeStamp = (TextView) itemView.findViewById(R.id.item_messagebox_textview_timestamp);
                imageViewProfile = (ImageView) itemView.findViewById(R.id.item_messagebox_ImageView_profile);
                linearLayoutDest = (LinearLayout) itemView.findViewById(R.id.item_messagebox_LinearLayout);
                linearLayoutRoot = (LinearLayout) itemView.findViewById(R.id.item_messagebox_root);
                linearLayoutTime = (LinearLayout) itemView.findViewById(R.id.item_messagebox_layout_timestamp);
            }
        }
    }
}
