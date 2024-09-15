package com.example.randomuserapp.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapp.R
import com.example.randomuserapp.domain.model.RandomUserModel

class FavoriteAdapter(private var favoriteList: List<RandomUserModel>,
                      private val onClickDelete: (randomUserModel: RandomUserModel) -> Unit
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    fun updateList(list: List<RandomUserModel>) {
        favoriteList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        )
    }

    override fun getItemCount(): Int = favoriteList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.render(favoriteList[position], onClickDelete)
    }
}