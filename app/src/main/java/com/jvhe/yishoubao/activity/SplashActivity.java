package com.jvhe.yishoubao.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jvhe.yishoubao.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends MyBaseActivity {

    /**
     * 定义三个切换动画
     */
//    private Animation mFadeIn;

    private Animation mFadeOut;

    private Animation mFadeInScale;


    @BindView(R.id.application_bg)
    ImageView applicationBg;

    @Override
    public void initView() {
        setTheme(R.style.SplashTheme);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        RandomApplicationBg();
        initAnim();
        setAnimListener();
    }

    /**
     * 建立监听事件
     */
    private void setAnimListener() {
//        mFadeIn.setAnimationListener(new Animation.AnimationListener() {
//
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//
//            public void onAnimationEnd(Animation animation) {
//                applicationBg.startAnimation(mFadeInScale);
//            }
//        });
        mFadeInScale.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {


            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                applicationBg.startAnimation(mFadeOut);
            }
        });
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
                Intent intent = new Intent();
                intent.setClass(mContext, HomeActivity.class);
                startActivity(intent);
                finish();
            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {

            }
        });
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
//        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.splash_fade_in);
//        mFadeIn.setDuration(4000);

        mFadeInScale = AnimationUtils.loadAnimation(this, R.anim.splash_fade_in_scale);
        mFadeInScale.setDuration(4000);

        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.splash_fade_out);
        mFadeOut.setDuration(4000);

        applicationBg.setAnimation(mFadeInScale);
    }

    /**
     * 随机选择背景图片
     */
    private void RandomApplicationBg() {

//        int index = new Random().nextInt(2);
//        if (index == 1) {
          applicationBg.setImageResource(R.mipmap.backgroud_splash);
//        } else {
//            applicationBg.setImageResource(R.mipmap.entrance2);
//        }

    }

//    /**
//     * 进入欢迎界面
//     */
//    private void Welcome() {
//        Intent intent = new Intent();
//        intent.setClass(this, WelcomeActivity.class);
//        startActivity(intent);
//        finish();
//    }

}
