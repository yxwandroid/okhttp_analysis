package com.wilson.okhttp_analysis.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpNDSInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
//        Request originRequest = chain.request();
//        HttpUrl httpUrl = originRequest.url();
//
//         String url = httpUrl.toString();
//         String host = httpUrl.host();
//
//         hostIP = HttpDNS.getIpByHost(host)
//         builder = originRequest.newBuilder()
//
//        if (hostIP != null) {
//            builder.url(HttpDNS.getIpUrl(url, host, hostIP))
//            builder.header("host", hostIP)
//        }
//         newRequest = builder.build()
//         newResponse = chain.proceed(newRequest)
//        return newResponse
        return null;
    }
}
