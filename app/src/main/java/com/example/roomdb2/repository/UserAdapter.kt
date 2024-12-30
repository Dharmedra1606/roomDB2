package com.example.roomdb2.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb2.databinding.UserItemBinding
import com.example.roomdb2.model.User

class UserAdapter(private val list: List<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
         return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.txtUserItemId.text = list[position].id.toString()
        holder.binding.txtUserItemName.text = list[position].name
        holder.binding.txtUserItemAge.text = list[position].age.toString()

    }

}