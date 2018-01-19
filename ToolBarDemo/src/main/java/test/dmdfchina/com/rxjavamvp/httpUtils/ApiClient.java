package test.dmdfchina.com.rxjavamvp.httpUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mkt on 2018/1/19.
 */

public class ApiClient {
    public static Retrofit mRetrofit;

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            mRetrofit = new Retrofit.Builder().baseUrl(ApiService.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }
}
