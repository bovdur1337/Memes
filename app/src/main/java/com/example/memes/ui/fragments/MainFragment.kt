package com.example.memes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.memes.databinding.FragmentMainBinding
import com.example.memes.model.RandomMeme
import com.example.memes.ui.activity.MainActivity
import com.example.memes.viewmodel.MemeViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MemeViewModel
    private var randomMeme: RandomMeme? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
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

        //adding on click listener for like button
        onBtnLikeClick()
    }

    private fun observeRandomMealLD(){
        viewModel.randomMemeLD.observe(viewLifecycleOwner, Observer { meme ->
            Glide.with(this@MainFragment)
                .load(meme.url)
                .into(binding.ivRandomMeme)
            randomMeme = meme

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

    private fun onBtnLikeClick(){
        binding.ivLike.setOnClickListener{
            randomMeme?.let {
                viewModel.addMemeToFavs(it)
                Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }
}