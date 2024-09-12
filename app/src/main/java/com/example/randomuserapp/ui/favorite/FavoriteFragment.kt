package com.example.randomuserapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.randomuserapp.R
import com.example.randomuserapp.databinding.FragmentFavoriteBinding
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

    private fun initUI(){
        initFavoriteList()
        initUIstate()
    }

    private fun initUIstate() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoriteViewModel.favoriteState.collect {
                    favoriteAdapter.updateList(it)
                }
            }
        }
    }

    private fun initFavoriteList() {
        favoriteAdapter = FavoriteAdapter(emptyList(), requireContext())
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = favoriteAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}