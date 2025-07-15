package ru.shcherbakov.weatherapp.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.shcherbakov.weatherapp.R
import ru.shcherbakov.weatherapp.databinding.FragmentMainBinding
import ru.shcherbakov.weatherapp.domain.Fact
import ru.shcherbakov.weatherapp.utils.Resource


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private var isAddButtonChecked = false
    private val viewModel: MainViewModel by activityViewModels()

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
                is Resource.Error -> mBinding.tvRandomFact.text = "Error: ${fact.message}"
                is Resource.Loading -> mBinding.tvRandomFact.text = "Loading..."
                is Resource.Success -> mBinding.tvRandomFact.text = fact.data?.activity
            }
        }
        setupClickListeners()

    }

    private fun setupClickListeners(){
        mBinding.getTaskButton.setOnClickListener {
            if (isAddButtonChecked){
                mBinding.addTaskButton.apply {
                    isChecked = false
                    isAddButtonChecked = false // Снимаем состояние "нажато"
                    updateAddTaskButton()
                }
            }
            viewModel.getFact() // Запрашиваем новый факт
        }
        mBinding.addTaskButton.setOnClickListener {
            isAddButtonChecked = !isAddButtonChecked
            updateAddTaskButton()
            if (isAddButtonChecked) {
                val currentFact = viewModel.factLiveData.value?.data
                if (currentFact != null) {
                    viewModel.addToFavorites(currentFact.copy()) // Копируем объект
                    Toast.makeText(context, "Добавлено в избранное", Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.favoritesLiveData.observe(viewLifecycleOwner) { list ->
                Log.d("MyLog", "Items count: ${list.size}") // Проверьте в LogCat
            }
        }
    }
    private fun updateAddTaskButton() {
        val iconRes = if (isAddButtonChecked) R.drawable.ic_check else R.drawable.ic_add
        mBinding.addTaskButton.setIconResource(iconRes)
    }
}