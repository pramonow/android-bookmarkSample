package com.pramonow.bookmarksample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.support.v4.content.LocalBroadcastManager
import android.content.Intent

class BookmarkObjectActivity:AppCompatActivity(){

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        button = this.findViewById(R.id.button)

        val id = intent.getIntExtra("id",0)
        button.setOnClickListener { sendMessage(id) }
    }

    //This will send broadcast to the bookmark activity list and will issue bookmark/unbookmark to them
    //Sending id
    private fun sendMessage(id: Int) {
        val intent = Intent("mark")
        intent.putExtra("id",id)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}