package com.example.roomdb2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roomdb2.model.User
import com.example.roomdb2.repository.UserDao
import com.example.roomdb2.view.RoomDBViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db: UserDao
//    private val roomDBViewModel: RoomDBViewModel by viewModels()
    private val roomDBViewModel: RoomDBViewModel by lazy {
        RoomDBViewModel()
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        db= UserDatabase.getInstance(this@MainActivity).getDB()
//
//        GlobalScope.launch(Dispatchers.IO) {
//            db.insertUser(User(0, "Dharmendra", 20))
//        }
//        GlobalScope.launch(Dispatchers.Main) {
//            val users = db.getAllUsers()
//            users.forEach {
//                Log.d("TAG", it.name)
//            }
//        }

        roomDBViewModel.insertUser(this@MainActivity, User(0, "Dharmendra", 20))
        roomDBViewModel.getAllUsers(this@MainActivity)


    }
}