package com.wilson.okhttp_analysis.interceptor;

import com.wilson.okhttp_analysis.application.App;
import com.wilson.okhttp_analysis.utils.NetUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * maxAge和maxStale的区别在于：
 * maxAge:没有超出maxAge,不管怎么样都是返回缓存数据，超过了maxAge,发起新的请求获取数据更新，请求失败返回缓存数据。
 * maxStale:没有超过maxStale，不管怎么样都返回缓存数据，超过了maxStale,发起请求获取更新数据，请求失败返回失败
 * 链接：https://www.jianshu.com/p/dbda0bb8d541
 */
public class OffLineCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtils.checkEnable(App.context)) {
            int offlineCacheTime = 60;//离线的时候的缓存的过期时间
            //两种方式结果是一样的，写法不同
//            request = request.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
//                    .build();

             request = request.newBuilder().cacheControl(
                    new CacheControl.Builder()
                            .maxStale(60, TimeUnit.SECONDS)
                            .maxAge(60,TimeUnit.SECONDS)
                            .onlyIfCached()
                            .build())
                    .build();
        }
        return chain.proceed(request);
    }
}
