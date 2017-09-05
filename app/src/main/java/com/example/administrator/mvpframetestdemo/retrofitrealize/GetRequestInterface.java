package com.example.administrator.mvpframetestdemo.retrofitrealize;

import com.example.administrator.mvpframetestdemo.retrofitrealize.bean.Translation;
import com.example.administrator.mvpframetestdemo.retrofitrealize.bean.TranslationPost;

import java.util.Observer;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Poison_Y on 2017/8/28.
 * mail : yixiaoqiang1120@163.com
 */

public interface GetRequestInterface {

    /*注解里传入 网络请求 的部分URL地址
   Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
   如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
   getCall()是接受网络请求数据的方法*/

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world") //ajax.php?a=fy&f=auto&t=auto&w=hello%20world
    Call<Translation> getCall();

    @HTTP(method = "GET" , path = "ajax.php?a=fy&f=auto&t=auto&w=hello%20world" , hasBody = false)
    Call<Translation> httpGetCall();


    /*采用@Post表示Post方法进行请求（传入部分url地址）
     采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
     需要配合@Field 向服务器提交需要的字段*/
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<TranslationPost> postCall(@Field("i") String i);

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Observable<TranslationPost> postRxCall(@Field("i") String i);

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    Observable<TranslationPost> getBookDatas(@Query("i") String stri);


}
