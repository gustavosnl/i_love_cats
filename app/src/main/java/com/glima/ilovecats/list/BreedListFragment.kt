package com.glima.ilovecats.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.glima.ilovecats.databinding.FragmentBreedsListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel

class BreedListFragment : Fragment() {

    private val breedListViewModel by viewModel(this, BreedListViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentBreedsListBinding.inflate(inflater)
        val adapter = BreedsAdapter()

        binding.breedsList.adapter = adapter

        lifecycleScope.launch {
            val breeds = breedListViewModel.getBreeds()
            breeds.collectLatest {
                adapter.submitData(it)

            }
        }
        return binding.root

    }

}