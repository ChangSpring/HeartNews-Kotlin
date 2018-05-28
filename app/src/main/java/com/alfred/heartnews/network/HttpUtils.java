package com.alfred.heartnews.network;

import com.alfred.heartnews.network.service.ApiService;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by alfred on 2018/5/17.
 */

public class HttpUtils {

    private static final String BASE_URL = "";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    private HttpUtils() {
        Logger.addLogAdapter(new AndroidLogAdapter());

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    private static class SingletonHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getHomeList(Subscriber<HomeBean> subscriber, boolean isFirst, String data) {
        Observable<HomeBean> observable;
        if (isFirst) {
            observable = mApiService.getHomeData();
        } else {
            observable = mApiService.getHomeMoreData(String.valueOf(data), "2");
        }

        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(rx.Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

//   private class HttpResultFunc<T> implements Func1<BaseEntity<T>, T> {
//       @Override
//       public T call(BaseEntity<T> tBaseEntity) {
//           if (tBaseEntity.getCode() != 1) {
//               return xxxxxx;
//           }
//           return tBaseEntity.getData();
//       }
//   }
}
