package com.lw.code;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/15.
 */

public class ProjectAdapater extends RecyclerView.Adapter<ProjectAdapater.ViewHolder> {

    private LayoutInflater mInflater;
    private List<DemoEntry> mDemoEntries;
    private Context mContext;
    public ProjectAdapater(Context context,List<DemoEntry> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDemoEntries = new ArrayList<>();
        mDemoEntries.addAll(data);
    }

    public void add(List<DemoEntry> data) {
        mDemoEntries.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mInflater.inflate(R.layout.staggerd_item,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DemoEntry entry = mDemoEntries.get(position/4);
        holder.title.setText(entry.title);
        Glide.with(mContext).load(Constant.FILE_BASE_URL + entry.icon).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mDemoEntries.size() * 4;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @OnClick(R.id.item_content)
        public void onclick() {
            Intent intent = new Intent();
            DemoEntry entry = mDemoEntries.get(getAdapterPosition());
            intent.putExtra("data", entry);
            intent.setClass(mContext, DetailActivity.class);
            mContext.startActivity(intent);
        }
    }
}
