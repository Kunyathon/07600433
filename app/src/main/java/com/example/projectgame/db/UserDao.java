package com.example.projectgame.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectgame.model.User;


@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    User[] getAllUsers();

    @Insert
    void addUser(User... users);


    @Delete
    void deleteUser(User user);
}
