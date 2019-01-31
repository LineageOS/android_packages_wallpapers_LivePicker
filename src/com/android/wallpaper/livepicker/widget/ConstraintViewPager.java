/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.wallpaper.livepicker.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * When ConstraintViewPager is being measured, it will calculate height of the currently selected
 * page and makes itself be the same height exactly.
 */
public class ConstraintViewPager extends ViewPager {

    public ConstraintViewPager(@NonNull Context context) {
        this(context, null /* attrs */);
    }

    public ConstraintViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Calculates the measured height of the selected page and makes itself be the same height.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final View pageView = getChildAt(getCurrentItem());
        if (pageView != null) {
            pageView.measure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0 /* size */, MeasureSpec.UNSPECIFIED));
            if (pageView.getMeasuredHeight() != 0) {
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(pageView.getMeasuredHeight(),
                        MeasureSpec.EXACTLY);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
