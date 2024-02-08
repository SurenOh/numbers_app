package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemHistoryBinding
import com.example.myapplication.model.NumberModel

typealias OnClickHistory = (NumberModel) -> Unit

class HistoryAdapter(private var items: ArrayList<NumberModel>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private lateinit var binding: ItemHistoryBinding

    var onClickHistory: OnClickHistory? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: NumberModel) = with(binding) {
            tvNumber.text = model.number
            tvFact.text = model.fact
            itemView.setOnClickListener { onClickHistory?.invoke(model) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: ArrayList<NumberModel>) {
        items = newItems
        notifyDataSetChanged()
    }

}