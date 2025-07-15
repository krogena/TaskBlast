package ru.shcherbakov.weatherapp.presentation.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.shcherbakov.weatherapp.databinding.FragmentFavouritesBinding
import ru.shcherbakov.weatherapp.presentation.main.MainViewModel

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val mBinding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: FactAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouritesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()

    }
    private fun setupAdapter() {
        adapter = FactAdapter()
        mBinding.tvListFavourites.layoutManager = LinearLayoutManager(requireContext())
        mBinding.tvListFavourites.adapter = this@FavouritesFragment.adapter
    }

    private fun setupObservers() {
        viewModel.favoritesLiveData.observe(viewLifecycleOwner) { facts ->
            Log.d("MyLog", "Received ${facts.size} items")
            facts.forEach { Log.d("MyLog", it.toString()) }
            adapter.differ.submitList(facts)
            if (facts.isEmpty()) {
                Log.d("MyLog", "Received ${facts.size} items")
            }

        }
    }
}