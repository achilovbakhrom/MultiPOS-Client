package com.jim.multipos.core.fragments;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.R.id;
import com.jcodecraeer.xrecyclerview.R.string;
import com.jcodecraeer.xrecyclerview.SimpleViewSwitcher;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.jim.multipos.R;

import java.util.Date;

public class CustomRefreshHeader extends ArrowRefreshHeader {
    private static final String XR_REFRESH_KEY = "XR_REFRESH_KEY";
    private static final String XR_REFRESH_TIME_KEY = "XR_REFRESH_TIME_KEY";
    private LinearLayout mContainer;
    private ImageView mArrowImageView;
    private SimpleViewSwitcher mProgressBar;
    private TextView mStatusTextView;
    private int mState = 0;
    private TextView mHeaderTimeView;
    private LinearLayout mHeaderRefreshTimeContainer;
    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;
    private static final int ROTATE_ANIM_DURATION = 180;
    public int mMeasuredHeight;
    private AVLoadingIndicatorView progressView;

    public void destroy() {
        this.mProgressBar = null;
        if (this.progressView != null) {
            this.progressView.destroy();
            this.progressView = null;
        }

        if (this.mRotateUpAnim != null) {
            this.mRotateUpAnim.cancel();
            this.mRotateUpAnim = null;
        }

        if (this.mRotateDownAnim != null) {
            this.mRotateDownAnim.cancel();
            this.mRotateDownAnim = null;
        }

    }

    public CustomRefreshHeader(Context context) {
        super(context);
        this.initView();
    }

    public CustomRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView();
    }

    public void setRefreshTimeVisible(boolean show) {
        if (this.mHeaderRefreshTimeContainer != null) {
            this.mHeaderRefreshTimeContainer.setVisibility(show ? 0 : 8);
        }

    }

    private void initView() {
        if (getChildCount() > 0 && getChildAt(0) != null) {
            this.mContainer = (LinearLayout) getChildAt(0);
            this.mHeaderRefreshTimeContainer = this.mContainer.findViewById(id.header_refresh_time_container);
            this.mArrowImageView = this.findViewById(id.listview_header_arrow);
            this.mArrowImageView.setColorFilter(0xFFFFFF);
            this.mStatusTextView = this.findViewById(id.refresh_status_textview);
            this.mStatusTextView.setTextColor(getContext().getColor(R.color.colorWhite));
            this.mProgressBar = this.findViewById(id.listview_header_progressbar);
            this.progressView = new AVLoadingIndicatorView(this.getContext());
            this.progressView.setIndicatorColor(0xFFFFFF);
            this.progressView.setIndicatorId(22);
            if (this.mProgressBar != null) {
                this.mProgressBar.setView(this.progressView);
            }

            this.mRotateUpAnim = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
            this.mRotateUpAnim.setDuration(180L);
            this.mRotateUpAnim.setFillAfter(true);
            this.mRotateDownAnim = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
            this.mRotateDownAnim.setDuration(180L);
            this.mRotateDownAnim.setFillAfter(true);
            this.mHeaderTimeView = (TextView)this.findViewById(id.last_refresh_time);
            this.measure(-2, -2);
            this.mMeasuredHeight = this.getMeasuredHeight();
        }
    }

    public void setProgressStyle(int style) {
        if (style == -1) {
            if (this.mProgressBar != null) {
                this.mProgressBar.setView(new ProgressBar(this.getContext(), (AttributeSet)null, 16842871));
            }
        } else {
            this.progressView = new AVLoadingIndicatorView(this.getContext());
            this.progressView.setIndicatorColor(0xFFFFFF);
            this.progressView.setIndicatorId(style);
            this.mProgressBar.setView(this.progressView);
        }

    }

    public void setArrowImageView(int resid) {
        this.mArrowImageView.setImageResource(resid);
    }

    public void setState(int state) {
        if (state != this.mState) {
            if (state == 2) {
                this.mArrowImageView.clearAnimation();
                this.mArrowImageView.setVisibility(4);
                if (this.mProgressBar != null) {
                    this.mProgressBar.setVisibility(0);
                }

                this.smoothScrollTo(this.mMeasuredHeight);
            } else if (state == 3) {
                this.mArrowImageView.setVisibility(4);
                if (this.mProgressBar != null) {
                    this.mProgressBar.setVisibility(4);
                }
            } else {
                this.mArrowImageView.setVisibility(0);
                if (this.mProgressBar != null) {
                    this.mProgressBar.setVisibility(4);
                }
            }

            this.mHeaderTimeView.setText(friendlyTime(this.getLastRefreshTime()));
            switch(state) {
                case 0:
                    if (this.mState == 1) {
                        this.mArrowImageView.startAnimation(this.mRotateDownAnim);
                    }

                    if (this.mState == 2) {
                        this.mArrowImageView.clearAnimation();
                    }
                    if (getReleaseText().isEmpty())
                        this.mStatusTextView.setText(string.listview_header_hint_release);
                    else
                        this.mStatusTextView.setText(getReleaseText());
                    break;
                case 1:
                    if (this.mState != 1) {
                        this.mArrowImageView.clearAnimation();
                        this.mArrowImageView.startAnimation(this.mRotateUpAnim);
                        if (getReleaseText().isEmpty())
                            this.mStatusTextView.setText(string.listview_header_hint_release);
                        else
                            this.mStatusTextView.setText(getReleaseText());

                    }
                    break;
                case 2:
                    if (getRefreshingText().isEmpty())
                        this.mStatusTextView.setText(string.refreshing);
                    else
                        this.mStatusTextView.setText(getRefreshingText());
                    break;
                case 3:
                    if (getRefreshDoneText().isEmpty())
                        this.mStatusTextView.setText(string.refresh_done);
                    else
                        this.mStatusTextView.setText(getRefreshDoneText());
            }

            this.mState = state;
        }
    }

    public String getRefreshingText() {
        return getContext().getString(R.string.refreshing);
    }

    public String getRefreshDoneText() {
        return getContext().getString(R.string.refresh_done);
    }

    public String getReleaseText() {
        return getContext().getString(R.string.release);
    }


    public int getState() {
        return this.mState;
    }

    private long getLastRefreshTime() {
        SharedPreferences s = this.getContext().getSharedPreferences("XR_REFRESH_KEY", 32768);
        return s.getLong("XR_REFRESH_TIME_KEY", (new Date()).getTime());
    }

    private void saveLastRefreshTime(long refreshTime) {
        SharedPreferences s = this.getContext().getSharedPreferences("XR_REFRESH_KEY", 32768);
        s.edit().putLong("XR_REFRESH_TIME_KEY", refreshTime).apply();
    }

    public void refreshComplete() {
        this.mHeaderTimeView.setText(friendlyTime(this.getLastRefreshTime()));
        this.saveLastRefreshTime(System.currentTimeMillis());
        this.setState(3);
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                CustomRefreshHeader.this.reset();
            }
        }, 200L);
    }

    public void setVisibleHeight(int height) {
        if (height < 0) {
            height = 0;
        }

        LayoutParams lp = (LayoutParams)this.mContainer.getLayoutParams();
        lp.height = height;
        this.mContainer.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        LayoutParams lp = (LayoutParams)this.mContainer.getLayoutParams();
        return lp.height;
    }

    public void onMove(float delta) {
        if (this.getVisibleHeight() > 0 || delta > 0.0F) {
            this.setVisibleHeight((int)delta + this.getVisibleHeight());
            if (this.mState <= 1) {
                if (this.getVisibleHeight() > this.mMeasuredHeight) {
                    this.setState(1);
                } else {
                    this.setState(0);
                }
            }
        }

    }

    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = this.getVisibleHeight();
        if (height == 0) {
            isOnRefresh = false;
        }

        if (this.getVisibleHeight() > this.mMeasuredHeight && this.mState < 2) {
            this.setState(2);
            isOnRefresh = true;
        }

        if (this.mState == 2 && height <= this.mMeasuredHeight) {
            ;
        }

        if (this.mState != 2) {
            this.smoothScrollTo(0);
        }

        if (this.mState == 2) {
            int destHeight = this.mMeasuredHeight;
            this.smoothScrollTo(destHeight);
        }

        return isOnRefresh;
    }

    public void reset() {
        this.smoothScrollTo(0);
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                CustomRefreshHeader.this.setState(0);
            }
        }, 500L);
    }

    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(new int[]{this.getVisibleHeight(), destHeight});
        animator.setDuration(300L).start();
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                CustomRefreshHeader.this.setVisibleHeight((Integer)animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    public static String friendlyTime(Date time) {
        return friendlyTime(time.getTime());
    }

    public static String friendlyTime(long time) {
        int ct = (int)((System.currentTimeMillis() - time) / 1000L);
        if (ct == 0) {
            return "刚刚";
        } else if (ct > 0 && ct < 60) {
            return ct + "秒前";
        } else if (ct >= 60 && ct < 3600) {
            return Math.max(ct / 60, 1) + "分钟前";
        } else if (ct >= 3600 && ct < 86400) {
            return ct / 3600 + "小时前";
        } else if (ct >= 86400 && ct < 2592000) {
            int day = ct / 86400;
            return day + "天前";
        } else {
            return ct >= 2592000 && ct < 31104000 ? ct / 2592000 + "月前" : ct / 31104000 + "年前";
        }
    }
}
