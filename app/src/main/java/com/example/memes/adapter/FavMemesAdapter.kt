package com.example.memes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.memes.databinding.MemeItemBinding
import com.example.memes.model.RandomMeme

class FavMemesAdapter: RecyclerView.Adapter<FavMemesAdapter.FavMemesAdapterViewHolder>() {

    inner class FavMemesAdapterViewHolder(val binding: MemeItemBinding): ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<RandomMeme>(){
        override fun areItemsTheSame(oldItem: RandomMeme, newItem: RandomMeme): Boolean {
            return oldItem.postLink == newItem.postLink
        }

        override fun areContentsTheSame(oldItem: RandomMeme, newItem: RandomMeme): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavMemesAdapterViewHolder {
        return FavMemesAdapterViewHolder(
            MemeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavMemesAdapterViewHolder, position: Int) {
        val meme = differ.currentList[position]

        Glide.with(holder.itemView)
            .load(meme.url)
            .into(holder.binding.ivFavMeme)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}