package com.lw.code;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.ref.SoftReference;
import java.util.List;

public class StaggeredAdapter extends BaseAdapter{
	
	private List<DemoEntry> mEntries;
	private LayoutInflater mInflater;
    private boolean mAutosize;
    private SparseArray<ViewDimensionCache> mViewDimensionCache;
    
    private int mDefaultWidth = 100;
    private int mDefaultHeight = 100;
	
    private final SparseArray<SoftReference<ImageView>> mImageViewsToLoad = new SparseArray<SoftReference<ImageView>>();
    
	public StaggeredAdapter(Context context,List<DemoEntry> entries) {
		mEntries = entries;
		mInflater = LayoutInflater.from(context);
		System.out.println(entries.size());
        if (mAutosize) {
            mViewDimensionCache = new SparseArray<ViewDimensionCache>();
        } else {
            mViewDimensionCache = null;
        }
	}
	
	public void setDatas(List<DemoEntry> datas) {
	    mEntries = datas;
	}

	@Override
	public int getCount() {
		System.out.println("getCount>"+mEntries.size());
		return mEntries.size() * 4;
	}

	@Override
	public Object getItem(int position) {
		return mEntries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if(convertView == null) {
			convertView = mInflater.inflate(R.layout.staggerd_item, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.text = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		DemoEntry entry = mEntries.get(position/4);
		holder.text.setText(entry.title);
		Glide.with(mInflater.getContext()).load(Constant.FILE_BASE_URL + entry.icon).into(holder.image);
		Log.v("dd","entry.icon = " + entry.icon);
		return convertView;
	}
	
	class ViewHolder {
		ImageView image;
		TextView text;
	}


    private static class ViewDimensionCache {
        int width;
        int height;
    }
}
