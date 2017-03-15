package com.jvhe.yishoubao.SaleLine;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


/**
 * Created by SHI Jahoda on 14/09/15.
 */
public class WeekXAxisFormatter implements IAxisValueFormatter {

    protected String[] mWeeks = new String[]{
            "周一", "周二", "周三", "周四", "周五", "周六", "周日",};

    public WeekXAxisFormatter() {
        // maybe do something here or provide parameters in constructor
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mWeeks[(int)value];
    }
}
