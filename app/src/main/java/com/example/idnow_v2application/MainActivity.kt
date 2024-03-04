package com.example.idnow_v2application

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var captureButton: Button
    private lateinit var downloadButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        captureButton = findViewById(R.id.captureButton)
        downloadButton = findViewById(R.id.downloadButton)
        val afficherButton = findViewById<Button>(R.id.afficher_products)

        captureButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 1)
        }

        downloadButton.setOnClickListener {
            val bitmapDrawable = imageView.drawable as BitmapDrawable
            val image = bitmapDrawable.bitmap
            val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val file = File(folder, "image.jpg")
            try {
                val outputStream = FileOutputStream(file)
                image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
                Toast.makeText(this, "Image enregistrée dans la galerie", Toast.LENGTH_SHORT).show()
                MediaScannerConnection.scanFile(this, arrayOf(file.toString()), null) { _, uri ->
                    Log.i("ExternalStorage", "Scanned $file:\n$uri")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        afficherButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ProductListActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }
}
