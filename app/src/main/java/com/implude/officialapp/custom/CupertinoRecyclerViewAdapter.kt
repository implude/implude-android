package com.implude.officialapp.custom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.implude.officialapp.R
import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter
import com.implude.officialapp.custom.CupertinoRecyclerViewAdapter.*
import com.implude.officialapp.databinding.ItemImageBinding
import com.implude.officialapp.databinding.ItemManageMemberBinding
import com.implude.officialapp.databinding.ItemNoticeBinding
import com.implude.officialapp.model.BaseItemModel
import com.implude.officialapp.model.UserModel

class CupertinoRecyclerViewAdapter : RecyclerView.Adapter<CupertinoViewHolder>(){

    private var items: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CupertinoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemImageBinding>(
            inflater, R.layout.item_image, parent, false)
        return CupertinoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CupertinoViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setItems(items: ArrayList<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    companion object {
        @BindingAdapter("app:item")
        @JvmStatic
        fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<String>) {
            val adapter : CupertinoRecyclerViewAdapter = recyclerView.adapter as CupertinoRecyclerViewAdapter;
            adapter.setItems(items);
        }

        @BindingAdapter("app:imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty())
                Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    inner class CupertinoViewHolder(private val binding: ItemImageBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.item = items[position]
        }
    }
}