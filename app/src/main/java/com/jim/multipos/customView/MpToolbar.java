package com.jim.multipos.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jim.multipos.R;

public class MpToolbar extends RelativeLayout {

    int mode = 1;
    int lastID = R.id.layoutCompany;
    public static final int SIGN_IN = 1;
    public static final int ADMIN = 2;

    enum ToolbarItems{
        COMPANY, DASHBOARD, ESTABLISHMENT,
        ENTITIES, MANAGEMENT, WAREHOUSE,
        CRM, HRM, REPORTS, CASH
    }

    ToolbarItems toolbarItems;

    OnBackButtonClick onBackButtonClick;
    OnToolbarItemSelected itemSelected;

    public MpToolbar(Context context) {
        super(context);
        init(context, null);
    }

    public MpToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.mp_toolbar, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MpToolbar);
        if(array!=null){
            mode = array.getInt(R.styleable.MpToolbar_mode, 1);
            array.recycle();
            setMode(mode);
        }

    }

    public void setMode(int mode) {

        switch (mode){
            case SIGN_IN:
                findViewById(R.id.ltSignIn).setVisibility(VISIBLE);
                findViewById(R.id.ltAdmin).setVisibility(GONE);
                findViewById(R.id.ivBack).setOnClickListener(v ->{
                    if(onBackButtonClick!=null) onBackButtonClick.onBack();
                });
                break;
            case ADMIN:
                findViewById(R.id.ltSignIn).setVisibility(GONE);
                findViewById(R.id.ltAdmin).setVisibility(VISIBLE);
                toolbarItems = ToolbarItems.COMPANY;
                setUpListeners();
                break;
        }
    }

    public interface OnBackButtonClick{
        void onBack();
    }

    public void setOnBackButtonClick(OnBackButtonClick onBackButtonClick) {
        this.onBackButtonClick = onBackButtonClick;
    }

    public interface OnToolbarItemSelected{
        void onCompany();
        void onDashboard();
        void onEstablishment();
        void onEntities();
        void onManagements();
        void onInventory();
        void onCRM();
        void onHRM();
        void onReports();
    }

    public void setItemSelected(OnToolbarItemSelected itemSelected) {
        this.itemSelected = itemSelected;
    }

    void setUpListeners(){
        findViewById(R.id.layoutCompany).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.COMPANY) {
                toolbarItems = ToolbarItems.COMPANY;
                itemSelected.onCompany();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutCompany).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutCompany;
            }
        });
        findViewById(R.id.layoutDashboard).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.DASHBOARD) {
                toolbarItems = ToolbarItems.DASHBOARD;
                itemSelected.onDashboard();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutDashboard).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutDashboard;
            }
        });
        findViewById(R.id.layoutEstablishment).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.ESTABLISHMENT) {
                toolbarItems = ToolbarItems.ESTABLISHMENT;
                itemSelected.onEstablishment();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutEstablishment).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutEstablishment;
            }
        });
        findViewById(R.id.layoutEntities).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.ENTITIES) {
                toolbarItems = ToolbarItems.ENTITIES;
                itemSelected.onEntities();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutEntities).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutEntities;
            }
        });
        findViewById(R.id.layoutManagements).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.MANAGEMENT) {
                toolbarItems = ToolbarItems.MANAGEMENT;
                itemSelected.onManagements();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutManagements).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutManagements;
            }
        });
        findViewById(R.id.layoutInventory).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.WAREHOUSE) {
                toolbarItems = ToolbarItems.WAREHOUSE;
                itemSelected.onInventory();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutInventory).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutInventory;
            }
        });
        findViewById(R.id.layoutCRM).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.CRM){
                toolbarItems = ToolbarItems.CRM;
                itemSelected.onCRM();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutCRM).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutCRM;
            }
        });
        findViewById(R.id.layoutHRM).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.HRM) {
                toolbarItems = ToolbarItems.HRM;
                itemSelected.onHRM();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutHRM).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutHRM;
            }
        });
        findViewById(R.id.layoutReports).setOnClickListener(v -> {
            if(toolbarItems!=ToolbarItems.REPORTS){
                toolbarItems = ToolbarItems.REPORTS;
                itemSelected.onReports();
                findViewById(lastID).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorNavy));
                findViewById(R.id.layoutReports).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkBlue));
                lastID = R.id.layoutReports;
            }
        });
    }
}
