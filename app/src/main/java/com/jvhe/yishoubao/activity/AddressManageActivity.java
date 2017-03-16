package com.jvhe.yishoubao.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afinalstone.androidstudy.swiperefreshview.OnSwipeRefreshViewListener;
import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshListView;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.adapter.MyBaseAdapter;
import com.jvhe.yishoubao.model.AddressModel;
import com.jvhe.yishoubao.model.CustomModel;
import com.jvhe.yishoubao.view.FragmentOkAndCancelDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收货地址界面
 *
 * @author SHI 2016-2-16 13:48:39
 */
public class AddressManageActivity extends MyBaseActivity implements
        OnSwipeRefreshViewListener {

    @BindView(R.id.iv_titleLeft)
    ImageView iv_titleLeft;

    @BindView(R.id.tv_titleLeft)
    TextView tv_titleLeft;
    /**
     * 地址管理
     **/
    @BindView(R.id.tv_title)
    TextView tv_title;
    /**
     * 右侧新增收货地址
     **/
    @BindView(R.id.iv_titleRight)
    ImageView iv_titleRight;
    /**
     * 我的收货数据
     **/
    @BindView(R.id.swipeRefreshListView)
    SwipeRefreshListView swipeRefreshListView;
    /**
     * 数据源
     **/
    private List<AddressModel> listData = new ArrayList<AddressModel>();
    /**
     * 适配器
     **/
    private ShoppingAddressAdapter shoppingAddressAdapter;
    /**
     * 当前登录用户
     **/
    private CustomModel mCustomModel;
    /**
     * 当前待删除收货地址对象
     **/
    private AddressModel addressModelToDelete;

    private ImageView listViewIsEmpty;

    @Override
    public void initView() {
        setContentView(R.layout.activity_address_manage);
        ButterKnife.bind(this);
        iv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setText(R.string.title_back);
        tv_title.setText(R.string.title_AddressManager);
        iv_titleRight.setImageResource(R.mipmap.icon_address_manager_title_right_add);
        iv_titleRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {


        // 初始化listView控件和外围的刷新控件
//		listViewIsEmpty = (ImageView) View.inflate(mContext,R.layout.common_adapter_listview_empty, null);
//		if(swipeRefreshListView.getListView().getHeaderViewsCount() == 0){
//			swipeRefreshListView.getListView().addHeaderView(listViewIsEmpty);
//		}
        listData.add(new AddressModel());
        listData.add(new AddressModel());
        listData.add(new AddressModel());
        listData.add(new AddressModel());
        listData.add(new AddressModel());
        shoppingAddressAdapter = new ShoppingAddressAdapter(this, listData);
        swipeRefreshListView.getListView().setAdapter(shoppingAddressAdapter);
        swipeRefreshListView.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                previewFinish(position);
            }
        });
        swipeRefreshListView.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        swipeRefreshListView.openRefreshState();
        super.onResume();
    }


    public void showDeleteDialog() {

        final FragmentOkAndCancelDialog fragmentDailog = new FragmentOkAndCancelDialog();
        fragmentDailog.initView("删除", "确定删除吗?", "取消", "确定",
                new FragmentOkAndCancelDialog.OnButtonClickListener() {

                    @Override
                    public void OnOkClick() {
                    }

                    @Override
                    public void OnCancelClick() {
                    }
                });
        fragmentDailog.show(getSupportFragmentManager(), "fragmentDialog");
    }

    @Override
    public void onTopRefrushListener() {
        swipeRefreshListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshListView.closeRefreshState();
            }
        }, 3000);
    }

    @Override
    public void onBottomRefrushListener() {
        swipeRefreshListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshListView.closeRefreshState();
            }
        }, 3000);
    }

    @OnClick({R.id.linearLayout_titleLeft, R.id.linearLayout_titleRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayout_titleLeft:
                finish();
                break;
            case R.id.linearLayout_titleRight:
                startActivity(new Intent(mContext,AddressEditActivity.class));
                break;
        }
    }

    /**
     * 收货地址适配器
     *
     * @author SHI
     *         2016-2-17 15:44:17
     */
    public class ShoppingAddressAdapter extends MyBaseAdapter<AddressModel> {

        public ShoppingAddressAdapter(Context mContext, List<AddressModel> listData) {
            super(mContext, listData);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.adapter_address_manager_listview, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        private class ViewHolder {
            /**
             * 用户名
             **/
            private TextView tv_userName;
            /**
             * 手机号码
             */
            private TextView tv_userPhone;
            /**
             * 收货地址
             **/
            private TextView tv_addressName;
            /**
             * 编辑地址按钮
             **/
            private LinearLayout linearLayout_editAddress;
            /**
             * 删除地址按钮
             **/
            private TextView tv_delete;
        }

    }

    private void previewFinish(int position) {

        if (listData == null || listData.size() == 0) {
            finish();
            return;
        }
        Intent intent = getIntent();
        intent.putExtra("selectAddressModel", listData.get(position));
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        previewFinish(0);
    }

}
