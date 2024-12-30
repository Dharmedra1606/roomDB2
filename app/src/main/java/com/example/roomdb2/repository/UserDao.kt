package com.example.roomdb2.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdb2.model.User
import java.util.ArrayList

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Query("DELETE FROM user where age=:age")
    suspend fun deleteUser(age:Int)


    @Query("UPDATE user set name=:name where age=:age")
    suspend fun updateUser(age:Int,name:String)


}