package com.example.roomdb2.view.mainactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roomdb2.R
import com.example.roomdb2.databinding.ActivityMainBinding
import com.example.roomdb2.databinding.CustomDialogBinding
import com.example.roomdb2.model.User
import com.example.roomdb2.repository.UserDao
import com.example.roomdb2.view.user.UsersActivity

class MainActivity : AppCompatActivity() {
    lateinit var db: UserDao

    //    private val roomDBViewModel: RoomDBViewModel by viewModels()
    private val roomDBViewModel: RoomDBViewModel by lazy {
        RoomDBViewModel()
    }
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnInsertData.setOnClickListener {
            dialogBox("insert")

        }

        binding.btnShowData.setOnClickListener {
            Toast.makeText(this, "Show data", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, UsersActivity::class.java)
            startActivity(intent)

        }
        binding.btnDeleteData.setOnClickListener {
            dialogBox("delete")
        }
        binding.btnUpdateData.setOnClickListener {
            dialogBox("update")
        }

    }

    fun dialogBox(title: String) {
        val builder = AlertDialog.Builder(this)
            .create()
        val view = layoutInflater.inflate(R.layout.custom_dialog, null)
        val binding = CustomDialogBinding.bind(view)
        builder.setView(view)
        binding.txtTitle.text = title

        when (title) {
            "insert" -> {

                binding.btnsave.setOnClickListener {
                    val name = binding.edtName.text.toString()
                    val age = binding.edtAge.text.toString().toInt()
                    if (name.isNotEmpty() || age.toString().isNotEmpty()) {
                        roomDBViewModel.insertUser(this@MainActivity, User(0, name, age))
                        builder.dismiss()
                    }
                    else{
                        Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
            }

            "update" -> {
                binding.btnsave.setOnClickListener {
                    val name = binding.edtName.text.toString()
                    val age = binding.edtAge.text.toString().toInt()
                    if (name.isNotEmpty() || age.toString().isNotEmpty()) {
                        roomDBViewModel.updateUser(this@MainActivity, age, name)
                        builder.dismiss()
                    }
                    else{
                        Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }

            }

            "delete" -> {
                binding.edtName.visibility= View.GONE
                binding.btnsave.setOnClickListener {
                    val age = binding.edtAge.text.toString().toInt()
                    if (age.toString().isNotEmpty()) {
                        val age = binding.edtAge.text.toString().toInt()
                        roomDBViewModel.deleteUser(this@MainActivity, age)
                        builder.dismiss()
                    }
                }

            }
        }

        builder.setCanceledOnTouchOutside(true)
        builder.show ()
    }
}
