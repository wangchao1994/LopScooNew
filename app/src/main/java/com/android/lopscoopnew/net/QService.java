package com.android.lopscoopnew.net;

import com.android.lopscoopnew.bean.NewData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wangchao on 18-3-13.
 */

public interface QService {
    /**
     * 获取首页tabs
     *
     * @return
     */
    //http://www.lopscoop.com:90/lopscoop-api/token=test&category_code=Humor
    @GET("content_api?")
    Observable<NewData> getNewsData(
            @Query("token") String test,
            @Query("category_code") String category_code
    );
}
