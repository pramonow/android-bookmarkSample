package com.pramonow.bookmarksample

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.ArrayList

class BookmarkObjectAdapter : RecyclerView.Adapter<BookmarkObjectAdapter.BookmarkVH>() {

    var bookmarkList: List<BookmarkObject> = ArrayList()
    private lateinit var clickInterface: ClickInterface

    fun setAdapterClickCallback(clickInterface: ClickInterface) {
        this.clickInterface = clickInterface
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BookmarkVH {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_bookmark, viewGroup, false)
        return BookmarkVH(view)
    }

    override fun onBindViewHolder(sampleVH: BookmarkVH, i: Int) {
        sampleVH.setViewHolder(bookmarkList[i], clickInterface)

        //Clicking the button will flag the object as bookmark/unbookmark
        sampleVH.button.setOnClickListener {
            bookmarkList[i].bookmark = !bookmarkList[i].bookmark
            notifyDataSetChanged() }
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    inner class BookmarkVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var text: TextView = itemView.findViewById(R.id.text)
        private var bookmarked: TextView = itemView.findViewById(R.id.is_bookmarked)
        var button: Button = itemView.findViewById(R.id.button_bookmark)

        @SuppressLint("SetTextI18n")
        fun setViewHolder(bookmarkObject: BookmarkObject, clickInterface: ClickInterface) {
            text.text = bookmarkObject.text

            // Changed the text based on bookmark/unbookmark status
            if(bookmarkObject.bookmark){
                button.text = "Unbookmark this"
                bookmarked.text = "This is bookmarked"
            }
            else {
                button.text = "Bookmark this"
                bookmarked.text = "This is not bookmarked"
            }

            //Set callback here to pass id data to our activity
            itemView.setOnClickListener { clickInterface.onClick(bookmarkObject.id) }
        }
    }
}