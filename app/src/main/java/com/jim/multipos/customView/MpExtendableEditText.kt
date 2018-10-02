package com.jim.multipos.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.jim.multipos.R

class MpExtendableEditText @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.mp_extendable_edittext, this, true)
        val ivPlus = findViewById<ImageView>(R.id.ivPlus)
        ivPlus.setOnClickListener {  }
    }
}