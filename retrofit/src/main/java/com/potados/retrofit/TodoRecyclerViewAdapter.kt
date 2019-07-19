package com.potados.retrofit

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoRecyclerViewAdapter(
    private val source: List<Todo>
) : RecyclerView.Adapter<TodoRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = source.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            titleTextView.text = source[position].title
            userTextView.text = source[position].userName
            doneCheckBox.isChecked = source[position].done

        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.title_textview
        val userTextView: TextView = view.user_textview
        val doneCheckBox: CheckBox = view.completed_checkbox
    }
}