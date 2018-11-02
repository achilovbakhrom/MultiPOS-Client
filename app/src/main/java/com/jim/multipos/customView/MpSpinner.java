package com.jim.multipos.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jim.multipos.R;
import com.jim.multipos.customView.adapter.SpinnerAdapter;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.Gravity.END;
import static com.jim.multipos.utils.UtilsKt.convertDpToPx;

public class MpSpinner extends FrameLayout {

    String[] items;
    OnMpSpinnerItemSelected itemSelected;

    public MpSpinner(Context context) {
        super(context);
        init(context);
    }

    public MpSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context){
        setBackgroundResource(R.drawable.edit_text_bg);
        AppCompatSpinner spinner = new AppCompatSpinner(context);
        spinner.setId(R.id.spinner);
        LayoutParams spinnerLP = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        spinnerLP.topMargin = (int) convertDpToPx(context, 2);
        spinnerLP.bottomMargin = (int) convertDpToPx(context, 2);
        spinnerLP.leftMargin = (int) convertDpToPx(context, 4);
        spinnerLP.rightMargin = (int) convertDpToPx(context, 4);

        spinnerLP.gravity = CENTER_VERTICAL;
        spinner.setLayoutParams(spinnerLP);
        spinner.setBackground(null);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                if (itemSelected != null)
                    itemSelected.onItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addView(spinner);

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.triangle);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.rightMargin = (int) convertDpToPx(context, 10);
        lp.gravity = END | CENTER_VERTICAL;
        imageView.setLayoutParams(lp);
        addView(imageView);

    }

    public void setItems(String[] items){
        this.items = items;
        ((AppCompatSpinner) findViewById(R.id.spinner)).setAdapter(new SpinnerAdapter(getContext(), items));
    }

    public void setItemSelected(OnMpSpinnerItemSelected itemSelected) {
        this.itemSelected = itemSelected;
    }

    public void selectItem(String item) {
        Spinner spinner = findViewById(R.id.spinner);
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                spinner.setSelection(i);
                if (itemSelected != null) {
                    itemSelected.onItemSelected(i);
                }
                break;
            }
        }
    }

    public interface OnMpSpinnerItemSelected{
        void onItemSelected(int position);
    }
}
