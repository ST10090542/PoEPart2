package com.berni.timetrackerapp.ui.fragments.photo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.berni.timetrackerapp.R

class PhotoFragment : AppCompatActivity() {
    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    private lateinit var btnAddPhoto: Button
    private lateinit var imgPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_photo)

        btnAddPhoto = findViewById(R.id.btnAddPhoto)
        imgPhoto = findViewById(R.id.imgPhoto)

        btnAddPhoto.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imgPhoto.setImageBitmap(imageBitmap)
            imgPhoto.visibility = ImageView.VISIBLE
        }
    }
}