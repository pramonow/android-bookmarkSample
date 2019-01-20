package com.pramonow.bookmarksample

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.ArrayList

class BookmarkObjectAdapter : RecyclerView.Adapter<BookmarkObjectAdapter.BookmarkVH>() {

    internal var bookmarkList: List<BookmarkObject> = ArrayList<BookmarkObject>()
    lateinit var clickInterface: ClickInterface

    fun setAdapterClickCallback(clickInterface: ClickInterface) {
        this.clickInterface = clickInterface
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BookmarkVH {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_bookmark, viewGroup, false)
        return BookmarkVH(view)
    }

    override fun onBindViewHolder(sampleVH: BookmarkVH, i: Int) {
        sampleVH.setViewHolder(bookmarkList[i], clickInterface)

        sampleVH.button.setOnClickListener { bookmarkList[i].bookmark = !bookmarkList[i].bookmark; notifyDataSetChanged() }
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    fun setBookmarkList(bookmarkList: List<BookmarkObject>) {
        this.bookmarkList = bookmarkList
    }

    inner class BookmarkVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var text: TextView
        lateinit var bookmarked: TextView
        lateinit var button: Button

        init {
            text = itemView.findViewById(R.id.text)
            bookmarked = itemView.findViewById(R.id.is_bookmarked)
            button = itemView.findViewById(R.id.button_bookmark)
        }

        fun setViewHolder(bookmarkObject: BookmarkObject, clickInterface: ClickInterface) {
            text.setText(bookmarkObject.text)

            if(bookmarkObject.bookmark == true)
                bookmarked.setText("This is bookmarked")
            else
                bookmarked.setText("This is not bookmarked")



            //Set callback here to pass person data to our activity
            itemView.setOnClickListener { clickInterface.onClick(bookmarkObject.id) }
        }
    }
}