package com.jvhe.yishoubao.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecycleView的适配器
 * Created by SHI on 2016/7/13 11:48
 */
public abstract class RecyclerViewBaseAdapter<VH extends RecyclerView.ViewHolder, VA> extends RecyclerView.Adapter<VH>{

    protected List<VA> listData;
    protected LayoutInflater mLayoutInflater;

    public RecyclerViewBaseAdapter(Context context, List<VA> listData) {
        mLayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType) ;

    @Override
    public int getItemCount() {
        if(listData == null){
            return 0;
        }
        return listData.size();
    }

}
