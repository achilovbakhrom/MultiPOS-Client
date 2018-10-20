package com.jim.multipos.customView.recyclerView.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import com.jim.multipos.R
import java.io.Serializable

class ProgressViewHolder<T: Serializable>(itemView: View): BaseViewHolder<T>(itemView) {

    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)

    override fun onBind(item: T?, position: Int, isSelected: Boolean) {
        progressBar?.indeterminateDrawable?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
    }

    @SuppressLint("InflateParams")
    override fun newInstance(context: Context): BaseViewHolder<T> {
        val view = LayoutInflater.from(context).inflate(R.layout.loading_progress_layout, null, false)
        return ProgressViewHolder(view)
    }

    companion object {
        val context: Context? = null
        fun <T: Serializable> instance(): ProgressViewHolder<T> {
            return ProgressViewHolder(View(context!!))
        }
    }

}