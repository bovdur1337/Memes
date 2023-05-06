package com.example.memes.ui.fragments

import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memes.R
import com.example.memes.adapter.FavMemesAdapter
import com.example.memes.databinding.FragmentFavoritesBinding
import com.example.memes.ui.activity.MainActivity
import com.example.memes.viewmodel.MemeViewModel
import com.google.android.material.snackbar.Snackbar

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

        //adding possibility to swipe and delete memes
        onMemeSwipe()
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

    private fun onMemeSwipe(){
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val currentMeme = favMemesAdapter.differ.currentList[position]
                viewModel.deleteMemeFromFavs(currentMeme)

                Snackbar.make(requireView(), "Meme Deleted", Snackbar.LENGTH_SHORT)
                    .setAction(
                        "Undo",
                        View.OnClickListener {
                            viewModel.addMemeToFavs(currentMeme)
                        }
                    ).show()
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavMemes)
    }
}