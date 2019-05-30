package com.potados.fragment.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.potados.fragment.R
import kotlinx.android.synthetic.main.fragment_one.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TITLE = "arg_title"
private const val ARG_TEXT = "arg_text"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OneFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var title: String? = null
    private var text: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            text = it.getString(ARG_TEXT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false).apply {
            plain_textview.text = String.format("$title\n$text")
        }
    }

    companion object {

        private var instance: OneFragment? = null
        private var lastTitle: String? = null
        private var lastText: String? = null

        @JvmStatic
        fun getInstance(title: String, text: String) =
            instance ?: newInstance(title, text)

        @JvmStatic
        private fun newInstance(title: String, text: String): OneFragment {
            lastTitle = title
            lastText = text
            instance = OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_TEXT, text)
                }
            }

            return instance!!
        }
    }
}
