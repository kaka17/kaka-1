package com.kaiser.kkshares.gupian.utils;


import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.kaiser.kkshares.utils.Utils;

import java.text.DecimalFormat;

public class VolFormatter implements YAxisValueFormatter {

    private final int unit;
    private DecimalFormat mFormat;
    private String u;
    public VolFormatter(int unit) {
        if (unit == 1) {
            mFormat = new DecimalFormat("#0");
        } else {
            mFormat = new DecimalFormat("#0.00");
        }
        this.unit = unit;
        this.u= Utils.getVolUnit(unit);
    }


    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        value = value / unit;
        if(value==0){
            return u;
        }
        return mFormat.format(value);
    }
}
