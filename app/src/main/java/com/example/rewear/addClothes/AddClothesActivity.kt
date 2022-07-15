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
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.rewear.BuildConfig
import com.example.rewear.R
import kotlinx.android.synthetic.main.activity_add_clothes.*
import kotlinx.android.synthetic.main.card_closet.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*



class AddClothesActivity : AppCompatActivity(), AddClothesContract.View {
    private var ibCamara: ImageButton? = null
    private var ivReceipt: ImageView? = null
    private val IMAGE_CAPTURE_CODE = 1001
    private var pathToFile: String? = null

    private var presenter: AddClothesContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_clothes)
        val presenter = AddClothesPresenter(this)

        ivReceipt = findViewById(R.id.ivReceipt)
        ibCamara = findViewById(R.id.ibCamera)

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 1
            )
        }

        //Handles Camera button click
        ibCamara!!.setOnClickListener {
            try {
                dispatchPictureTakeAction()
            } catch (e: IOException) {
                Log.d("MainActivity", "Error. $e")
            }
        }

        //Handles Add button click
        //Handles Add button click
        btAddExpense.setOnClickListener {

            if (ivReceipt?.drawable != null) {
                val bmTemp:Bitmap = (ivReceipt!!.drawable as BitmapDrawable).bitmap //(ivReceipt!!.drawable as BitmapDrawable) ivReceipt
                val bos = ByteArrayOutputStream()
                bmTemp.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                val img: ByteArray = bos.toByteArray()
                presenter.addClothes(1,img,"","")
                //ivReceipt!!.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.size))
            }

        }


        addNumTwo.setOnClickListener {

            if (ivReceipt?.drawable != null) {
                val ivReceipt2 = findViewById<ImageView>(R.id.ivReceipt2)
                val bmTemp:Bitmap = (ivReceipt!!.drawable as BitmapDrawable).bitmap //(ivReceipt!!.drawable as BitmapDrawable) ivReceipt
                val bos = ByteArrayOutputStream()
                bmTemp.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                val img: ByteArray = bos.toByteArray()
                presenter.addClothes(1,img,"","")
                ivReceipt2!!.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.size))
            }

        }


    }

    //Handles the return to this activity
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == RESULT_OK) {
            //Return from the camera click
            val bitReceipt = BitmapFactory.decodeFile(pathToFile)
            ivReceipt!!.setImageBitmap(bitReceipt)
        }
    }

    //Handles taking picture
    private fun dispatchPictureTakeAction()  {
        val takePic: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePic.resolveActivity(packageManager) != null) {
            var photoFile: File? = null;
            photoFile = createPhotoFile();
            if (photoFile != null) {
                pathToFile = photoFile.absolutePath;
                var photoURI: Uri = FileProvider.getUriForFile (this, BuildConfig.APPLICATION_ID+".provider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePic, IMAGE_CAPTURE_CODE)
            }
        }
    }

    //Handles storing the picture for use
    private fun createPhotoFile(): File? {
        val name: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        var image: File? = null
        try {
            image = File.createTempFile(name, ".jpg", storageDir)
        } catch (e: IOException) {
            Log.d("myLog", "Camara error. $e")

        }
        return image
    }

}