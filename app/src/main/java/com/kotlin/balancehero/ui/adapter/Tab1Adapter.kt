package com.kotlin.balancehero.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.balancehero.R
import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.databinding.ItemTab1Binding
import com.kotlin.balancehero.ui.SharedViewModel


class Tab1Adapter(
    var viewModel: SharedViewModel,
    private val list: MutableList<Beers> = arrayListOf()
) : RecyclerView.Adapter<Tab1Adapter.Tab1ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tab1ViewHolder {
        val binding: ItemTab1Binding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_tab1, parent, false)
        return Tab1ViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: Tab1ViewHolder, position: Int) {
        holder.setRowDetails(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(beers: List<Beers>) {
        this.list.addAll(beers)
        notifyDataSetChanged()
    }

    fun updateItem(beer: Beers, checked: Boolean) {
        if (beer.checkbox != checked) {
            val index = list.indexOf(beer)
            list[index] = Beers(beer, checked)
            notifyItemChanged(index)
        }
    }

    class Tab1ViewHolder(
        private var binding: ItemTab1Binding,
        var viewModel: SharedViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setRowDetails(beers: Beers) {
            binding.viewModel = viewModel
            binding.beer = beers
            binding.executePendingBindings()

            Glide.with(binding.imageTab1.context)
                .load(beers.image_url)
                .into(binding.imageTab1)
        }
    }
}
