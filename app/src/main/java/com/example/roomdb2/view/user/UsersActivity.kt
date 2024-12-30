package com.example.roomdb2.view.user

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb2.R
import com.example.roomdb2.databinding.ActivityUsersBinding
import com.example.roomdb2.model.User
import com.example.roomdb2.repository.UserAdapter
import com.example.roomdb2.view.mainactivity.RoomDBViewModel

class UsersActivity : AppCompatActivity() {
    private val roomDBViewModel: RoomDBViewModel by viewModels()
    private val binding: ActivityUsersBinding by lazy {
        ActivityUsersBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val arr: ArrayList<User> = arrayListOf()
        roomDBViewModel.getAllUsers(this).observe(this) { it ->
            Log.d("Tag", it.toString())
            if (it != null) {
                arr.clear()
                it.forEach {
                    arr.add(it)
                }
            }

            val adapter = UserAdapter(arr)
            binding.recylerView.layoutManager = LinearLayoutManager(this)
            binding.recylerView.adapter = adapter
        }



    }
}