package com.digitalmeverick.stupa;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from user where phone=(:phoneL) and password=(:passwordL)")//login
    UserEntity Login(String phoneL,String passwordL);

    @Query("SELECT * FROM user ")//fetch all
    List<UserEntity> getAlluser();
}
