package com.kotlin.balancehero.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.balancehero.R
import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.databinding.ItemTab1Binding
import com.kotlin.balancehero.ui.SharedViewModel

class Tab1Adapter(
    var viewModel: SharedViewModel
) : PagingDataAdapter<Beers, Tab1Adapter.Tab1ViewHolder>(DiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tab1ViewHolder {
        val binding: ItemTab1Binding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_tab1, parent, false)
        return Tab1ViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: Tab1ViewHolder, position: Int) {
        holder.setRowDetails(getItem(position)!!)
    }

    fun updateItem(beer: Beers, checked: Boolean, fromLayout: Boolean) {
        val index = beer.id - 1
        if (itemCount > index) {
            if (snapshot()[index]?.checkbox != checked) {
                snapshot()[index]?.checkbox = checked
                notifyItemChanged(index)
            } else if (!fromLayout) {
                notifyItemChanged(index)
            }
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

    class DiffUtilsCallback : DiffUtil.ItemCallback<Beers>() {
        override fun areItemsTheSame(oldItem: Beers, newItem: Beers): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Beers, newItem: Beers): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
                    && oldItem.checkbox == newItem.checkbox
        }

    }
}
