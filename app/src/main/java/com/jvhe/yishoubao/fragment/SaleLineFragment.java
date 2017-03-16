package com.jvhe.yishoubao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.SaleLine.WeekXAxisFormatter;
import com.jvhe.yishoubao.activity.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHI on 2017/3/1 13:58
 * 商城
 */
public class SaleLineFragment extends MyBaseFragment<MyBaseActivity> {

    private String title;

    private LineChart lineChart;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(mActivity, R.layout.fragment_sale_line, null);
        lineChart = (LineChart) rootView.findViewById(R.id.lineChart);

        return rootView;
    }

    @Override
    public void initData() {
        Description ds = new Description();
        ds.setText(title);
        lineChart.setDescription(ds);
        lineChart.setData(getLineData());

        //设置X轴
        XAxis mXAxis = lineChart.getXAxis();
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mXAxis.setDrawGridLines(false);
        mXAxis.setValueFormatter(new WeekXAxisFormatter());

        //设置右侧的Y轴
        YAxis mAxisYRight = lineChart.getAxisRight();
        YAxis mAxisYLeft = lineChart.getAxisLeft();
        mAxisYRight.setDrawAxisLine(false);
        mAxisYRight.setGridColor(getResources().getColor(R.color.dividingLineColor));
//      mAxisYRight.setStartAtZero(false);
//      mAxisYLeft.setStartAtZero(false);
        // 設定最大值
        mAxisYLeft.setAxisMaxValue(600);
        mAxisYRight.setAxisMaxValue(600);
        // 設定最小值
        mAxisYLeft.setAxisMinValue(0); //如果 data 沒有到負數，就不會看到 min value 的效果囉
        mAxisYRight.setAxisMinValue(0);

        //设置动画效果
        lineChart.animateY(1000, Easing.EasingOption.Linear);
        lineChart.animateX(1000, Easing.EasingOption.Linear);
        lineChart.invalidate();
    }


    /* 將 DataSet 資料整理好後，回傳 LineData */
    private LineData getLineData() {
        //设置折线A相关属性
        LineDataSet dataSetA = new LineDataSet(getChartDataA(), "A");
        //设置折线A的颜色
        int colorLineA = mActivity.colorPrimary;
        dataSetA.setColor(colorLineA);
        dataSetA.setLineWidth(1f);
        //设置折线A折线点的颜色
        dataSetA.setCircleColor(colorLineA);
        dataSetA.setCircleRadius(3f);
        dataSetA.setCircleHoleRadius(1f);
        //设置点击某个点时候的横纵坐标
        dataSetA.setHighlightLineWidth(2f);
        dataSetA.setHighLightColor(colorLineA);
        //设置底部的遮影
//        dataSetB.setDrawFilled(true);

        //设置折线B相关属性
        LineDataSet dataSetB = new LineDataSet(getChartDataB(), "B");
        //设置折线A的颜色
        int colorLineB = mActivity.colorAccent;
        dataSetB.setColor(colorLineB);
        dataSetB.setLineWidth(1f);
        //设置折线A折线点的颜色
        dataSetB.setCircleColor(colorLineB);
        dataSetB.setCircleRadius(3f);
        dataSetB.setCircleHoleRadius(1f);
        //设置点击某个点时候的横纵坐标
        dataSetB.setHighlightLineWidth(2f);
        dataSetB.setHighLightColor(colorLineB);
        //设置底部的遮影
//        dataSetB.setDrawFilled(true);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSetA);
        dataSets.add(dataSetB);

        LineData data = new LineData(dataSets);

        return data;

    }


    private List<Entry> getChartDataA() {
        List<Entry> chartData = new ArrayList<>();
        chartData.add(new Entry(0, 340));
        chartData.add(new Entry(1, 320));
        chartData.add(new Entry(2, 300));
        chartData.add(new Entry(3, 380));
        chartData.add(new Entry(4, 390));
        chartData.add(new Entry(5, 550));
        chartData.add(new Entry(6, 520));
        return chartData;
    }

    private List<Entry> getChartDataB() {
        List<Entry> chartData = new ArrayList<>();
        chartData.add(new Entry(0, 110));
        chartData.add(new Entry(1, 120));
        chartData.add(new Entry(2, 100));
        chartData.add(new Entry(3, 120));
        chartData.add(new Entry(4, 90));
        chartData.add(new Entry(5, 210));
        chartData.add(new Entry(6, 190));
        return chartData;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
