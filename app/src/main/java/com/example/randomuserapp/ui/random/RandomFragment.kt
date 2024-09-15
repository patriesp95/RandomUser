package com.example.randomuserapp.ui.random

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.*
import androidx.lifecycle.Lifecycle.State.*
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.randomuserapp.databinding.FragmentRandomBinding
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.ui.common.launchAndCollect
import com.example.randomuserapp.ui.favorite.FavoriteViewModel
import com.example.randomuserapp.ui.favorite.adapter.FavoriteAdapter
import com.example.randomuserapp.ui.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomFragment : Fragment() {
    private val randomUserViewModel by viewModels<RandomUserViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoritesAdapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        handleState()
    }

    private fun initList() {
        favoritesAdapter = FavoriteAdapter(emptyList(), onClickDelete =  { randomUser ->
            favoriteViewModel.deleteFavoriteUser(randomUser)
        })
    }

    private fun handleState() {
        viewLifecycleOwner.launchAndCollect(randomUserViewModel.state)  { randomState ->
            with(randomState){
                handleProgressBarBehaviour(loading)
                favoriteUser?.let { user ->
                    loadImage(user.image)
                    binding.tvName.text = user.name
                    binding.tvCountry.text = user.country
                }
            }
            initList()
            initListeners()
        }
    }

    private fun initListeners() {
        nextUserFunctionality()
        addUserFunctionality()
    }

    private fun addUserFunctionality() {
        binding.btnAddUser.setOnClickListener {
            viewLifecycleOwner.launchAndCollect(randomUserViewModel.state){ randomState ->
                    randomUserViewModel.insertFavoriteUser(randomState.favoriteUser)
            }
        }
    }

    private fun nextUserFunctionality() {
        binding.btnNextUser.setOnClickListener {
            randomUserViewModel.getRandomUser()
        }
    }

    private fun handleProgressBarBehaviour(isLoading: Boolean) {
        when (isLoading) {
            true -> {
                binding.progressBar.isVisible = true
                binding.userData.visibility = View.GONE
            }

            false -> {
                binding.progressBar.isVisible = false
                binding.userData.visibility = View.VISIBLE
            }
        }
    }

    private fun loadImage(image: String) {
        Glide.with(this).load(image).into(binding.ivUser);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}