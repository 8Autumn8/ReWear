package com.example.rewear.addClothes

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStoragePublicDirectory
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.rewear.BuildConfig
import com.example.rewear.R
import com.example.rewear.database.DataBaseHelper
import com.example.rewear.objects.ClothesData
import kotlinx.android.synthetic.main.activity_add_clothes.*
import kotlinx.android.synthetic.main.activity_clothes_tracking.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.card_closet.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*



class AddClothesActivity : AppCompatActivity(), AddClothesContract.View {
    private var ibCamara: ImageButton? = null
    private var imageView: ImageView? = null

    private var presenter: AddClothesContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_clothes)
        var presenter = AddClothesPresenter(this)

        // initialize temp image directory on first launch
        val tempImageUri = initTempUri()

        registerTakePictureLauncher(tempImageUri) //Binds button to launch camera activity

        btAddClothes.setOnClickListener {

            if (imageView?.drawable != null) {
                val bmTemp:Bitmap = (ivReceipt!!.drawable as BitmapDrawable).bitmap //(ivReceipt!!.drawable as BitmapDrawable) ivReceipt
                val bos = ByteArrayOutputStream()
                bmTemp.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                val img: ByteArray = bos.toByteArray()

                presenter.addClothes(1,img,"","2022-8-23")


            }

        }


    }
    private fun initTempUri(): Uri {
        //gets the temp_images dir
        val tempImagesDir = File(
            applicationContext.filesDir, //this function gets the external cache dir
            getString(R.string.temp_images_dir)) //gets the directory for the temporary images dir

        tempImagesDir.mkdir() //Create the temp_images dir

        //Creates the temp_image.jpg file
        val tempImage = File(
            tempImagesDir, //prefix the new abstract path with the temporary images dir path
            getString(R.string.temp_image)) //gets the abstract temp_image file name

        //Returns the Uri object to be used with ActivityResultLauncher
        return FileProvider.getUriForFile(
            applicationContext,
            getString(R.string.authorities),
            tempImage)
    }


    private fun registerTakePictureLauncher(path: Uri) {
        imageView = findViewById(R.id.ivReceipt)
        ibCamara = findViewById(R.id.ibCamera) //gets the ImageView object

        //Creates the ActivityResultLauncher
        val resultLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()){
            imageView!!.setImageURI(null) //rough handling of image changes. Real code need to handle different API levels.
            imageView!!.setImageURI(path)

        }

        //Launches the camera when button is pressed.
        ibCamara!!.setOnClickListener {
            resultLauncher.launch(path) //launches the activity here
        }


    }




}