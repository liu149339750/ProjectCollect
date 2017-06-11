package com.lw.api;

import com.lw.code.DemoEntry;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/10.
 */

public interface ProjectApiService {

    @GET("query")
    Observable<List<DemoEntry>> getProjectList(@Query("start") int start, @Query("limit") int limit, @Query("type") String type);

    @GET("sorks")
    Observable<List<String>> getSorkList();

}