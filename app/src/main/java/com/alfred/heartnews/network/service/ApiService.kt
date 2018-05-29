package com.alfred.heartnews.network.service

import com.alfred.heartnews.data.module.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by alfred on 2018/5/18.
 */
interface ApiService {
    companion object {
        val BASE_URL : String
        get() = "http://baobab.kaiyanapp.com/api/"
    }

    @GET("v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")
    fun getHomeData(): Observable<HomeBean>

    @GET("v2/feed")
    fun getHomeMoreData(@Query("date") date : String,@Query("num") num :String) : Observable<HomeBean>
}