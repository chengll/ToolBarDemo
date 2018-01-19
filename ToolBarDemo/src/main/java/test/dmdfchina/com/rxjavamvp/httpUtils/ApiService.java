package test.dmdfchina.com.rxjavamvp.httpUtils;

import java.util.Observer;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import test.dmdfchina.com.rxjavamvp.model.WeatherModel;

/**
 * Created by mkt on 2018/1/18.
 */

public interface ApiService {
    public static  final  String baseUrl="http://apistore.baidu.com/microservice/";
    /*post请求*/
    @POST("/manager.php?m=Admin&c=OfflineLesson&a=getSchoolLesson")
    Observer getData(@Field("id") String id, @Field("l_id") int l_id);

    /*get请求*/
    @GET("/manager.php?m=Admin&c=OfflineLesson&a=getSchoolLesson")
    Observer getNetGet(@Query("id") String id, @Query("t_id") int t_id);



    //加载天气
    @GET("weather")
    Observable<WeatherModel> loadDataByRetrofitRxjava(@Query("citypinyin") String cityId);
}
