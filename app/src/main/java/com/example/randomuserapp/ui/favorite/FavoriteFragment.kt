package com.example.randomuserapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomuserapp.databinding.FragmentFavoriteBinding
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.ui.favorite.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private lateinit var favoriteAdapter: FavoriteAdapter

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initFilter()
        initFavoriteList()
        updateList()
        initDeleteUserFunctionality()
    }

    private fun initDeleteUserFunctionality() {
//        binding.btnDeleteUser.setOnClickListener {
//            this.randomUserViewModel.getRandomUser()
//        }
    }

    private fun initFilter() {
        binding.etFilter.addTextChangedListener {
            lifecycleScope.launch {
                favoriteViewModel.favoriteState.collect { favoriteState ->
                    val filteredList = favoriteState.favoritesUserList.filter { user ->
                        user.name.contains(it.toString())
                    }
                    enableFilteredList(filteredList)
                }
            }
        }
    }

    private fun enableFilteredList(randomUserList: List<RandomUserModel>) {
        favoriteAdapter.updateList(randomUserList)
    }


    private fun initFavoriteList() {
        favoriteAdapter = FavoriteAdapter(emptyList(), requireContext())
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
        }
    }

    private fun updateList() {
        lifecycleScope.launch {
            favoriteViewModel.favoriteState.collect { favoriteState ->
                favoriteAdapter.updateList(favoriteState.favoritesUserList)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}