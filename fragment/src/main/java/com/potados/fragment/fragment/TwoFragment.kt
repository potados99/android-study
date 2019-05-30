package com.potados.fragment.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.potados.fragment.R
import com.potados.fragment.adapter.RightRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_two.view.*

class TwoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false).apply {

            /**
             * 왼쪽 리스트뷰
             */
            left_listview.adapter = ArrayAdapter(
                activity,
                android.R.layout.simple_list_item_1,
                (0..25).map { "ListView index $it" }
            )

            /**
             * 오른쪽 리사이클러뷰
             */
            right_recyclerview.layoutManager = LinearLayoutManager(activity)
            right_recyclerview.adapter = RightRecyclerViewAdapter(25)
        }
    }

    companion object {
        private var instance: TwoFragment? = null

        @JvmStatic
        fun getInstance() =
            instance ?: newInstance()

        @JvmStatic
        private fun newInstance(): TwoFragment {
            instance = TwoFragment()

            return instance!!
        }
    }
}
