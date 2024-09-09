package com.example.randomuserapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.randomuserapp.databinding.ActivityMainBinding
import com.example.randomuserapp.domain.model.RandomUserModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val randomUserViewModel by viewModels<RandomUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.mainContainer.setOnClickListener {
            randomUserViewModel.getRandomUser()
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            randomUserViewModel.state.collect { result ->
                if(!result.isNullOrEmpty()){
                    bindData(result)
                }
            }
        }
    }

    private fun bindData(result: List<RandomUserModel>) {
        loadImage(result[0].image)
        binding.tvName.text = result[0].name
        binding.tvCountry.text = result[0].country
    }

    private fun loadImage(image: String) {
        Glide.with(this).load(image).into(binding.ivUser);

    }

}