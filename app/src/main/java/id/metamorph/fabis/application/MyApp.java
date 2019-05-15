package id.metamorph.fabis.application;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import java.util.concurrent.TimeUnit;

import id.metamorph.fabis.utils.TypefaceUtil;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/montserat.ttf");


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();


//        AndroidNetworking.initialize(getApplicationContext(),client);
        AndroidNetworking.enableLogging();


    }
}
