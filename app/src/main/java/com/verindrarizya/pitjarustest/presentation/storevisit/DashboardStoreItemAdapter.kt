package com.verindrarizya.pitjarustest.presentation.storevisit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.verindrarizya.pitjarustest.R
import com.verindrarizya.pitjarustest.databinding.DashboardStoreItemBinding
import com.verindrarizya.pitjarustest.presentation.model.DashboardStoreItem

class DashboardStoreItemAdapter() :
    ListAdapter<DashboardStoreItem, DashboardStoreItemAdapter.DashboardStoreItemViewHolder>(
        DIFF_CALLBACK
    ) {

    private val colors = listOf<Int>(
        R.color.orangish,
        R.color.turqoa,
        R.color.warm_blue
    )

    inner class DashboardStoreItemViewHolder(private val binding: DashboardStoreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dashboardStoreItem: DashboardStoreItem, position: Int) {
            val chosenColorIndex = position % colors.size
            binding.cvDashboardItem.setCardBackgroundColor(itemView.context.getColor(colors[chosenColorIndex]))

            binding.tvTitle.text = dashboardStoreItem.name
            binding.tvPercentage.text = dashboardStoreItem.percentage
            binding.tvTargetValue.text = dashboardStoreItem.target.toString()
            binding.tvAchievementValue.text = dashboardStoreItem.achievement.toString()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DashboardStoreItem>() {
            override fun areItemsTheSame(
                oldItem: DashboardStoreItem,
                newItem: DashboardStoreItem
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: DashboardStoreItem,
                newItem: DashboardStoreItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardStoreItemViewHolder {
        val binding =
            DashboardStoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardStoreItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardStoreItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

}