package com.implude.officialapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.implude.officialapp.R
import com.implude.officialapp.databinding.ItemApplicationBinding
import com.implude.officialapp.databinding.ItemNoticeBinding
import com.implude.officialapp.model.ApplicationItemModel
import com.implude.officialapp.model.BaseItemModel
import com.implude.officialapp.model.NoticeItemModel


class AnnounceRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_NOTICE = 1
        const val TYPE_APPLICATION = 2

        @BindingAdapter("app:item")
        @JvmStatic
        fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<BaseItemModel>) {
            val adapter : AnnounceRecyclerViewAdapter = recyclerView.adapter as AnnounceRecyclerViewAdapter;
            adapter.setItems(items);
        }
    }

    private var items: ArrayList<BaseItemModel> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == Companion.TYPE_NOTICE) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false)
            NoticeViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_application, parent, false)
            ApplicationViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == Companion.TYPE_NOTICE) {
            val binding : ItemNoticeBinding = (holder as NoticeViewHolder).binding
            binding.item = items[position] as NoticeItemModel
        } else {
            val binding : ItemApplicationBinding = (holder as ApplicationViewHolder).binding
            binding.item = items[position] as ApplicationItemModel
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public fun setItems(items: ArrayList<BaseItemModel>)
    {
        this.items = items
        notifyDataSetChanged();
    }

    class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : ItemNoticeBinding = DataBindingUtil.bind(itemView)!!
    }

    class ApplicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : ItemApplicationBinding = DataBindingUtil.bind(itemView)!!
    }
}