package com.example.randomuserapp.ui.favorite.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randomuserapp.databinding.ItemFavoriteBinding
import com.example.randomuserapp.domain.model.RandomUserModel

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFavoriteBinding.bind(view)

    fun render(
        favoriteInfo: RandomUserModel,
        onClickDelete: (randomUserModel: RandomUserModel) -> Unit
    ) {
        loadImage(favoriteInfo.image)
        binding.tvTitle.text = favoriteInfo.name
        binding.tvCountry.text = favoriteInfo.country
        deleteUserFunctionality(favoriteInfo,onClickDelete)
    }

    private fun loadImage(image: String) {
        Glide.with(binding.ivFavorite.context).load(image).into(binding.ivFavorite);
    }

    private fun deleteUserFunctionality(
        randomUserModel: RandomUserModel,
        onClickDelete: (randomUserModel: RandomUserModel) -> Unit
    ) {
        with(binding) {
            btnDeleteUser.setOnClickListener {
                onClickDelete(randomUserModel)
            }
        }
    }
}