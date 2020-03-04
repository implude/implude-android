package com.implude.officialapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.implude.officialapp.AnnounceViewModel
import com.implude.officialapp.R
import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter
import com.implude.officialapp.databinding.ActivityMainBinding.inflate
import com.implude.officialapp.databinding.FragmentAnnounceBinding

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
