package com.jim.multipos.customView

import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.jim.multipos.R

class MPTabBar: LinearLayout {

    var items: MutableList<String> = mutableListOf()


    private var tabLayout: LinearLayout? = null
    private var frameLayout: FrameLayout? = null
    private var stripeLayout: FrameLayout? = null

    var currentFragment: Fragment? = null
        get() {
            val activityCompat = context as AppCompatActivity
            return activityCompat.supportFragmentManager.findFragmentByTag("TAB_FRAGMENT")
        }

    var selectedPosition: Int = 0 // Default selected position is 0
        set(value) {
            if (value != field) {

                findViewWithTag<Button>(field).background = ContextCompat.getDrawable(context, R.drawable.tab_unselected_bg)
                findViewWithTag<Button>(field).setTextColor(ContextCompat.getColor(context, R.color.colorTitle))
                findViewWithTag<Button>(field).translationZ = 0.0f
                findViewWithTag<Button>(field).elevation = 0.0f
                findViewWithTag<Button>(value).background = ContextCompat.getDrawable(context, R.drawable.tab_selected_bg)
                findViewWithTag<Button>(value).setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
                findViewWithTag<Button>(value).translationZ = context.resources.getDimension(R.dimen.six_dp)
                findViewWithTag<Button>(value).elevation = context.resources.getDimension(R.dimen.two_dp)
                val activityCompat = context as AppCompatActivity
                activityCompat
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.tab_frame_layout, fragmentProtocol?.getFragment(value), "TAB_FRAGMENT")
                        .commit()

                field = value
            }
        }

    var fragmentProtocol: FragmentForTab? = null
        set(value) {
            field = value
            openFragmentForPosition()
        }
    var selectionListener: FragmentSelectionListener? = null

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        orientation = VERTICAL
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.MPTabBar, defStyleAttr, 0)
        val array = a.getTextArray(R.styleable.MPTabBar_items)

        if (array != null) {
            items = array.map { it.toString() }.toMutableList()
        }
        removeAllViews()


        val tabLayoutContainer = FrameLayout(context)
        val tabLayoutContainerLayoutParams = FrameLayout.LayoutParams(MATCH_PARENT, context.resources.getDimension(R.dimen.seventy_dp).toInt())
        tabLayoutContainer.layoutParams = tabLayoutContainerLayoutParams
        tabLayoutContainer.background = ContextCompat.getDrawable(context, R.color.colorGray)
        addView(tabLayoutContainer)

        stripeLayout = FrameLayout(context)

        val stripeLayoutParams = FrameLayout.LayoutParams(MATCH_PARENT, context.resources.getDimension(R.dimen.fourty_dp).toInt())
        stripeLayoutParams.gravity = Gravity.BOTTOM

        stripeLayout?.layoutParams = stripeLayoutParams
        stripeLayout?.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLightGray))
        tabLayoutContainer.addView(stripeLayout)


        tabLayout = LinearLayout(context)
        val tabLayoutLayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        tabLayoutLayoutParams.weight = 1.0f
        tabLayout?.layoutParams = tabLayoutLayoutParams

        tabLayout?.orientation = HORIZONTAL
        tabLayoutContainer.addView(tabLayout)


        if (!items.isEmpty()) {
            for ((counter, item) in items.withIndex()) {
                val button = Button(context)
                val buttonLp = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                button.layoutParams = buttonLp
                buttonLp.weight = 1.0f
                buttonLp.bottomMargin = context.resources.getDimension(R.dimen.six_dp).toInt()
                button.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
                button.text = item
                button.stateListAnimator = null
                if (selectedPosition == counter) { // selected
                    button.background = ContextCompat.getDrawable(context, R.drawable.tab_selected_bg)
                    button.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
                    button.translationZ = context.resources.getDimension(R.dimen.six_dp)
                    button.elevation= context.resources.getDimension(R.dimen.two_dp)
                } else { // unselected
                    button.background = ContextCompat.getDrawable(context, R.drawable.tab_unselected_bg)
                    button.setTextColor(ContextCompat.getColor(context, R.color.colorTitle))
                    button.translationZ = 0.0f
                    button.elevation = 0.0f
                }
                button.tag = counter
                button.setOnClickListener {
                    selectedPosition = it.tag as Int
                    selectionListener?.fragmentSelected(selectedPosition, currentFragment)
                }
                tabLayout?.addView(button)
            }
        }

        selectedPosition = a.getInt(R.styleable.MPTabBar_selectedPosition, 0)

        frameLayout = FrameLayout(context)
        val frameLayoutLayoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        frameLayoutLayoutParams.weight = 1.0f
        frameLayout?.layoutParams = frameLayoutLayoutParams
        frameLayout?.id = R.id.tab_frame_layout
        addView(frameLayout)
    }

    fun setBottomStripeColor(colorId: Int) {
        stripeLayout?.setBackgroundColor(colorId)
    }

    private fun openFragmentForPosition() {
        val appCompatActivity = context as AppCompatActivity
        appCompatActivity
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.tab_frame_layout, fragmentProtocol?.getFragment(selectedPosition), "TAB_FRAGMENT")
                .commit()
    }

    interface FragmentForTab {
        fun getFragment(forPosition: Int) : Fragment
    }
    interface FragmentSelectionListener {
        fun fragmentSelected(position: Int, fragment: Fragment?)
    }
}