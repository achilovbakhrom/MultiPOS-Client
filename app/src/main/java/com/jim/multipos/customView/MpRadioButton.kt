package com.jim.multipos.customView

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.google.android.flexbox.*
import com.jim.multipos.R
import kotlinx.android.synthetic.main.mp_radio_button.view.*

class MpRadioButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FlexboxLayout(context, attrs, defStyleAttr) {


    init {
        flexWrap = FlexWrap.WRAP
        alignItems = AlignItems.FLEX_START
        alignContent = AlignContent.SPACE_AROUND

    }

    var position = 0
    var textList = mutableListOf<String>()

    fun setList(value: List<String>){
        removeAllViews()
        textList = value as MutableList<String>
        for (i in value.indices){
            val row= MpRadioButtonRow(context)
            if(position == i)
                row.changeBackgrounColorToNavy(true)
            row.listener = object : MpRadioButtonRow.MpRadioButtonListener{
                override fun onTextSelected() {
                    if(position != i) {
                        row.changeBackgrounColorToNavy(true)
                        (getChildAt(position) as MpRadioButtonRow).changeBackgrounColorToNavy(false)
                        position = i
                    }
                }
            }
            row.setText(value[i])
            row.tag = i
            addView(row)
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val ss = SavedState(superState)
        ss.position = position
        ss.list = textList
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val ss = state as SavedState
        super.onRestoreInstanceState(ss.superState)
        position = ss.position
        setList(ss.list)
    }

    internal class SavedState : View.BaseSavedState {
        var position: Int = 0
        var list = mutableListOf<String>()

        constructor(superState: Parcelable) : super(superState) {}

        private constructor(`in`: Parcel) : super(`in`) {
            position = `in`.readInt()
            `in`.readStringList(list)
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(position)
            out.writeStringList(list)
        }

        companion object {

            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
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
            rbCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorNavy))
            rbText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        }else {
            rbCardView.setCardBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            rbText.setTextColor(ContextCompat.getColor(context, R.color.colorNavy))
        }
    }

    interface MpRadioButtonListener{
        fun onTextSelected()
    }
}