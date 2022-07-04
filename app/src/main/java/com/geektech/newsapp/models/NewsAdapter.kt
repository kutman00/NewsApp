package com.geektech.newsapp.models

import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.newsapp.databinding.ItemNewsBinding
import java.util.*

class NewsAdapter(private val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var onLongClick: ((Int) -> Unit)? = null

    private val list = arrayListOf<News>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundColor(Color.GRAY)
        }
        holder.itemView.setOnClickListener {
            onClick(position)
        }
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun addItem(news: News) {
        list.add(0, news)
        notifyItemInserted(0)
    }

    fun getItem(pos: Int): News {
        return list[pos]
    }

    fun replaceItem(news: News, position: Int) {
        list.set(position, news)
        notifyItemChanged(position)
    }

    fun deleteItems(pos: Int) {

        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    inner class ViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            binding.textTitle.text = news.title
            itemView.setOnLongClickListener {
                onLongClick?.invoke(adapterPosition)
                return@setOnLongClickListener true
            }
            binding.textDate.text = getDate(news.createdAt, "dd MMM yyyy")
            binding.textTime.text = getDate(news.createdAt, "HH:mm,")

        }
}

    fun getDate(milliSeconds: Long, dateFormat: String): String {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance();
        calendar.timeInMillis = milliSeconds;
        return formatter.format(calendar.time)

    }
}



