package com.example.projectgame.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity(tableName = "users")
public class User {

    @ColumnInfo(name = "name")
    public final String name;

    @ColumnInfo(name = "score")
    public final int score;


    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }



}

