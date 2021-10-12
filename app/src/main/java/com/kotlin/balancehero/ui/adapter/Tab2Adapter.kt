package com.kotlin.balancehero.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlin.balancehero.R
import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.databinding.ItemTab2Binding
import com.kotlin.balancehero.ui.SharedViewModel

class Tab2Adapter(
    var viewModel: SharedViewModel,
val list : MutableList<Beers> = arrayListOf()
) : RecyclerView.Adapter<Tab2Adapter.Tab2ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tab2ViewHolder {

        val binding: ItemTab2Binding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_tab2, parent, false)
        return Tab2ViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: Tab2ViewHolder, position: Int) {
        holder.setRowDetails(list.get(position))
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<Beers>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun updateItem(beer: Beers, checked: Boolean) {
        if(beer.checkbox != checked) {
            val index = list.indexOf(beer)
            list.set(index, Beers(beer, checked))
            notifyItemChanged(index)
        }
    }

    class Tab2ViewHolder(
        var binding: ItemTab2Binding,
        var viewModel: SharedViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setRowDetails(beers: Beers) {
            binding.viewModel = viewModel
            binding.beer = beers
            binding.executePendingBindings()

            Glide.with(binding.imageTab2.context)
                .load(beers.image_url)
                .into(binding.imageTab2)
        }

    }

}
