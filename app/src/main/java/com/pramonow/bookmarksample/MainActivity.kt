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




/*
    Example on making recycler view (list)
    Uncomment the call for trying API call
 */
class MainActivity : AppCompatActivity(), ClickInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var bookmarkObjectAdapter: BookmarkObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.bookmark_list)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(linearLayoutManager)

        bookmarkObjectAdapter = BookmarkObjectAdapter()


        var bookmarkList = ArrayList<BookmarkObject>()
        bookmarkList.add(BookmarkObject(0,"Object 0", false))
        bookmarkList.add(BookmarkObject(1,"Object 1", false))
        bookmarkList.add(BookmarkObject(2,"Object 2", false))
        bookmarkList.add(BookmarkObject(3,"Object 3", false))
        bookmarkList.add(BookmarkObject(4,"Object 4", false))
        bookmarkList.add(BookmarkObject(5,"Object 5", false))
        bookmarkList.add(BookmarkObject(6,"Object 6", false))
        bookmarkList.add(BookmarkObject(7,"Object 7", false))

        bookmarkObjectAdapter.setBookmarkList(bookmarkList)
        bookmarkObjectAdapter.setAdapterClickCallback(this)
        //bookmarkObjectAdapter.notifyDataSetChanged()

        //Set callback for clicking
        //sampleAdapter.setAdapterClickCallback(this)
        recyclerView.setAdapter(bookmarkObjectAdapter)

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, IntentFilter("mark"));

    }

    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Get extra data included in the Intent
            val message = intent.getIntExtra("index",0)

            bookmarkObjectAdapter.bookmarkList[message].bookmark = !bookmarkObjectAdapter.bookmarkList[message].bookmark
            bookmarkObjectAdapter.notifyDataSetChanged()

        }
    }

    override fun onClick(index:Int) {
        val intent = Intent(this, NewActivty::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }

    override fun onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
        super.onDestroy()
    }
}
