/*
 * Copyright (C) 2016 huanghaibin_dev <huanghaibin_dev@163.com>
 * WebSite https://github.com/MiracleTimes-Dev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xugongming38.timemanager.view.calendarview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xugongming38.timemanager.R;

/**
 * 星期栏，如果你要使用星期栏自定义，切记XML使用 merge，不要使用LinearLayout
 * Created by huanghaibin on 2017/11/30.
 */
public class WeekBar extends LinearLayout {
    private CustomCalendarViewDelegate mDelegate;

    public WeekBar(Context context) {
        super(context);
        if ("com.haibin.calendarview.WeekBar".equals(getClass().getName())) {
            LayoutInflater.from(context).inflate(R.layout.cv_week_bar, this, true);
        }
    }

    /**
     * 传递属性
     *
     * @param delegate delegate
     */
    void setup(CustomCalendarViewDelegate delegate) {
        this.mDelegate = delegate;
        if ("com.haibin.calendarview.WeekBar".equalsIgnoreCase(getClass().getName())) {
            setTextColor(delegate.getWeekTextColor());
            setBackgroundColor(delegate.getWeekBackground());
        }
    }

    /**
     * 设置文本颜色，
     * 如果这里报错了，请确定你自定义XML文件跟布局是不是使用merge，而不是LinearLayout
     *
     * @param color color
     */
    void setTextColor(int color) {
        for (int i = 0; i < getChildCount(); i++) {
            ((TextView) getChildAt(i)).setTextColor(color);
        }
    }


    /**
     * 日期选择事件，这里提供这个回调，可以方便定制WeekBar需要
     *
     * @param calendar calendar 选择的日期
     * @param isClick  isClick 点击
     */
    @SuppressWarnings("unused")
    protected void onDateSelected(Calendar calendar, boolean isClick) {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mDelegate != null) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mDelegate.getWeekBarHeight(), MeasureSpec.EXACTLY);
        } else {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Util.dipToPx(getContext(), 40), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
