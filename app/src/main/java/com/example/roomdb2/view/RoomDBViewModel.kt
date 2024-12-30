package com.example.roomdb2.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb2.UserDatabase
import com.example.roomdb2.model.User
import kotlinx.coroutines.launch

class RoomDBViewModel():ViewModel() {
    val data = MutableLiveData<List<User>>()
    fun insertUser(context: Context,user: User){

        val db = UserDatabase.getInstance(context).getDB()
        viewModelScope.launch {
            db.insertUser(user)

        }
    }

    fun getAllUsers(context: Context){
        val db = UserDatabase.getInstance(context).getDB()
        viewModelScope.launch {
            data.value = db.getAllUsers()
            Log.d("TAG", data.value.toString())

        }

    }
}