package ru.shcherbakov.weatherapp.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.shcherbakov.weatherapp.R
import ru.shcherbakov.weatherapp.databinding.FragmentMainBinding
import ru.shcherbakov.weatherapp.utils.Resource


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!


    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.factLiveData.observe(viewLifecycleOwner){ fact ->
            when (fact){
                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
                is Resource.Success -> mBinding.tvRandomFact.text = fact.data?.activity
            }

        }
    }
}