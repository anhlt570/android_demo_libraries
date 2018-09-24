package demo.com.demolibraries.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import java.util.ArrayList

import demo.com.demolibraries.R

class MenuAdapter(menuItems: List<MenuItem>?, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MainViewHolder>() {
    private var data: List<MenuItem>? = null

    init {
        data = menuItems ?: ArrayList()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MainViewHolder(v, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindView(data!![position])
        holder.showDivider(position < itemCount - 1)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}
