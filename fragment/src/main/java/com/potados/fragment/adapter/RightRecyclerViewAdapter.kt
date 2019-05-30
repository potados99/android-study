package com.potados.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.potados.fragment.R
import kotlinx.android.synthetic.main.right_recyclerview_item.view.*


class RightRecyclerViewAdapter(private val count: Int) : RecyclerView.Adapter<RightRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewGroup: Int): ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.right_recyclerview_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contentView.text = String.format("Recycler index $position")
    }

    override fun getItemCount() = count

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentView: TextView = itemView.content_textview
    }

}