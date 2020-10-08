package com.example.dicodingsubmissiontwo.app.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dicodingsubmissiontwo.R
import com.example.dicodingsubmissiontwo.databinding.FavoriteItemLayoutBinding
import com.example.dicodingsubmissiontwo.db.entity.FavoriteUser
import com.example.dicodingsubmissiontwo.model.GithubUser

class FavoriteAdapter(val context: Context, private var favoriteList: List<FavoriteUser>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickListener(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            FavoriteItemLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteUser = favoriteList[position]
        holder.bind(favoriteUser)
    }

    override fun getItemCount(): Int = favoriteList.count()

    fun setDataSet(favoriteList: List<FavoriteUser>) {
        this.favoriteList = favoriteList
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val itemBinding: FavoriteItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(favoriteUser: FavoriteUser) {
            itemBinding.user = favoriteUser
            itemBinding.clFavorite.setOnClickListener { onItemClickCallback?.onItemClicked(favoriteUser) }
            itemBinding.imageBtnRemove.setOnClickListener { onItemClickCallback?.onRemoveClicked(favoriteUser) }
            Glide.with(context)
                .load(favoriteUser.avatar)
                .apply(RequestOptions.errorOf(R.drawable.ic_person))
                .into(itemBinding.civAvatar)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FavoriteUser)
        fun onRemoveClicked(data: FavoriteUser)
    }
}