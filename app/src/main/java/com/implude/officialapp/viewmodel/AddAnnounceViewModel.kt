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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.implude.officialapp.R
import com.implude.officialapp.activity.SetProfileActivity
import com.implude.officialapp.adapter.AddItemClickable
import com.implude.officialapp.adapter.AnnounceRecyclerViewAdapter.Companion.TYPE_APPLICATION
import com.implude.officialapp.custom.CupertinoDialog
import com.implude.officialapp.model.ApplicationItemModel
import com.implude.officialapp.model.BaseItemModel
import com.implude.officialapp.model.NoticeItemModel
import com.implude.officialapp.model.UserModel
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class AddAnnounceViewModel(val app: Application) : AndroidViewModel(app) {
    private val db = FirebaseFirestore.getInstance()
    public val images: ObservableArrayList<Bitmap> = ObservableArrayList()

    fun addImageItem(bitmap: Bitmap)
    {
        images.add(bitmap)
    }

    suspend fun uploadAnnounce(item: BaseItemModel, type: Int): Boolean
    {
        val imageUrls: ArrayList<String> = ArrayList<String>()

        if(type == TYPE_APPLICATION)
        {
            db.collection("announce")
                .document()
                .set(item as ApplicationItemModel)
                .await()
        }
        else
        {
            db.collection("announce")
                .document()
                .set(item as NoticeItemModel)
                .await()
        }

        return true
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

    private suspend fun uploadImage(ref: StorageReference, bitmap: Bitmap): String {
        //Bitmap to Byte
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        return ref.putBytes(baos.toByteArray()).continueWithTask { task ->
            if (!task.isSuccessful)
                task.exception?.let { throw it }
            ref.downloadUrl
        }.await().toString()
    }
}