package com.example.randomuserapp.ui.favorite.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randomuserapp.databinding.ItemFavoriteBinding
import com.example.randomuserapp.domain.model.RandomUserModel

class FavoriteViewHolder(view: View, myContext: Context) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFavoriteBinding.bind(view)

    fun render(favoriteInfo: RandomUserModel, myContext: Context) {
        loadImage(favoriteInfo.image, myContext)
        binding.tvTitle.text = favoriteInfo.name
        binding.tvCountry.text = favoriteInfo.country
    }

    private fun loadImage(image: String, myContext: Context) {
        Glide.with(myContext).load(image).into(binding.ivFavorite);
    }
}