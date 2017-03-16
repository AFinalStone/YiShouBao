package com.jvhe.yishoubao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.activity.CheckStandMoneyRecordActivity;
import com.jvhe.yishoubao.activity.MyBaseActivity;
import com.jvhe.yishoubao.util.CreateQRImageUtil;
import com.jvhe.yishoubao.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SHI on 2017/3/1 13:58
 * 收银台
 */
public class CheckStandFragment extends MyBaseFragment<MyBaseActivity> {


    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.iv_titleRight)
    ImageView iv_titleRight;

    @BindView(R.id.tv_arriveTomorrow)
    TextView tv_arriveTomorrow;

    @BindView(R.id.iv_arriveTomorrow)
    ImageView iv_arriveTomorrow;

    @BindView(R.id.linearLayout_arriveTomorrow)
    LinearLayout linearLayout_arriveTomorrow;

    @BindView(R.id.tv_arriveImmediately)
    TextView tv_arriveImmediately;

    @BindView(R.id.iv_arriveImmediately)
    ImageView iv_arriveImmediately;

    @BindView(R.id.linearLayout_arriveImmediately)
    LinearLayout linearLayout_arriveImmediately;

    @BindView(R.id.iv_QDCode)
    ImageView iv_QDCode;

    /**
     * 二维码生成对象
     **/
    private CreateQRImageUtil createQRImageUtil = new CreateQRImageUtil();


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(mActivity, R.layout.fragment_check_stand, null);
        ButterKnife.bind(this,rootView);
        iv_titleRight.setVisibility(View.VISIBLE);
        iv_titleRight.setImageResource(R.mipmap.icon_check_to_money_record);
        tv_title.setText(R.string.title_CheckStandWithdrawalActivity);
        showArriveTomorrow();
        return rootView;
    }

    @Override
    public void initData() {
        int length = DensityUtil.dip2px(mActivity,225);
        createQRImageUtil.createQRImage("http://www.zcy.gov.cn/", iv_QDCode, length, length);
    }

    @OnClick({R.id.iv_titleRight, R.id.linearLayout_arriveTomorrow, R.id.linearLayout_arriveImmediately})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_titleRight:
                startActivity(new Intent(mActivity, CheckStandMoneyRecordActivity.class));
                break;
            case R.id.linearLayout_arriveTomorrow:
                showArriveTomorrow();
                break;
            case R.id.linearLayout_arriveImmediately:
                showArriveImmediately();
                break;
        }
    }

    //明天到账
    private void showArriveTomorrow() {
        //右侧恢复正常颜色
        linearLayout_arriveImmediately.setBackgroundResource(R.drawable.check_stand_shape_check_right_normal);
        iv_arriveImmediately.setImageResource(R.mipmap.icon_check_immediately_white);
        tv_arriveImmediately.setTextColor(mActivity.colorPrimary);
        iv_arriveImmediately.setColorFilter(mActivity.colorPrimary);

        linearLayout_arriveTomorrow.setBackgroundResource(R.drawable.check_stand_shape_check_left_select);
        tv_arriveTomorrow.setTextColor(getResources().getColor(R.color.colorWhite_FFFFFFFF));
        iv_arriveTomorrow.setImageResource(R.mipmap.icon_check_tomorry_white);
        iv_arriveTomorrow.setColorFilter(getResources().getColor(R.color.colorWhite_FFFFFFFF));
    }

    //立即到账
    private void showArriveImmediately() {
        //左侧恢复正常颜色
        linearLayout_arriveTomorrow.setBackgroundResource(R.drawable.check_stand_shape_check_left_normal);
        tv_arriveTomorrow.setTextColor(mActivity.colorPrimary);
        iv_arriveTomorrow.setImageResource(R.mipmap.icon_check_tomorry_white);
        iv_arriveTomorrow.setColorFilter(mActivity.colorPrimary);

        linearLayout_arriveImmediately.setBackgroundResource(R.drawable.check_stand_shape_check_right_select);
        tv_arriveImmediately.setTextColor(getResources().getColor(R.color.colorWhite_FFFFFFFF));
        iv_arriveImmediately.setImageResource(R.mipmap.icon_check_immediately_white);
        iv_arriveImmediately.setColorFilter(getResources().getColor(R.color.colorWhite_FFFFFFFF));

    }



}
