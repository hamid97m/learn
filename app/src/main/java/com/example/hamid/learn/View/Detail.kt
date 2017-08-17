package com.example.hamid.learn.View
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.hamid.learn.R
import kotlinx.android.synthetic.main.activity_detail.*


class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        messege.text="sad"

        Toast.makeText(this,"Asdasd",5000).show()


        map.setOnClickListener {
            Toast.makeText(this,"Asdasd",5000).show()
        }





        }



    }

