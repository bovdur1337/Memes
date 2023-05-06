package com.example.memes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memes.R
import com.example.memes.adapter.FavMemesAdapter
import com.example.memes.databinding.FragmentFavoritesBinding
import com.example.memes.ui.activity.MainActivity
import com.example.memes.viewmodel.MemeViewModel

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: MemeViewModel
    private lateinit var favMemesAdapter: FavMemesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        favMemesAdapter = FavMemesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //preparing our rv
        prepareFavMemesRV()

        //observing our livedata with list of fav memes
        observeFavMemes()
    }

    private fun prepareFavMemesRV(){
        binding.rvFavMemes.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = favMemesAdapter
        }
    }

    private fun observeFavMemes(){
        viewModel.favMemesLD.observe(viewLifecycleOwner, Observer { memesList ->
            favMemesAdapter.differ.submitList(memesList)
        })
    }
}