package com.implude.officialapp.adapter

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
import com.implude.officialapp.databinding.ItemManageMemberBinding
import com.implude.officialapp.model.UserModel


class ManageMemberRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        @BindingAdapter("app:item")
        @JvmStatic
        fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<UserModel>) {
            val adapter : ManageMemberRecyclerViewAdapter = recyclerView.adapter as ManageMemberRecyclerViewAdapter;
            adapter.setItems(items);
        }

        @BindingAdapter("app:imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty())
                Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    private var itemViewModel: ArrayList<UserModel> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_manage_member, parent, false)
        return ManageMemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding : ItemManageMemberBinding = (holder as ManageMemberViewHolder).binding
        binding.item = itemViewModel[position] as UserModel
    }

    override fun getItemCount(): Int {
        return itemViewModel.size
    }

    public fun setItems(items: ArrayList<UserModel>)
    {
        itemViewModel = items
        notifyDataSetChanged();
    }

    class ManageMemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : ItemManageMemberBinding = DataBindingUtil.bind(itemView)!!
    }

}