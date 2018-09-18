package com.jim.multipos.customView

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.jim.multipos.R
import kotlinx.android.synthetic.main.mp_checkbox.view.*


class MpCheckbox @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
        ) : LinearLayout(context, attrs, defStyleAttr) {

    var text: String?=null
    var checked: Boolean?=null
    var listener: CheckedChangeListener?=null

    init {
        LayoutInflater.from(context).inflate(R.layout.mp_checkbox, this)
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MpCheckbox)
        text = ta.getString(R.styleable.MpCheckbox_text)
        checked = ta.getBoolean(R.styleable.MpCheckbox_checked, false)
        ta.recycle()

        setChecked(checked!!)
        tvCheckbox.text = text

        ivCheckbox.setOnClickListener {
            checked = !checked!!
            setChecked(checked!!)
        }

    }

    private fun changeTextColor(color: Int){
        tvCheckbox.setTextColor(ContextCompat.getColor(context, color))
    }

    fun setChecked(checked: Boolean){

        if (ivCheckbox != null) {
            val imageId = if (checked) R.drawable.checked_blue else R.drawable.unchecked
            ivCheckbox.setImageResource(imageId)
            if(checked) changeTextColor(android.R.color.tertiary_text_light) else changeTextColor(R.color.colorGray)
            if(listener!=null)
                listener!!.onCheckedChange(checked)
        }

    }

    fun setCheckListener(listener: CheckedChangeListener){
        this.listener = listener
    }

    fun setCustomText(text: String){
        tvCheckbox.text = text
    }

    fun isChecked(): Boolean{
        return checked!!
    }

    interface CheckedChangeListener {
        fun onCheckedChange(isChecked: Boolean)
    }

}