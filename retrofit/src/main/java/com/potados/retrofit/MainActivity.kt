package com.potados.retrofit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todo_recyclerview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager(this).orientation))
        todo_recyclerview.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.todos.observe(this, object: Observer<List<Todo>> {
            override fun onChanged(t: List<Todo>?) {
                if (t == null) return
                
                // Toast.makeText(this@MainActivity, "Refresh success.", Toast.LENGTH_SHORT).show()

                todo_recyclerview.adapter = TodoRecyclerViewAdapter(t)
            }
        })

        viewModel.failure.observe(this, object: Observer<String> {
            override fun onChanged(t: String?) {
                Toast.makeText(this@MainActivity, t, Toast.LENGTH_SHORT).show()
            }
        })

        refresh_button.setOnClickListener {
            viewModel.loadTodos()
        }

        viewModel.loadTodos()
    }
}
