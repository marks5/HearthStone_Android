package me.gabriel.hearthstone.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.gabriel.hearthstone.databinding.ItemCardBinding
import me.gabriel.hearthstone.domain.HearthStoneDomainModel

class HearthStoneRecyclerViewAdapter(private val listener: (HearthStoneDomainModel?) -> Unit) :
    RecyclerView.Adapter<HearthStoneViewHolder>() {

    private var items = emptyList<HearthStoneDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HearthStoneViewHolder {
        return HearthStoneViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HearthStoneViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount() = items.size

    fun setItems(list: List<HearthStoneDomainModel>) {
        items = list
        notifyDataSetChanged()
    }
}
