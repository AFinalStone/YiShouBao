package com.jvhe.yishoubao.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.model.AddressModel;
import com.jvhe.yishoubao.util.InformationCodeUtil;
import com.jvhe.yishoubao.util.ToastUtil;
import com.jvhe.yishoubao.view.PopWindowSelectAddress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author SHI
 * @action 新增或者编辑收货地址
 * @date 2016-2-17 10:09:43
 */
public class AddressEditActivity extends MyBaseActivity implements OnClickListener {
    /**
     * 返回控件
     **/
    @BindView(R.id.iv_titleLeft)
    ImageView iv_titleLeft;

    /**
     * 返回控件
     **/
    @BindView(R.id.tv_titleLeft)
    TextView tv_titleLeft;
    /**
     * 标题
     **/
    @BindView(R.id.tv_title)
    TextView tv_title;

    /**
     * 保存
     **/
    @BindView(R.id.tv_titleRight)
    TextView tv_titleRight;
    @BindView(R.id.iv_titleRight)
    ImageView iv_titleRight;
    /**
     * 姓名
     **/
    @BindView(R.id.et_userNumber)
    EditText et_userNumber;
    /**
     * 联系电话
     **/
    @BindView(R.id.et_userPhone)
    EditText et_userPhone;


    /**
     * 选择省、市、区控件
     **/
    @BindView(R.id.linearLayout_selectAddress)
    LinearLayout linearLayout_selectAddress;
    /**
     * 所在地区
     **/
    @BindView(R.id.tv_selectAddress)
    TextView tv_selectAddress;
    /**
     * 详细收货地址
     **/
    @BindView(R.id.et_receiverAddress)
    EditText et_receiverAddress;
    /**
     * 当前待编辑的收货地址对象
     **/
    private AddressModel currentAddressModel;
    /**
     * 是否成功获取省、市、区
     **/
    private boolean selectSuccessFlag;


    PopWindowSelectAddress pop;
    View rootView;

    @Override
    public void initView() {
        rootView = View.inflate(mContext, R.layout.activity_address_edit, null);
        setContentView(rootView);
        ButterKnife.bind(this);
        iv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setText(R.string.title_back);
        tv_titleRight.setVisibility(View.VISIBLE);
        tv_titleRight.setText(R.string.AddressManager_tv_save);
        iv_titleRight.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        currentAddressModel = (AddressModel) getIntent().getSerializableExtra("currentAddressModel");
        if (currentAddressModel == null) {
            tv_title.setText("新增地址");
            selectSuccessFlag = false;
            currentAddressModel = new AddressModel();
            currentAddressModel.setDjLsh(-1);
//            currentAddressModel.setUserID(MyApplication.getmCustomModel(mContext).getDjLsh());
        } else {
            selectSuccessFlag = true;
            tv_title.setText("修改地址");
            et_userNumber.setText(currentAddressModel.getRealName());
            et_userPhone.setText(currentAddressModel.getPhoneNum());
            et_receiverAddress.setText(currentAddressModel.getAddress());
            String strPosition = currentAddressModel.getProvinceName() + " " + currentAddressModel.getCityName() + " " + currentAddressModel.getAreaName();
            tv_selectAddress.setText(strPosition);
        }

        tv_titleRight.setOnClickListener(this);
        linearLayout_selectAddress.setOnClickListener(this);

    }

    @OnClick({R.id.linearLayout_titleLeft, R.id.linearLayout_selectAddress, R.id.linearLayout_titleRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayout_titleLeft:
                finish();
                break;
            case R.id.linearLayout_titleRight:
                if (checkMsg()) {
                    if ("新增地址".equals(tv_title.getText())) {
//                        getData(InformationCodeUtil.methodNameAddAddr);
                    } else {
//                        getData(InformationCodeUtil.methodNameEditAddr);
                    }
                }
                 break;
            case R.id.linearLayout_selectAddress:
                showSelectAddressView();
                break;
        }
    }

    private void showSelectAddressView() {

        pop = new PopWindowSelectAddress(this, new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 隐藏弹出窗口
                pop.dismiss();
                switch (v.getId()) {
                    case R.id.tv_cancel:
                        tv_selectAddress.setText("所在地区");
                        selectSuccessFlag = false;
                        break;
                    case R.id.tv_ok:
                        String selectAddress = pop.selectCurrentFirstPosition.getName() + " "
                                + pop.selectCurrentSecondPosition.getName() + " " + pop.selectCurrentThirdPosition.getName();
                        tv_selectAddress.setText(selectAddress);
                        currentAddressModel.setProvinceCode(pop.selectCurrentFirstPosition.getCode());
                        currentAddressModel.setCityCode(pop.selectCurrentSecondPosition.getCode());
                        currentAddressModel.setAreaCode(pop.selectCurrentThirdPosition.getCode());
                        selectSuccessFlag = true;
                        break;
                    default:
                        break;
                }
            }
        });
        pop.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    private boolean checkMsg() {
        if (et_userNumber.getText().toString().isEmpty()) {
            ToastUtil.show(mContext, "请输入收货人名称");
            return false;
        }
        if (!InformationCodeUtil.RegExp_PhotoNumber.matches(et_userPhone.getText().toString())) {
            ToastUtil.show(mContext, "请输入合法的手机号码");
            return false;
        }
        if (et_receiverAddress.getText().toString().isEmpty()) {
            ToastUtil.show(mContext, "请输入详细地址");
            return false;
        }
        if (selectSuccessFlag == false) {
            ToastUtil.show(mContext, "请选择所在地区");
            return false;
        }
        return true;
    }

    private AddressModel getAddressModel() {

        currentAddressModel.setRealName(et_userNumber.getText().toString());
        currentAddressModel.setPhoneNum(et_userPhone.getText().toString());
        currentAddressModel.setAddress(et_receiverAddress.getText().toString());
        return currentAddressModel;

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }

//	public void onActivityResult(int requestCode,int resultCode,Intent data){
//		switch (resultCode) {
//		case RESULT_OK:
//			//获取对应key的map值
//			String text = data.getStringExtra(InformationCodeUtil.IntentAddressLocationSelect);
//			if(!StringUtil.isEmpty(text)){
//				tv_selectPosition.setText(text);
//				selectSuccessFlag = true;
//				currentAddressModel.setProvinceCode(MyApplication.getmAddressModel().getProvinceCode());
//				currentAddressModel.setCityCode(MyApplication.getmAddressModel().getCityCode());
//				currentAddressModel.setAreaCode(MyApplication.getmAddressModel().getAreaCode());
//			}
//			break;
//		}
//	}

}
