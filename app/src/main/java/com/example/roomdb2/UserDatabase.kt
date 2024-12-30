package com.example.roomdb2

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb2.model.User
import com.example.roomdb2.repository.UserDao

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase(){

    abstract fun getDB(): UserDao

    companion object{
        private var INSTANCE: UserDatabase ?= null
        fun getInstance(context: Context): UserDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = androidx.room.Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_table").build()
                    return INSTANCE as UserDatabase
                }
        }
            return INSTANCE!!
        }

    }
}