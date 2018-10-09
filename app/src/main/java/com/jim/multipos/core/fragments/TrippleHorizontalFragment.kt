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

abstract class TrippleHorizontalFragment<T: ViewDataBinding, V: BaseViewModel>: SearchHeaderFragment<T, V>() {




    companion object {
        protected val TRIPLE_LEFT_FRAGMENT_TAG = "TRIPLE_LEFT_FRAGMENT_TAG"
        protected val TRIPLE_CENTER_FRAGMENT_TAG = "TRIPLE_CENTER_FRAGMENT_TAG"
        protected val TRIPLE_RIGHT_FRAGMENT_TAG = "TRIPLE_RIGHT_FRAGMENT_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (getLeftFragment() == null) {
            throw Exception("LEFT Fragment is not set")
        }
        if (getRightFragment() == null) {
            throw Exception("RIGHT Fragment is not set")
        }


        if (activity?.supportFragmentManager?.findFragmentByTag(TRIPLE_LEFT_FRAGMENT_TAG) != null) {
            removeFragment(TRIPLE_LEFT_FRAGMENT_TAG)

        }

        if (activity?.supportFragmentManager?.findFragmentByTag(TRIPLE_CENTER_FRAGMENT_TAG) != null) {
            removeFragment(TRIPLE_CENTER_FRAGMENT_TAG)
        }

        if (activity?.supportFragmentManager?.findFragmentByTag(TRIPLE_RIGHT_FRAGMENT_TAG) != null) {
            removeFragment(TRIPLE_RIGHT_FRAGMENT_TAG)
        }

        initFragmentContent()
        addFragment(getLeftFragment(), TRIPLE_LEFT_FRAGMENT_TAG, R.id.flLeftContainer)
        addFragment(getCenterFragment(), TRIPLE_CENTER_FRAGMENT_TAG, R.id.flCenterContainer)
        addFragment(getRightFragment(), TRIPLE_RIGHT_FRAGMENT_TAG, R.id.flRightContainer)

    }


    abstract fun getLeftFragment() : Fragment?
    abstract fun getCenterFragment() : Fragment?
    abstract fun getRightFragment() : Fragment?

    override fun getLayoutId(): Int = R.layout.search_header_fragment

    private fun initFragmentContent() {
        LayoutInflater.from(context).inflate(R.layout.tripple_horizontal_fragment, flContainer, true)
    }
}