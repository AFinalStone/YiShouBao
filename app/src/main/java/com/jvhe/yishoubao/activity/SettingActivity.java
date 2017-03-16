package com.jvhe.yishoubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.utils.JUtils;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.util.InformationCodeUtil;
import com.jvhe.yishoubao.util.PreferencesUtil;
import com.jvhe.yishoubao.util.ToastUtil;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：SHI
 * 2017-03-15 13:45:23
 */
public class SettingActivity extends MyBaseActivity {




    @BindView(R.id.iv_titleLeft)
    ImageView iv_titleLeft;

    @BindView(R.id.tv_titleLeft)
    TextView tv_titleLeft;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.setting_switch)
    SwitchCompat switchCompatSetting;

    @Override
    public void initView() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        iv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setText(R.string.title_back);
        tv_title.setText(R.string.title_Setting);

        if (PreferencesUtil.getBoolean(mContext, InformationCodeUtil.SP_ReceivePush, true)) {
            switchCompatSetting.setChecked(true);
        } else {
            switchCompatSetting.setChecked(false);
        }
    }

    @Override
    public void initData() {

    }


    private void createSelectThemeDialog() {
        View layoutView = LayoutInflater.from(this).inflate(R.layout.setting_theme_color_select, null);
        Holder holder = new ViewHolder(layoutView);
        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                switch (view.getId()) {
                    case R.id.theme1:
                        PreferencesUtil.putInt( mContext, InformationCodeUtil.SP_SettingThemed, 1);
                        break;
                    case R.id.theme2:
                        PreferencesUtil.putInt( mContext, InformationCodeUtil.SP_SettingThemed, 2);
                        break;
                    case R.id.theme3:
                        PreferencesUtil.putInt( mContext, InformationCodeUtil.SP_SettingThemed, 3);
                        break;
                    case R.id.theme4:
                        PreferencesUtil.putInt( mContext, InformationCodeUtil.SP_SettingThemed, 4);
                        break;
                    case R.id.theme5:
                        PreferencesUtil.putInt( mContext, InformationCodeUtil.SP_SettingThemed, 5);
                        break;

                }
                dialog.dismiss();
                restartApp();

            }
        };

        DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setOnClickListener(clickListener)
                .create();
        dialogPlus.show();
    }


    private void restartApp() {
        ToastUtil.show(mContext,"设置成功,重启生效");
        final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    @OnClick({R.id.linearLayout_titleLeft, R.id.linearLayout_settingUpdate, R.id.linearLayout_settingTheme})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayout_titleLeft:
                finish();
                break;
            case R.id.linearLayout_settingUpdate:
                ToastUtil.show(mContext,"已经是最新版本");
                break;
            case R.id.linearLayout_settingTheme:
                createSelectThemeDialog();
                break;
        }
    }
}
