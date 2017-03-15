package com.jvhe.yishoubao.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.adapter.MyBaseAdapter;
import com.jvhe.yishoubao.model.ShoppingMallSelectCondition;
import com.jvhe.yishoubao.util.ImagerLoaderUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 商家列表条件筛选
 * SHI
 * 2016-12-26 18:32:01
 */
public class PopWindowSelectCondition extends PopupWindow {

	private View mPopView;
	private GridView gridView;
	private Context mContext;
	private MyGridAdapter myGridAdapter;
	List<ShoppingMallSelectCondition> listData;
	public PopWindowSelectCondition(Context context, List<ShoppingMallSelectCondition> listData,final AdapterView.OnItemClickListener itemsOnClick) {
		super(context);
		mContext = context;
		mPopView = View.inflate(mContext, R.layout.pop_shopping_mall_condition_select, null);
		gridView = (GridView) mPopView.findViewById(R.id.gridView);
		// 设置SelectPicPopupWindow的View
		setContentView(mPopView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		setHeight(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		setFocusable(true);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x33000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		this.listData = listData;

		myGridAdapter = new MyGridAdapter(mContext,listData);
		gridView.setAdapter(myGridAdapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(itemsOnClick != null){
					itemsOnClick.onItemClick(parent,view,position,id);
				}
				dismiss();
			}
		});
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mPopView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent event) {

				int height = mPopView.findViewById(R.id.gridView).getBottom();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y > height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}

	protected class MyGridAdapter extends MyBaseAdapter<ShoppingMallSelectCondition> {

		public MyGridAdapter(Context mContext, List<ShoppingMallSelectCondition> listData) {
			super(mContext, listData);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MyViewHolder holder = null;
			if (convertView == null) {
				holder = new MyViewHolder();
				convertView = View.inflate(mContext, R.layout.adapter_shopping_mall_pop_gridview_item, null);
				holder.iv_model = (ImageView) convertView.findViewById(R.id.iv_model);
				holder.tv_modelName = (TextView) convertView.findViewById(R.id.tv_modelName);
				convertView.setTag(holder);
			} else {
				holder = (MyViewHolder) convertView.getTag();
			}
			ShoppingMallSelectCondition model = listData.get(position);
			ImagerLoaderUtil.getInstance(mContext).displayMyImage(model.modelUrl, holder.iv_model);
			holder.tv_modelName.setText(model.modelName);

			return convertView;
		}

		protected class MyViewHolder {
			private ImageView iv_model;
			private TextView tv_modelName;
		}
	}


}
