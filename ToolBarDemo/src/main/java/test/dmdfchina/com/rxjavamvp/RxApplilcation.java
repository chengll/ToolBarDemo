package test.dmdfchina.com.rxjavamvp;

import android.app.Application;
import android.content.Context;

/**
 * Created by mkt on 2018/1/18.
 */

public class RxApplilcation extends Application {
    private static RxApplilcation instance;
    public static Context getInstance(){
        return instance==null?null:instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
