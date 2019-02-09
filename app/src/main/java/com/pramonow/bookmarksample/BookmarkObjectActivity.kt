package com.pramonow.bookmarksample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.support.v4.content.LocalBroadcastManager
import android.content.Intent
import android.widget.TextView

class BookmarkObjectActivity:AppCompatActivity(){

    lateinit var button: Button
    lateinit var text:TextView
    var bookmarked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        button = findViewById(R.id.button)
        text = findViewById(R.id.text)

        val id = intent.getIntExtra("id",0)
        bookmarked = intent.getBooleanExtra("bookmarked",false)

        setBookmarkText(bookmarked)

        button.setOnClickListener { sendMessage(id) }
    }

    //This will send broadcast to the bookmark activity list and will issue bookmark/unbookmark to them
    //Sending id
    private fun sendMessage(id: Int) {

        bookmarked = !bookmarked
        setBookmarkText(bookmarked)

        val intent = Intent("mark")
        intent.putExtra("id",id)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun setBookmarkText(bookmarked: Boolean) {
        if(bookmarked)
            text.setText("This object is bookmarked")
        else
            text.setText("This object is not bookmarked")
    }
}