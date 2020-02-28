package com.cclean.implude.fragment

import android.graphics.Movie
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
    private val itemList: ObservableArrayList<BaseItemModel> = ObservableArrayList<BaseItemModel>()
    private val adapter: AnnounceRecyclerViewAdapter = AnnounceRecyclerViewAdapter()

    companion object {
        fun newInstance() = AnnounceFragment()
    }

    private lateinit var viewModel: AnnounceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentAnnounceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_announce, container, false);

        binding.recyclerviewMain.adapter = adapter
        binding.itemList = itemList

        val noticeTest = NoticeItemModel(
            "설문조사 해주세요",
            "쌤이 시키네요 무조건 하세요\n청소년 기업가 정신에 관한 실태조사 입니다",
            mutableListOf(),
            "",
            isImportant = false,
            isAllowedComment = false
        )

        val applicationTest = ApplicationItemModel(
            "세무사님 특강 보러갈 사람?",
            "오늘 시청각실에서 이주은 세무사님이\n" +
                    "창업에 관련한 세무 내용에 관해 특강을 해주신다고 합니다\n" +
                    "보고싶은 사람은 보세요",
            mutableListOf(),
            "7월 2일 오후 5시",
            "신관 시청각실"
        )

        itemList.add(noticeTest)
        itemList.add(applicationTest)

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AnnounceViewModel::class.java)
    }

}
