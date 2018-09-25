package com.jim.multipos.core

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.jim.multipos.R
import kotlinx.android.synthetic.main.search_header_fragment.view.*


abstract class SinglePageFragment<T: ViewDataBinding, V: BaseViewModel>: SearchHeaderFragment<T, V>() {

    companion object {
        protected val SINGLE_PAGE_TAG = "SINGLE_PAGE_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (getFragment() == null) {
            throw Exception(message = "Fragment is not set")
        }

        if (activity
                        ?.supportFragmentManager
                        ?.findFragmentByTag(SINGLE_PAGE_TAG) == null) {
            activity
                    ?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.flContainer, getFragment(), SINGLE_PAGE_TAG)
                    ?.commit()
        }


    }


    protected abstract fun getFragment(): Fragment?

}