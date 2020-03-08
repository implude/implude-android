package com.implude.officialapp.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.implude.officialapp.R
import com.implude.officialapp.adapter.AddImageRecyclerViewAdapter
import com.implude.officialapp.adapter.AddItemClickable
import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_APPLICATION
import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_NOTICE
import com.implude.officialapp.custom.CupertinoDialog
import com.implude.officialapp.databinding.ActivityAddAnnounceBinding
import com.implude.officialapp.model.ApplicationItemModel
import com.implude.officialapp.model.NoticeItemModel
import com.implude.officialapp.viewmodel.AddAnnounceViewModel
import kotlinx.android.synthetic.main.activity_add_announce.*
import kotlinx.android.synthetic.main.activity_add_announce.button_add
import kotlinx.android.synthetic.main.activity_add_member.*
import kotlinx.android.synthetic.main.layout_add_application.*
import kotlinx.android.synthetic.main.layout_add_notice.*
import kotlinx.android.synthetic.main.layout_title.view.*
import kotlinx.coroutines.launch

class AddAnnounceActivity : AppCompatActivity() {
    private lateinit var viewModel: AddAnnounceViewModel

    //TODO: 뷰 키보드한테 밀려서 위로 올라오는 현상 수정
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddAnnounceBinding =
            DataBindingUtil.setContentView<ActivityAddAnnounceBinding>(this, R.layout.activity_add_announce)

        viewModel = ViewModelProviders.of(this).get(AddAnnounceViewModel::class.java)
        binding.vm = viewModel

        val adapter: AddImageRecyclerViewAdapter = AddImageRecyclerViewAdapter(object : AddItemClickable {
            override fun onImageClick() {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                this@AddAnnounceActivity.startActivityForResult(intent, REQUEST_CODE)
            }
        })

        recyclerview_image.adapter = adapter

        check_add_link.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                edit_link.visibility = View.VISIBLE
            } else {
                edit_link.text = ""
                edit_link.visibility = View.GONE
            }
        }

        radioType.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioApplication) {
                layout_application.visibility = View.VISIBLE
                layout_notice.visibility = View.GONE
            }
            if (checkedId == R.id.radioNotice) {
                layout_application.visibility = View.GONE
                layout_notice.visibility = View.VISIBLE
            }
        }

        layout_nav.button_back.setOnClickListener { finish() }

        button_add.setOnClickListener {
            button_add.startAnimation()

            lifecycleScope.launch {
                val urls: ArrayList<String>? = viewModel.uploadImages()

                if(radioType.checkedRadioButtonId == R.id.radioApplication) {
                    val post = ApplicationItemModel(
                        edit_title.text,
                        edit_content.text,
                        urls,
                        edit_date.text,
                        edit_place.text)

                    viewModel.uploadAnnounce(post, TYPE_APPLICATION)
                } else {
                    val post = NoticeItemModel(
                        edit_title.text,
                        edit_content.text,
                        urls,
                        edit_link.text,
                        check_important.isChecked,
                        check_able_comment.isChecked)
                    viewModel.uploadAnnounce(post, TYPE_NOTICE)
                }

                CupertinoDialog(this@AddAnnounceActivity).show("성공!", "성공적으로 공지/신청이 추가 되었습니다")
                button_add.revertAnimation()
                button_add.background = this@AddAnnounceActivity.getDrawable(R.drawable.shape_round_accent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && null != data)
        {
            try{
                val inputStream = getContentResolver().openInputStream(data.data!!)
                val img = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()
                viewModel.addImageItem(img)
            }catch(e : Exception) {
                CupertinoDialog(this@AddAnnounceActivity).show("죄송합니다", "이미지 업로드에 실패했습니다\n사유: ${e.message}")
            }
        }

    }

    companion object {
        public val REQUEST_CODE = 0
    }
}