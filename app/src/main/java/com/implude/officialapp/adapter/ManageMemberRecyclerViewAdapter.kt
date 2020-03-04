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
import com.implude.officialapp.databinding.ItemManageMemberBinding
import com.implude.officialapp.databinding.ItemNoticeBinding
import com.implude.officialapp.model.UserModel


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
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_manage_member, parent, false)
        return ManageMemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding : ItemManageMemberBinding = (holder as ManageMemberViewHolder).binding
        binding.item = ItemViewModels[position] as UserModel
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
        var binding : ItemManageMemberBinding = DataBindingUtil.bind(itemView)!!
    }

}