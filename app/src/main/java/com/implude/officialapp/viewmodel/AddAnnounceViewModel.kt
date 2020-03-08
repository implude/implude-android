package com.implude.officialapp.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.airbnb.paris.R2.id.time
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.implude.officialapp.R
import com.implude.officialapp.activity.SetProfileActivity
import com.implude.officialapp.adapter.AddItemClickable
import com.implude.officialapp.custom.CupertinoDialog
import com.implude.officialapp.model.UserModel
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class AddAnnounceViewModel(val app: Application) : AndroidViewModel(app) {
    public val images: ObservableArrayList<Bitmap> = ObservableArrayList()

    fun addImageItem(bitmap: Bitmap)
    {
        images.add(bitmap)
    }

    //TODO: Promise 활용해서 업로드 좀 깔끔하게 하기?
    suspend fun uploadImages(): ArrayList<String>?
    {
        val imageUrls: ArrayList<String> = ArrayList<String>()

        val storage: FirebaseStorage = FirebaseStorage.getInstance(app.getString(R.string.firestorage_ref))
        val storageRef: StorageReference = storage.getReference()

        for ((i, bitmap) in images.withIndex())
        {
            val imageRef = storageRef.child("post/" + time + "_" + i)
            val url = uploadImage(imageRef, bitmap)
            if (url.isNotEmpty())
                imageUrls.add(url)
            else {
                return null
            }
        }

        return imageUrls
    }

    private suspend fun uploadImage(ref: StorageReference, bitmap: Bitmap) : String
    {
        var imageUrl : String = ""

        //Bitmap to Byte
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        //왜 링크받으려면 continueWithTask 해야하는 거지 기본으로 주면 얼마나 좋아...
        ref.putBytes(baos.toByteArray()).await().task.continueWithTask { task ->
            if (!task.isSuccessful)
                task.exception?.let { throw it }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful)
                imageUrl = task.result.toString()
        }

        return imageUrl
    }
}