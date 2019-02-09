package com.pramonow.bookmarksample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager

class BookmarkListActivity : AppCompatActivity(), ClickInterface {

    private lateinit var recyclerView: RecyclerView
    lateinit var bookmarkObjectAdapter: BookmarkObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.bookmark_list)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        bookmarkObjectAdapter = BookmarkObjectAdapter()

        val bookmarkList = ArrayList<BookmarkObject>()

        //Creating list of bookmark object
        bookmarkList.add(BookmarkObject(0,"Object 0, Click to navigate", false))
        bookmarkList.add(BookmarkObject(1,"Object 1, Click to navigate", false))
        bookmarkList.add(BookmarkObject(2,"Object 2, Click to navigate", false))
        bookmarkList.add(BookmarkObject(3,"Object 3, Click to navigate", false))
        bookmarkList.add(BookmarkObject(4,"Object 4, Click to navigate", false))
        bookmarkList.add(BookmarkObject(5,"Object 5, Click to navigate", false))
        bookmarkList.add(BookmarkObject(6,"Object 6, Click to navigate", false))
        bookmarkList.add(BookmarkObject(7,"Object 7, Click to navigate", false))

        bookmarkObjectAdapter.bookmarkList = bookmarkList
        bookmarkObjectAdapter.setAdapterClickCallback(this)
        recyclerView.adapter = bookmarkObjectAdapter

        //Register receiver to receive data from bookmark object activity
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, IntentFilter("mark"))
    }

    //Broadcast receiver used to obtain
    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            // Get extra data included in the Intent
            val message = intent.getIntExtra("id",0)

            // Iterate the adapter then bookmark/unbookmark it based on the corresponding id
            bookmarkObjectAdapter.bookmarkList.forEach {
                if(it.id == message){
                    it.bookmark = !it.bookmark
                }
            }

            bookmarkObjectAdapter.notifyDataSetChanged()
        }
    }

    //Click interface that will be used to navigate for the adapter
    override fun onClick(id:Int, bookmarked:Boolean) {
        val intent = Intent(this, BookmarkObjectActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("bookmarked", bookmarked)
        startActivity(intent)
    }

    override fun onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
        super.onDestroy()
    }
}
