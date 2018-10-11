package com.jim.multipos.core.fragments

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.jim.multipos.R
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SearchHeaderFragment
import kotlinx.android.synthetic.main.search_header_fragment.*

abstract class DoubleHorizontalFragment<T: ViewDataBinding, V: BaseViewModel>: SearchHeaderFragment<T, V>() {


    companion object {
        val LEFT_FRAGMENT_TAG = "DOUBLE_LEFT_FRAGMENT_TAG"
        val RIGHT_FRAGMENT_TAG = "DOUBLE_RIGHT_FRAGMENT_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (getLeftFragment() == null) {
            throw Exception("LEFT Fragment is not set")
        }
        if (getRightFragment() == null) {
            throw Exception("RIGHT Fragment is not set")
        }

        if (activity?.supportFragmentManager?.findFragmentByTag(LEFT_FRAGMENT_TAG) != null) {
            removeFragment(LEFT_FRAGMENT_TAG)

        }

        if (activity?.supportFragmentManager?.findFragmentByTag(RIGHT_FRAGMENT_TAG) != null) {
            removeFragment(RIGHT_FRAGMENT_TAG)
        }

        initFragmentContent()
        addFragment(getLeftFragment(), LEFT_FRAGMENT_TAG, R.id.flLeftConainer)
        addFragment(getRightFragment(), RIGHT_FRAGMENT_TAG, R.id.flRightConainer)
    }

    abstract fun getLeftFragment() : Fragment?
    abstract fun getRightFragment() : Fragment?

    private fun initFragmentContent() {
        LayoutInflater.from(context).inflate(R.layout.double_horizontal_fragment, flContainer, true)
    }

    override fun getLayoutId(): Int = R.layout.search_header_fragment


}