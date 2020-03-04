package com.cclean.implude.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.cclean.implude.R
import com.cclean.implude.databinding.ItemApplicationBinding
import com.cclean.implude.databinding.ItemNoticeBinding
import com.cclean.implude.model.ApplicationItemModel
import com.cclean.implude.model.BaseItemModel
import com.cclean.implude.model.NoticeItemModel
import com.cclean.implude.model.UserModel


class ManageMemberRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        @BindingAdapter("app:item")
        @JvmStatic
        fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<UserModel>) {
            val adapter : ManageMemberRecyclerViewAdapter = recyclerView.adapter as ManageMemberRecyclerViewAdapter;
            adapter.setItems(items);
        }
    }

    private var ItemViewModels: ArrayList<UserModel> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false)
        return ManageMemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val binding : ItemNoticeBinding = (holder as ManageMemberViewHolder).binding
//        binding.item = ItemViewModels[position] as UserModel
    }

    override fun getItemCount(): Int {
        return ItemViewModels.size
    }

    public fun setItems(items: ArrayList<UserModel>)
    {
        ItemViewModels = items
        notifyDataSetChanged();
    }

    class ManageMemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : ItemNoticeBinding = DataBindingUtil.bind(itemView)!!
    }

}