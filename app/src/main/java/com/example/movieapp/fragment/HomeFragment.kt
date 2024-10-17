package com.example.movieapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.adpater.MoviesAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.models.MovieResult
import com.example.movieapp.mvvm.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :Fragment() {
    var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    val moviesAdapter by lazy {
        MoviesAdapter()
    }

    val MoviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesAdapter.onItemClickListener=object :MoviesAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToDetailsFragment2(id))
            }
        }
        _binding= FragmentHomeBinding.bind(view)
        observeData()
    }
    private fun observeData() {
        binding.progressBar.visibility = View.VISIBLE
        MoviesViewModel.moviesLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            moviesAdapter.movieList = it as? ArrayList<MovieResult>
            binding.recMovie.adapter = moviesAdapter
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}