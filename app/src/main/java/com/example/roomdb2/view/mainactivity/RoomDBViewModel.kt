package com.example.roomdb2.view.mainactivity

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb2.UserDatabase
import com.example.roomdb2.model.User
import kotlinx.coroutines.launch

class RoomDBViewModel() : ViewModel() {

    fun insertUser(context: Context, user: User) {

        val db = UserDatabase.getInstance(context).getDB()
        viewModelScope.launch {
            db.insertUser(user)

        }
    }

    fun getAllUsers(context: Context): LiveData<List<User>> {
        val data = MutableLiveData<List<User>>()

        viewModelScope.launch {
            val db = UserDatabase.getInstance(context).getDB()
            data.postValue(db.getAllUsers())
        }
        return data
    }

    fun deleteUser(context: Context, id: Int) {
        val db = UserDatabase.getInstance(context).getDB()
        viewModelScope.launch {
            db.deleteUser(id)
        }

    }

    fun updateUser(context: Context, id: Int, name: String) {
        val db = UserDatabase.getInstance(context).getDB()
        viewModelScope.launch {
            db.updateUser(id, name)
        }
    }
}