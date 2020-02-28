package com.cclean.implude.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.cclean.implude.AnnounceViewModel
import com.cclean.implude.R
import com.cclean.implude.adapter.AnnounceRecyclerViewAdapter
import com.cclean.implude.databinding.ActivityMainBinding.inflate
import com.cclean.implude.databinding.FragmentAnnounceBinding
import com.cclean.implude.model.ApplicationItemModel
import com.cclean.implude.model.BaseItemModel
import com.cclean.implude.model.NoticeItemModel
import kotlinx.android.synthetic.main.fragment_announce.view.*

class AnnounceFragment : Fragment() {
    private val adapter: AnnounceRecyclerViewAdapter = AnnounceRecyclerViewAdapter()

    companion object {
        fun newInstance() = AnnounceFragment()
    }

    private lateinit var viewModel: AnnounceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(AnnounceViewModel::class.java)

        val binding: FragmentAnnounceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_announce, container, false)
        binding.viewModel = viewModel
        binding.recyclerviewMain.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
