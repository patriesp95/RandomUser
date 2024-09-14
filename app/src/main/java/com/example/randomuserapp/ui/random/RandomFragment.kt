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
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.randomuserapp.databinding.FragmentRandomBinding
import com.example.randomuserapp.domain.model.RandomUserModel
import com.example.randomuserapp.ui.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomFragment : Fragment() {
    private val randomUserViewModel by viewModels<RandomUserViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    private var _binding: FragmentRandomBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListeners()
        handleState()
    }

    private fun handleState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                randomUserViewModel.state.collect { randomState ->
                    handleProgressBarBehaviour(randomState.loading)
                    bindData(randomState.favoriteUser)
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnNextUser.setOnClickListener {
            this.randomUserViewModel.getRandomUser()
        }

        binding.btnAddUser.setOnClickListener {
            Log.d("patri", "add user clicked")
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    randomUserViewModel.state.collect { randomState ->
                        Log.d("patri", randomState.toString())
                        favoriteViewModel.insertFavoriteUser(randomState.favoriteUser)
                    }
                }
            }
            favoriteViewModel.getFavoriteList()
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

    private fun bindData(user: RandomUserModel?) {
        user?.let {
            loadImage(it.image)
            binding.tvName.text = it.name
            binding.tvCountry.text = it.country
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
}