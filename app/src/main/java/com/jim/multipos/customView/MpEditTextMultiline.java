package com.jim.multipos.customView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.jim.multipos.R;

public class MpEditTextMultiline extends AppCompatEditText {

    public MpEditTextMultiline(Context context) {
        super(context);
        init(context);
    }

    public MpEditTextMultiline(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpEditTextMultiline(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.edit_text_bg);
        int topPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        int sidePadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        setPadding(sidePadding, topPadding, sidePadding, topPadding);
        setTextColor(ContextCompat.getColor(context, R.color.mp_edittext_color));
        setHintTextColor(ContextCompat.getColor(context, R.color.colorGray));
    }


}