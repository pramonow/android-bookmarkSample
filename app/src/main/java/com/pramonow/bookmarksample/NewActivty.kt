package com.pramonow.bookmarksample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.support.v4.content.LocalBroadcastManager
import android.content.Intent



class NewActivty:AppCompatActivity(){

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        button = findViewById(R.id.button)

        val index = intent.getIntExtra("index",0)

        button.setOnClickListener { sendMessage(index) }

    }

    private fun sendMessage(int: Int) {
        val intent = Intent("mark")
        // You can also include some extra data.
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }



}