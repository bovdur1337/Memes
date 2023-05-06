package com.example.memes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.memes.databinding.FragmentMainBinding
import com.example.memes.viewmodel.MemeViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MemeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //adding observer on livedata
        observeRandomMealLD()

        //adding on click listener for next meme button
        onBtnNextMemeClick()
    }

    private fun observeRandomMealLD(){
        viewModel.randomMemeLD.observe(viewLifecycleOwner, Observer { meme ->
            Glide.with(this@MainFragment)
                .load(meme.url)
                .into(binding.ivRandomMeme)
            binding.pbImage.visibility = View.GONE
            binding.ivRandomMeme.visibility = View.VISIBLE
        })
    }

    private fun onBtnNextMemeClick(){
        binding.btnNextMeme.setOnClickListener{
            binding.pbImage.visibility = View.VISIBLE
            binding.ivRandomMeme.visibility = View.GONE
            viewModel.getRandomMeme()
        }
    }
}