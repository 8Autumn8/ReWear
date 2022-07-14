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
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.rewear.BuildConfig
import com.example.rewear.R
import kotlinx.android.synthetic.main.activity_add_clothes.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class AddClothesActivity : AppCompatActivity(), AddClothesContract.View {
    var ibCamara: ImageButton? = null
    var ivReceipt: ImageView? = null
    val IMAGE_CAPTURE_CODE = 1001
    var pathToFile: String? = null

    private var presenter: AddClothesContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_clothes)
        val presenter = AddClothesPresenter(this)

        ivReceipt = findViewById(R.id.ivReceipt);
        ibCamara = findViewById(R.id.ibCamera);

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
                dispatchPictureTakeAction();
            } catch (e: IOException) {
                Log.d("MainActivity", "Error. "+e.toString() );
            }
        }

        //Handles Add button click
        //Handles Add button click
        /*btAddExpense.setOnClickListener {
            val expense = Expense()

//Populate the expense data class
            if (etDate.getTag() != null) expense.setDate(
                etDate.getTag().toString()
            ) else expense.setDate("")
            expense.setPayee(etPayee.getText().toString())
            expense.setAmount(etAmount.getText().toString())
            expense.setCategory(spCategory.getSelectedItem().toString())
            expense.setPayment_method(spPaymentMethod.getSelectedItem().toString())
            if (ivReceipt.getDrawable() != null) {
                val bmTemp = (ivReceipt.getDrawable() as BitmapDrawable).bitmap
                val bos = ByteArrayOutputStream()
                bmTemp.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                val img: ByteArray = bos.toByteArray()
                expense.setReceipt(img)
            }

//Add Expense
            presenter.AddExpense(expense)
        }*/

    }

    //Handles the return to this activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == RESULT_OK) {
            //Return from the camera click
            val bitReceipt = BitmapFactory.decodeFile(pathToFile)
            ivReceipt!!.setImageBitmap(bitReceipt)
        }
    }

    //Handles taking picture
    fun dispatchPictureTakeAction()  {
        val takePic: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var image: File? = null;
        if (takePic.resolveActivity(getPackageManager()) != null) {
            var photoFile: File? = null;
            photoFile = createPhotoFile();

            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                var photoURI: Uri = FileProvider.getUriForFile (this, BuildConfig.APPLICATION_ID+".provider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePic, IMAGE_CAPTURE_CODE);
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
            Log.d("myLog", "Camara error. " + e.toString())

        }
        return image
    }

}