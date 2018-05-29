package com.alfred.heartnews.network;

import com.alfred.heartnews.data.module.HomeBean;
import com.alfred.heartnews.network.service.ApiService;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alfred on 2018/5/17.
 */

public class HttpUtils {

    private static final String BASE_URL = "http://baobab.kaiyanapp.com/api/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private ApiService mApiService;

    private static final HttpUtils INSTANCE = new HttpUtils();


    private HttpUtils() {
        Logger.addLogAdapter(new AndroidLogAdapter());

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor());
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    public static HttpUtils getInstance() {
        return INSTANCE;
    }

    public void getHomeList(Observer<HomeBean> subscriber, boolean isFirst, String data) {
        Observable<HomeBean> observable;
        if (isFirst) {
            observable = mApiService.getHomeData();
        } else {
            observable = mApiService.getHomeMoreData(String.valueOf(data), "2");
        }

        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> observable, Observer<T> subscriber) {
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
