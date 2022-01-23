package com.example.friendsinfo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.friendsinfo.databinding.ImageLayoutBinding
import com.example.friendsinfo.model.FriendInfo

class FriendAdapter : RecyclerView.Adapter<FriendAdapter.ImageViewHolder>() {


    inner class ImageViewHolder(
        val binding: ImageLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<FriendInfo>() {
        override fun areItemsTheSame(oldItem: FriendInfo, newItem: FriendInfo): Boolean {
            return oldItem.name.first == newItem.name.first
        }

        override fun areContentsTheSame(oldItem: FriendInfo, newItem: FriendInfo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<FriendInfo>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currImage = differ.currentList[position]

        holder.binding.apply {
            tvName.text = currImage.name.title+ " "+currImage.name.first+" "+currImage.name.last
            tvCountry.text = currImage.location.country

            val imageLink = currImage.picture.large
            imageView.load(imageLink) {
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount() = differ.currentList.size

}