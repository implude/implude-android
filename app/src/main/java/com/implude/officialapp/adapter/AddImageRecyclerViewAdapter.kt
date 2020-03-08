package com.implude.officialapp.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.implude.officialapp.R
import com.implude.officialapp.adapter.AddImageRecyclerViewAdapter.AddImageViewHolder
import com.implude.officialapp.databinding.ItemImageBinding
import kotlin.collections.ArrayList

class AddImageRecyclerViewAdapter(private val addItemClickable: AddItemClickable) : RecyclerView.Adapter<AddImageViewHolder>() {
    private var items: ArrayList<Bitmap> = ArrayList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemImageBinding>(
            inflater, R.layout.item_image, parent, false
        )
        context = parent.context
        return AddImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun onBindViewHolder(holder: AddImageViewHolder, position: Int) {
        if (position == items.size) {
            holder.binding.image.setImageDrawable(
                context.resources.getDrawable(R.drawable.ic_add_image, null))
            holder.binding.image.setOnClickListener { addItemClickable.onImageClick() }
        } else {
            holder.bind(position)
        }
    }

    fun setItems(items: ArrayList<Bitmap>) {
        this.items = items
        notifyDataSetChanged()
    }

    companion object {
        @BindingAdapter("app:item")
        @JvmStatic
        fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<Bitmap>) {
            val adapter: AddImageRecyclerViewAdapter =
                recyclerView.adapter as AddImageRecyclerViewAdapter;
            adapter.setItems(items);
        }

        @BindingAdapter("app:image")
        @JvmStatic
        fun loadImage(imageView: ImageView, bitmap: Bitmap?) {
            if (bitmap != null)
                imageView.setImageBitmap(bitmap)
        }
    }

    inner class AddImageViewHolder(public val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.item = items[position]
        }
    }
}

interface AddItemClickable {
    fun onImageClick()
}