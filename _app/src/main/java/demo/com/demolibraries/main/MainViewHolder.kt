package demo.com.demolibraries.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import demo.com.demolibraries.R

class MainViewHolder(itemView: View, private val onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
    private var item: MenuItem? = null

    private val imgIcon: ImageView = itemView.findViewById(R.id.img_icon)
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvDescription: TextView = itemView.findViewById(R.id.tv_description)

    init {
        itemView.setOnClickListener { onItemClickListener.onItemClick(item!!) }
    }

    fun bindView(item: MenuItem) {
        this.item = item
        if (item.getIconId() >= 0) {
            imgIcon.setImageResource(item.getIconId())
        }
        tvTitle.text = item.getTitle()
        tvDescription.text = item.getDescription()
    }

    fun showDivider(isShow: Boolean) {
        if (isShow) {
            itemView.findViewById<View>(R.id.divider).visibility = View.VISIBLE
        } else {
            itemView.findViewById<View>(R.id.divider).visibility = View.INVISIBLE
        }
    }
}
