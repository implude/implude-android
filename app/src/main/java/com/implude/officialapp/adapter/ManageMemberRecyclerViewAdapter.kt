package com.implude.officialapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.implude.officialapp.ManageMemberViewModel
import com.implude.officialapp.R
import com.implude.officialapp.databinding.ItemManageMemberBinding
import com.implude.officialapp.model.UserModel


class ManageMemberRecyclerViewAdapter(private val viewModel: ManageMemberViewModel)
    : RecyclerView.Adapter<ManageMemberRecyclerViewAdapter.ManageMemberViewHolder>() {

    private var items: ArrayList<UserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageMemberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemManageMemberBinding>(
            inflater, R.layout.item_manage_member, parent, false)
        return ManageMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ManageMemberViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: ArrayList<UserModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ManageMemberViewHolder(private val binding: ItemManageMemberBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.run {
                this.positon = positon
                item = items[position]
                vm = viewModel
            }
        }
    }

    companion object {
        @BindingAdapter("app:item")
        @JvmStatic
        fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<UserModel>) {
            val adapter: ManageMemberRecyclerViewAdapter = recyclerView.adapter as ManageMemberRecyclerViewAdapter
            adapter.setItems(items)
        }

        @BindingAdapter("app:imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty())
                Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}