package com.jim.multipos.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.jim.multipos.R
import kotlinx.android.synthetic.main.mp_radio_button.view.*

class MpRadioButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
    }

    var position = -1
    var textList = mutableListOf<String>()
    set(value) {
        for (i in value.indices){
            val row= MpRadioButtonRow(context)
            row.listener = object : MpRadioButtonRow.MpRadioButtonListener{
                override fun onTextSelected() {
                    position = i
                }
            }
            row.setText(value[i])
            row.tag = i
            addView(row)
        }
    }
}

class MpRadioButtonRow @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var listener : MpRadioButtonListener?=null

    init {
        LayoutInflater.from(context).inflate(R.layout.mp_radio_button, this, true)
        rbText.setOnClickListener {
            listener?.onTextSelected()
        }
    }

    fun setText(text: String){
        rbText.text = text
    }

    fun changeBackgrounColorToNavy(navy: Boolean){
        if(navy){
            rbCardView.setCardBackgroundColor(resources.getColor(R.color.colorNavy))
        }
    }

    interface MpRadioButtonListener{
        fun onTextSelected()
    }
}