package com.rabi.kudmi.createimgfromlayout

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import com.rabi.kudmi.createimgfromlayout.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root);
        init();
    }

    private fun init()
    {
        binding.btnSave.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?) {

                binding.relativeLayout.isDrawingCacheEnabled = true;
                binding.relativeLayout.buildDrawingCache();
                binding.relativeLayout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH;
                val bitmap:Bitmap = binding.relativeLayout.drawingCache;

                save(bitmap);


            }

        })
    }

    private fun save(bitmap:Bitmap)
    {

        val root:String = Environment.getExternalStorageDirectory().absolutePath;
        val file = File(root+"/Download")

        val imgName = "lulu"+Calendar.getInstance().time.time+".jpeg";
        val myFile = File(file,imgName)
        try {
            val fileOutputStream = FileOutputStream(myFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream)
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this,"Success check ",Toast.LENGTH_SHORT).show()
            binding.relativeLayout.isDrawingCacheEnabled = false;
        }
        catch (e:IOException)
        {

        }

    }


}