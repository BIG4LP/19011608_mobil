package com.example.mop125;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Post {

    private String Id;
    private String title;
    private String contents;
    @ServerTimestamp
    private Date date;

    public Post(){

    }

    public Post(String id, String title, String contents) {
        Id = id;
        this.title = title;
        this.contents = contents;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Post{" +
                "Id='" + Id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
