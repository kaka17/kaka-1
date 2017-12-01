package com.kaiser.kkshares.utils;

import android.content.Context;


public class Utils {
    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static String getDan(double money) {
        if (money > 99999999) {
            return DoubleUtil.getDoubleDecimal2(money / 99999999) + "/亿";
        } else if (money > 9999) {
            return DoubleUtil.getDoubleDecimal2(money / 9999 )+ "/万";
        } else {
            return (int)money + "/万";
        }
    }

    public static String getVolUnit(float num) {

        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return "亿手";
        } else if (e >= 4) {
            return "万手";
        } else {
            return "手";
        }


    }

}
