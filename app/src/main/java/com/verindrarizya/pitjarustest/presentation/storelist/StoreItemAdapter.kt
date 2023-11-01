package com.verindrarizya.pitjarustest.presentation.storelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.verindrarizya.pitjarustest.databinding.StoreItemBinding
import com.verindrarizya.pitjarustest.presentation.model.Store

class StoreItemAdapter(
    private val onStoreItemClick: (Store) -> Unit
) : ListAdapter<Store, StoreItemAdapter.StoreItemViewHolder>(DIFF_CALLBACK) {

    inner class StoreItemViewHolder(private val binding: StoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store) {
            binding.tvStoreName.text = store.storeName
            binding.tvVisited.isVisible = store.isVisited
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Store>() {
            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        val binding = StoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}