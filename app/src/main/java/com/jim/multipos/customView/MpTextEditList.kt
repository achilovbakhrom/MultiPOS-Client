package com.jim.multipos.customView

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.jim.multipos.R
import kotlinx.android.synthetic.main.mp_extendable_edittext.view.*
import android.util.SparseArray




class MpTextEditList @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var data = mutableMapOf<Int, String>()
    var rowCount = 0

    init {
        this.orientation = LinearLayout.VERTICAL

        val linearLayout = LinearLayout(context)
        linearLayout.tag = -1
        linearLayout.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        LayoutInflater.from(context).inflate(R.layout.mp_extendable_edittext, linearLayout, true)
        this.addView(linearLayout)
        ivButton.setImageDrawable(null)
        ivButton.setBackgroundResource(R.drawable.plus)
        ivButton.setOnClickListener {

            rowCount++

            val row = MpTextEditListRow(context)
            row.tag = rowCount

            row.mpEditText.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val seq = row.tag as Int
                    data[seq] = p0.toString()
                }
            })

            row.listener = object :MpTextEditListRowListener {
                override fun minusClicked() {
                    val seq = row.tag as Int
                    data.remove(seq)
                    removeView(findViewWithTag(row.tag))
                }
            }
            this.addView(row)
            data[rowCount] = ""
        }

    }

    fun getValues(): List<String> = data.values.toMutableList()


    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val ss = SavedState(superState)
        ss.rowCount = rowCount
        ss.map = data
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val ss = state as SavedState
        super.onRestoreInstanceState(ss.superState)
        data = ss.map
        rowCount = ss.rowCount
    }


    internal class SavedState : View.BaseSavedState {
        var rowCount: Int = 0
        var map = mutableMapOf<Int, String>()

        constructor(superState: Parcelable) : super(superState) {}

        private constructor(`in`: Parcel, classLoader: ClassLoader) : super(`in`) {
            rowCount = `in`.readInt()
            `in`.readMap(map, classLoader)
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeMap(map)
            out.writeInt(rowCount)
        }

        companion object {

            val CREATOR: Parcelable.ClassLoaderCreator<SavedState> = object : Parcelable.ClassLoaderCreator<SavedState> {
                override fun createFromParcel(source: Parcel, loader: ClassLoader?): SavedState {
                    return SavedState(source, loader!!)
                }

                override fun createFromParcel(source: Parcel): SavedState {
                    return createFromParcel(source, null)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

}

class MpTextEditListRow @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : LinearLayout(context, attrs, defStyleAttr) {

    lateinit var listener: MpTextEditListRowListener

    init {
        LayoutInflater.from(context).inflate(R.layout.mp_extendable_edittext, this, true)
        ivButton.setOnClickListener{
            listener.minusClicked()
        }
    }

}

interface MpTextEditListRowListener {
    fun minusClicked()
}



