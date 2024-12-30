package com.example.roomdb2.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdb2.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>
}