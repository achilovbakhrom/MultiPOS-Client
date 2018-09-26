package com.jim.multipos.core

import android.databinding.ViewDataBinding
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.LinearLayout
import com.jim.multipos.R
import kotlinx.android.synthetic.main.tabbed_fragment.*

abstract class TabbedFragment<T: ViewDataBinding, V: BaseViewModel>: BaseFragment<T, V>() {


    companion object {
        val TAB_FRAGMENT_TAG = "TAB_FRAGMENT_TAG"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (tlContainer.tabCount == 0) {

            val data = getTabData()
            if (data.isNotEmpty()) {

                for (key in data.keys) {
                    tlContainer.addTab(tlContainer.newTab().setText(key))
                }
                val tab = tlContainer.getChildAt(0)
                (tab as LinearLayout).showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
                val drawable = GradientDrawable()
                drawable.setColor(ContextCompat.getColor(context!!, R.color.colorGray))
                drawable.setSize(1, 1)
                tab.dividerDrawable = drawable
                tlContainer.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (tab?.text != null && data.get(tab.text) != null) {
                            openFragment(data.get(tab.text)!!)
                        }
                    }

                })
                val defaultTabName = getDefaultTabName()
                if (defaultTabName != null) {
                    if (data[defaultTabName] != null) {
                        openFragment(data[defaultTabName]!!)
                    }
                } else {
                    if (data[data.keys.toList()[0]] != null) {
                        openFragment(data[data.keys.toList()[0]]!!)
                    }
                }
            } else {
                throw Exception("Tab data is not set!!!")
            }
        }

    }


    private fun openFragment(fragment: Fragment) {
        activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.flTabContainer, fragment, TAB_FRAGMENT_TAG)
                ?.commitNow()
    }


    override fun getLayoutId(): Int {
        return R.layout.tabbed_fragment
    }

    abstract fun getTabData(): Map<String, Fragment>

    fun getDefaultTabName(): String? = null

}