package com.potados.fragment.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.potados.fragment.R
import com.potados.fragment.fragment.OneFragment
import com.potados.fragment.fragment.TwoFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.security.InvalidParameterException

class MainActivity : AppCompatActivity() {

    companion object {
        const val FRAG_ONE = 1
        const val FRAG_TWO = 2
    }

    private var currentFragment = FRAG_ONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle_button.setOnClickListener {
            when (currentFragment) {
                FRAG_ONE -> changeFragment(FRAG_TWO)
                FRAG_TWO -> changeFragment(FRAG_ONE)
            }
        }

        changeFragment(FRAG_ONE)
    }

    private fun changeFragment(fragmentNum: Int) {
        val fragment: Fragment = when (fragmentNum) {
            /**
             * getInstance(): 객체 생성은 해당 인자에 대해 한번만.
             */
            FRAG_ONE    -> OneFragment.getInstance("Hello", "World")
            FRAG_TWO    -> TwoFragment.getInstance()

            else        -> throw InvalidParameterException("fragmentNum can be only 1 or 2.")
    }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        currentFragment = fragmentNum
    }

}
