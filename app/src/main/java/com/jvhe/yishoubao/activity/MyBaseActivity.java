package com.jvhe.yishoubao.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.jude.utils.JUtils;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.util.ActivityCollectorUtil;
import com.jvhe.yishoubao.util.InformationCodeUtil;
import com.jvhe.yishoubao.util.PreferencesUtil;
import com.jvhe.yishoubao.util.SnackBarUtil;
import com.jvhe.yishoubao.util.ToastUtil;
import com.jvhe.yishoubao.view.FragmentOkAndCancelDialog;


/***
 * @author SHI
 *         所有Activity的父类
 *         2016-2-1 11:41:42
 */
public abstract class MyBaseActivity extends AppCompatActivity {
    /**
     * 当前Activity对象
     **/
    public FragmentActivity mContext = this;
    /**
     * 当前设备宽度
     **/
    public int displayDeviceWidth;
    /**
     * 当前设备高度
     **/
    public int displayDeviceHeight;
    /**
     * 返回键状态标记
     **/
    private boolean returnStatus = false;

    /**
     * 初始化布局文件
     **/
    public abstract void initView();

    /**
     * 初始化页面数据
     **/
    public abstract void initData();

    protected long exitTime = 0;

    public int colorPrimary;
    public int colorPrimaryDark;
    public int colorAccent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        displayDeviceWidth = getResources().getDisplayMetrics().widthPixels;
        displayDeviceHeight = getResources().getDisplayMetrics().heightPixels;
        initCustomTheme();
        initView();
        initData();
    }

    private void initCustomTheme() {
        int themedIndex = PreferencesUtil.getInt(mContext, InformationCodeUtil.SP_SettingThemed);
        switch (themedIndex) {
            case 1:
                setTheme(R.style.AppTheme1);
                break;
            case 2:
                setTheme(R.style.AppTheme2);
                break;
            case 3:
                setTheme(R.style.AppTheme3);
                break;
            case 4:
                setTheme(R.style.AppTheme4);
                break;
            case 5:
                setTheme(R.style.AppTheme5);
                break;
            default:
                setTheme(R.style.MyAppTheme);
                break;
        }

        TypedArray array = getTheme().obtainStyledAttributes(new int[] {
                android.R.attr.colorPrimary,
                android.R.attr.colorPrimaryDark,
                android.R.attr.colorAccent,
        });

        colorPrimary = array.getColor( 0, getResources().getColor(R.color.colorPrimary));
        colorPrimaryDark = array.getColor( 1, getResources().getColor(R.color.colorPrimaryDark));
        colorAccent = array.getColor( 2, getResources().getColor(R.color.colorAccent));
        array.recycle();
    }

    /**
     * 设置返回键状态
     **/
    protected void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    //显示是否退出应用的对话框
    protected void showQuitDialog() {

        final FragmentOkAndCancelDialog fragmentOkAndCancelDialog = new FragmentOkAndCancelDialog();
        fragmentOkAndCancelDialog.initView("温馨提示", "确定退出程序吗？", "取消", "退出",
                new FragmentOkAndCancelDialog.OnButtonClickListener() {

                    @Override
                    public void OnOkClick() {
                        ActivityCollectorUtil.finishAll();
                    }

                    @Override
                    public void OnCancelClick() {
                    }
                });
        fragmentOkAndCancelDialog.show(getSupportFragmentManager(), "fragmentOkAndCancelDialog");
    }


	//关闭当前Activity时
	@Override
	public void onBackPressed() {
		if(returnStatus){
            if((System.currentTimeMillis()-exitTime) > 2000){
                ToastUtil.show(mContext, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }else{
			super.onBackPressed();
		}
	}


    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }


}
