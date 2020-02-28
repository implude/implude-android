package com.cclean.implude

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.cclean.implude.model.ApplicationItemModel
import com.cclean.implude.model.BaseItemModel
import com.cclean.implude.model.NoticeItemModel

class AnnounceViewModel : ViewModel() {
    val itemList: ObservableArrayList<BaseItemModel> = ObservableArrayList<BaseItemModel>()

    init {
        //test
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
    }
}
