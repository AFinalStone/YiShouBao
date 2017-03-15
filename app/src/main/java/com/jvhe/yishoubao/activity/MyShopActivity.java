package com.jvhe.yishoubao.activity;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.util.CreateQRImageUtil;
import com.jvhe.yishoubao.util.DensityUtil;
import com.jvhe.yishoubao.view.PopWindowSelectShareType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyShopActivity extends MyBaseActivity {

    @BindView(R.id.iv_titleLeft)
    ImageView iv_titleLeft;

    /**
     * 主页标题
     **/
    @BindView(R.id.tv_title)
    TextView tv_title;

    /**
     * 右侧分享按钮
     **/
    @BindView(R.id.tv_titleRight)
    TextView tv_titleRight;
    /**
     * 右侧分享按钮
     **/
    @BindView(R.id.iv_titleRight)
    ImageView iv_titleRight;

    /**
     * 右侧分享按钮
     **/
    @BindView(R.id.linearLayout_QRCode)
    LinearLayout linearLayout_QRCode;

    /**
     * 右侧分享按钮
     **/
    @BindView(R.id.iv_myQRCode)
    ImageView iv_myQRCode;

    /**
     * 右侧分享按钮
     **/
    @BindView(R.id.tv_myShopName)
    TextView tv_myShopName;

    /**
     * 路径
     **/
    public static String IMAGELOGO_URL;
    /**
     * Log图片
     **/
    private final String FILE_NAME = "/weidian.png";
    private final String URL_WEIDIAN = "http://m.dainif.com//WeiXinAPI/Agent/Default.aspx?ShopID=";
    /**
     * 当前用户的店铺名称
     **/
    private String shopName;
    /**
     * 当前用户的店铺ID
     **/
    private int shopId;
    PopWindowSelectShareType pop;

    protected View rootView;

    @Override
    public void initView() {
        rootView = View.inflate(mContext,R.layout.activity_my_shop,null);
        setContentView(rootView);
        ButterKnife.bind(this);
        iv_titleLeft.setVisibility(View.VISIBLE);
        iv_titleRight.setVisibility(View.GONE);
        tv_titleRight.setVisibility(View.VISIBLE);
        tv_titleRight.setText(R.string.MyShop_tv_share);
    }

    @Override
    public void initData() {
        shopName = "乡里乡亲";
        shopId = 36;
        tv_title.setText(shopName);
        tv_myShopName.setText(shopName);
        CreateQRImageUtil QRImageUtil = new CreateQRImageUtil();
        int length = DensityUtil.dip2px(mContext, 300);
        QRImageUtil.createQRImage(URL_WEIDIAN + shopId, iv_myQRCode, length, length);
    }

    @OnClick({R.id.linearLayout_titleLeft, R.id.linearLayout_titleRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayout_titleLeft:
                finish();
                break;
            case R.id.linearLayout_titleRight:
                showShareTypePopupWindow();
                break;
        }
    }

    public void showShareTypePopupWindow() {
        pop = new PopWindowSelectShareType(mContext, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pop.showAtLocation(rootView,Gravity.CENTER,0,0);
    }

    private void showShareShopUrl() {

//        saveScreen(linearLayout_QRCode);
//        ShareSDK.initSDK(this);
//        OnekeyShare oks = new OnekeyShare();
//
//        oks.setTitle("好店推荐"); // 最多30个字符
//        if (StringUtil.isEmpty(shopName)) {
//            shopName = "我的微店";
//        }
//        oks.setText(shopName); // 最多40个字符
//        oks.setImagePath(IMAGELOGO_URL);
//        oks.setUrl(URL_WEIDIAN + shopId);
//        // 启动分享GUI
//        oks.show(this);
    }

    private void showShareShopImageUrl() {
//        saveScreen(linearLayout_QRCode);
//        ShareSDK.initSDK(this);
//        OnekeyShare oks = new OnekeyShare();
//        oks.setImagePath(IMAGELOGO_URL);
//        // 启动分享GUI
//        oks.show(this);
    }

    /**
     * 把图片从drawable复制到sdcard中
     **/
    private void initImagePath() {
//        try {
//            if (Environment.MEDIA_MOUNTED.equals(Environment
//                    .getExternalStorageState())
//                    && Environment.getExternalStorageDirectory().exists()) {
//                IMAGELOGO_URL = Environment.getExternalStorageDirectory()
//                        .getAbsolutePath() + FILE_NAME;
//            } else {
//                IMAGELOGO_URL = getApplication().getFilesDir().getAbsolutePath()
//                        + FILE_NAME;
//            }
//            File file = new File(IMAGELOGO_URL);
//            if (!file.exists()) {
//                InputStream is = getAssets().open("weidian.png");
//                is.available();
//                Bitmap pic = BitmapFactory.decodeStream(is);
//                FileOutputStream fos = new FileOutputStream(file);
//                pic.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            }
//        } catch (Throwable t) {
//            t.printStackTrace();
//            IMAGELOGO_URL = null;
//        }
    }

    private void saveScreen(View v) {
//        try {
//            if (Environment.MEDIA_MOUNTED.equals(Environment
//                    .getExternalStorageState())
//                    && Environment.getExternalStorageDirectory().exists()) {
//                IMAGELOGO_URL = Environment.getExternalStorageDirectory()
//                        .getAbsolutePath() + FILE_NAME;
//            } else {
//                IMAGELOGO_URL = getApplication().getFilesDir().getAbsolutePath()
//                        + FILE_NAME;
//            }
//
//            Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas();
//            canvas.setBitmap(bitmap);
//            v.draw(canvas);
//
//            FileOutputStream fos = new FileOutputStream(IMAGELOGO_URL);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            initImagePath();
//        }
    }


}
