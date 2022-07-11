package com.geektech.newsapp.ui.board


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geektech.newsapp.R
import com.geektech.newsapp.databinding.PagerBoardBinding

class BoardAdapter(private val onCLickStart: () -> Unit) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

 private var list = arrayListOf("Fresh Food", "Fast Delivery", "East Payment")
    private val image = arrayListOf(R.raw.delivery, R.raw.delivery, R.raw.delivery)
    private val text = arrayListOf("Hello", "Привет", "Салам")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PagerBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }
    override fun getItemCount() = 3


    inner class ViewHolder(private var binding: PagerBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.textDescription.text = list[position]
            binding.animationView.setAnimation(image[position])
            if (position ==  text.lastIndex) {
                binding.btnStart.visibility = View.VISIBLE
            } else {
                binding.btnStart.visibility = View.INVISIBLE
            }
            binding.btnStart.setOnClickListener {
                onCLickStart()

            }
        }
    }
}