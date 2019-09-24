package com.hsw.testapp.domain.dagger.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hsw.testapp.BuildConfig;
import com.hsw.testapp.TestApp;
import com.hsw.testapp.common.NetworkConstants;
import com.hsw.testapp.domain.executor.JobExecutor;
import com.hsw.testapp.domain.executor.PostExecutionThread;
import com.hsw.testapp.domain.executor.ThreadExecutor;
import com.hsw.testapp.domain.executor.UIThread;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by hsw
 * on 2018/8/16.
 */
@Module
public class AppModule {
    private final TestApp app;

    public AppModule(TestApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    TestApp provideApp() {
        return this.app;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }


    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread executor) {
        return executor;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConstants.HOST)
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }


    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        SSLSocketFactory sslSocketFactory = null;
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
        }
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);
        if (sslSocketFactory != null) {
            builder.sslSocketFactory(sslSocketFactory);
        }
        // 信任所有主机名
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        })
                // 这里我们使用host name作为cookie保存的key
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                });
        return builder.build();
    }
}
