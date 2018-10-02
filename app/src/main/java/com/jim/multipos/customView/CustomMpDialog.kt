package com.jim.multipos.customView

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jim.multipos.R
import com.jim.multipos.customView.adapter.CustomDialogAdapter

class CustomMpDialog @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var listener: DialogListener?=null
    var list: List<String>?=null
    var isVisible: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_mp_dialog, this, true)
        val ivDialog = findViewById<ImageView>(R.id.ivMpCustomDialog)
        ivDialog.setOnClickListener {
            listener?.onClick()
        }

    }

    fun showDialog(layout: FrameLayout, title: String, list: List<String>){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.mp_custom_dialog_layout, layout, true)
        dialogView.findViewById<ImageView>(R.id.ivDialogBack).setOnClickListener {
            listener?.onBack()
        }
        dialogView.findViewById<TextView>(R.id.tvTitle).text = title

        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.rvSpinner)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CustomDialogAdapter(list, context)
    }

    fun dismiss(layout: FrameLayout) {
        isVisible = false
        layout.removeView(layout.findViewById(R.id.dialogRoot))
    }

    interface DialogListener{
        fun onClick()
        fun onBack()
    }
}