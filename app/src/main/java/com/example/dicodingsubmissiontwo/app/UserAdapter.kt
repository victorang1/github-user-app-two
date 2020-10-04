package com.example.dicodingsubmissiontwo.app

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.databinding.UserItemLayoutBinding
import com.example.dicodingsubmissiontwo.model.GithubUser

class UserAdapter(val context: Context, private var userList: List<GithubUser>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val githubUser = userList[position]
        holder.bind(githubUser)
    }

    override fun getItemCount(): Int = userList.count()

    fun setDataSet(userList: List<GithubUser>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val itemBinding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(githubUser: GithubUser) {
            itemBinding.user = githubUser
            itemBinding.clContainer.setOnClickListener { onItemClickCallback?.onItemClicked(githubUser) }
            Glide.with(context)
                .load(githubUser.avatar)
                .apply(RequestOptions.errorOf(R.drawable.ic_person))
                .into(itemBinding.civAvatar)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GithubUser)
    }
}