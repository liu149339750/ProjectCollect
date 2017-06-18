package com.lw.ui.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lw.code.DemoEntry;
import com.lw.code.ProjectAdapater;
import com.lw.code.R;
import com.lw.presenter.ProjectListPresenter;
import com.lw.view.IProjectListView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/18.
 */

public class ProjectListFragment extends BaseTabFragment implements IProjectListView {

    @BindView(R.id.staggeredGridView1)
    RecyclerView mRecyclerView;
    ProjectAdapater mAdapter;

    ProjectListPresenter mPresenter;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new ProjectListPresenter(this);
        GridLayoutManager gl = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(gl);
        mRecyclerView.addItemDecoration(new GridDecoration());
        mPresenter.queryProjectDemoList();
    }

    @Override
    public void showProjectList(List<DemoEntry> data) {
        Context context = getContext();
        if(context == null || mRecyclerView == null)
            return;
        mAdapter = new ProjectAdapater(context,data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public int getMainLayout() {
        return R.layout.main;
    }

    @Override
    public String getTabString() {
        return getArguments().getString("title",TAG);
    }


    class GridDecoration extends RecyclerView.ItemDecoration {
        int space = 20;
        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

        }
    }

    public static ProjectListFragment newInstance(String title) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }
}
