package com.lw.api;

import com.lw.code.Constant;
import com.lw.code.DemoEntry;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/10.
 */

public class ProjectApi {

    private ProjectApiService mProjectApiService;
    public static ProjectApi mProjectApi;

    public ProjectApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        mProjectApiService = retrofit.create(ProjectApiService.class);
    }

    public static ProjectApi getInstance() {
        if(mProjectApi == null) {
            mProjectApi = new ProjectApi();
        }
        return mProjectApi;
    }


    public Observable<List<DemoEntry>> getProjectList( int start,int limit, String type){
        return mProjectApiService.getProjectList(start,limit,type);
    }

    public Observable<List<String>> getSorkList() {
        return mProjectApiService.getSorkList();
    }

}
