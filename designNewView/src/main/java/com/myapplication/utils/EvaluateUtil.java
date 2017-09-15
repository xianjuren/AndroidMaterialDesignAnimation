package com.myapplication.utils;

import android.graphics.Rect;

/**
 * Created by Jone on 17/2/3.
 */

public class EvaluateUtil {
    public EvaluateUtil() {
    }

    public static Integer evaluateInt(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue.intValue();
        return Integer.valueOf((int) ((float) startInt + fraction * (float) (endValue.intValue() - startInt)));
    }

    public static Float evaluateFloat(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return Float.valueOf(startFloat + fraction * (endValue.floatValue() - startFloat));
    }

    public static Object evaluateArgb(float fraction, Object startValue, Object endValue) {
        int startInt = ((Integer) startValue).intValue();
        int startA = startInt >> 24 & 255;
        int startR = startInt >> 16 & 255;
        int startG = startInt >> 8 & 255;
        int startB = startInt & 255;
        int endInt = ((Integer) endValue).intValue();
        int endA = endInt >> 24 & 255;
        int endR = endInt >> 16 & 255;
        int endG = endInt >> 8 & 255;
        int endB = endInt & 255;
        return Integer.valueOf(startA + (int) (fraction * (float) (endA - startA)) << 24 | startR + (int) (fraction * (float) (endR - startR)) << 16 | startG + (int) (fraction * (float) (endG - startG)) << 8 | startB + (int) (fraction * (float) (endB - startB)));
    }

    public static Rect evaluateRect(float fraction, Rect startValue, Rect endValue) {
        return new Rect(startValue.left + (int) ((float) (endValue.left - startValue.left) * fraction), startValue.top + (int) ((float) (endValue.top - startValue.top) * fraction), startValue.right + (int) ((float) (endValue.right - startValue.right) * fraction), startValue.bottom + (int) ((float) (endValue.bottom - startValue.bottom) * fraction));
    }
}
