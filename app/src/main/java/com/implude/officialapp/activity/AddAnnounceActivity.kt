package com.implude.officialapp.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.implude.officialapp.R
import com.implude.officialapp.custom.CupertinoRecyclerViewAdapter
import com.implude.officialapp.databinding.ActivityAddAnnounceBinding
import kotlinx.android.synthetic.main.activity_add_announce.*
import kotlinx.android.synthetic.main.activity_setprofile.*

class AddAnnounceActivity : AppCompatActivity() {
    val images: ObservableArrayList<String> = ObservableArrayList()

    //TODO: 라디오버튼이 안 이쁘다 바꿔야한다
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityAddAnnounceBinding = DataBindingUtil.setContentView<ActivityAddAnnounceBinding>(this, R.layout.activity_add_announce)
        val adapter: CupertinoRecyclerViewAdapter = CupertinoRecyclerViewAdapter()

        binding.items = images
        recyclerview_image.adapter = adapter

        radioType.setOnCheckedChangeListener { group, checkedId ->
            //이 방식으로 하는 이유는 사용자 입력했던 데이터 보존 하기 위해서
            if(checkedId == R.id.radioApplication)
            {
                layout_application.visibility = View.VISIBLE
                layout_notice.visibility = View.GONE
            }
            if(checkedId == R.id.radioNotice)
            {
                layout_application.visibility = View.GONE
                layout_notice.visibility = View.VISIBLE
            }
        }

        button_close.setOnClickListener {
            finish()
        }
    }
}