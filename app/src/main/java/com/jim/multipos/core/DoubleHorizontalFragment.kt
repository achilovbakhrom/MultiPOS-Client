package com.jim.multipos.core

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.jim.multipos.R
import kotlinx.android.synthetic.main.search_header_fragment.*

abstract class DoubleHorizontalFragment<T: ViewDataBinding, V: BaseViewModel>: SearchHeaderFragment<T, V>() {


    companion object {
        protected val LEFT_FRAGMENT_TAG = "LEFT_FRAGMENT_TAG"
        protected val RIGHT_FRAGMENT_TAG = "RIGHT_FRAGMENT_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (getLeftFragment() == null) {
            throw Exception("LEFT Fragment is not set")
        }
        if (getRightFragment() == null) {
            throw Exception("RIGHT Fragment is not set")
        }

        if (activity?.supportFragmentManager?.findFragmentByTag(LEFT_FRAGMENT_TAG) == null &&
                activity?.supportFragmentManager?.findFragmentByTag(RIGHT_FRAGMENT_TAG) == null) {
            initFragmentContent()

            activity
                    ?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.flLeftConainer, getLeftFragment(), LEFT_FRAGMENT_TAG)
                    ?.commit()

            activity
                    ?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.flRightConainer, getRightFragment(), RIGHT_FRAGMENT_TAG)
                    ?.commit()
        }
    }

    abstract fun getLeftFragment() : Fragment?
    abstract fun getRightFragment() : Fragment?

    private fun initFragmentContent() {
        LayoutInflater.from(context).inflate(R.layout.double_horizontal_fragment, flContainer, true)
    }

    override fun isCustomTopBar(): Boolean = true

}