package com.implude.officialapp.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.implude.officialapp.R
import com.implude.officialapp.custom.CupertinoDialog
import com.implude.officialapp.model.UserModel
import kotlinx.android.synthetic.main.activity_setprofile.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class SetProfileActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_CODE = 0
    }

    private val db = FirebaseFirestore.getInstance()
    private lateinit var userDoc : DocumentSnapshot
    private lateinit var user : UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setprofile)

        val currentUser = FirebaseAuth.getInstance().currentUser
        db.collection("users").document(currentUser?.uid.toString()).get()
            .addOnCompleteListener { task ->
                if(task.isSuccessful)
                {
                    user = task.result?.toObject(UserModel::class.java)!!
                    Glide.with(this@SetProfileActivity).load(user.profile).into(img_profile as ImageView)
                } else {
                    CupertinoDialog(this@SetProfileActivity).show("죄송합니다", "계정에 문제가 생긴 것 같습니다\n해결을 위해 동장혹은 부동장에게 문의하세요")
                    finish()
                }
            }

        img_edit_profile.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_CODE)
        }

        button_close.setOnClickListener {
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && null != data)
        {
            try{
                //FirebaseStore instance Name : gs://impludeapp-1582891257809.appspot.com
                val storage: FirebaseStorage = FirebaseStorage.getInstance(getString(R.string.firestorage_ref))
                val storageRef: StorageReference = storage.getReference()
                val time = SimpleDateFormat("yyyyMMHH_mmss").format(Date())
                val imageRef = storageRef.child("profiles/" + time)

                val inputStream = getContentResolver().openInputStream(data.data!!)
                val img = BitmapFactory.decodeStream(inputStream)

                //Bitmap to Byte
                val baos = ByteArrayOutputStream()
                img.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                val uploadTask = imageRef.putBytes(data)
                uploadTask.addOnFailureListener { it ->
                    CupertinoDialog(this@SetProfileActivity).show("죄송합니다", "이미지 업로드에 실패했습니다\n사유: ${it.message}")
                }.addOnSuccessListener {
                    img_profile.setImageBitmap(img);
                }

                //URL 다운로드
                val urlTask = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    imageRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = FirebaseAuth.getInstance().currentUser

                        user.profile = task.result.toString()
                        db.collection("users").document(currentUser?.uid.toString()).set(user)
                    } else {
                        CupertinoDialog(this@SetProfileActivity).show("죄송합니다", "이미지 업로드에 실패했습니다\n사유: ${task.exception?.message}")
                    }
                }

                inputStream?.close()
            }catch(e : Exception)
            {
                CupertinoDialog(this@SetProfileActivity).show("죄송합니다", "이미지 업로드에 실패했습니다\n사유: ${e.message}")
            }
        }

    }
}