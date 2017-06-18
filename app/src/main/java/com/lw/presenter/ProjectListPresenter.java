package com.lw.presenter;

import android.util.Log;

import com.lw.api.ProjectApi;
import com.lw.code.DemoEntry;
import com.lw.view.IProjectListView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/10.
 */

public class ProjectListPresenter {

    private static final String TAG = "ProjectListPresenter";
    private IProjectListView mIView;

    public ProjectListPresenter(IProjectListView view) {
        mIView = view;
    }

    public void queryProjectDemoList() {
        ProjectApi.getInstance().getProjectList(0,100,"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DemoEntry>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<DemoEntry> demoEntry) {
                        if(mIView != null) {
                            Log.v(TAG,"onNext");
                            mIView.showProjectList(demoEntry);
                        }
                    }
                });

    }

}
