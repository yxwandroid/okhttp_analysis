/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package okhttp3.internal.connection;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpclient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.RealInterceptorChain;
// TODO 负者和服务器建立连接的拦截器  实际上建立连接就是创建了一个 HttpCodec 对象，它将在后面的步骤中被使用，
/** Opens a connection to the target server and proceeds to the next interceptor. */
public final class ConnectInterceptor implements Interceptor {
  public final OkHttpclient client;

  public ConnectInterceptor(OkHttpclient client) {
    this.client = client;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    RealInterceptorChain realChain = (RealInterceptorChain) chain;
    Request request = realChain.request();


    //从拦截器里面得到streamAllocation对象
    StreamAllocation streamAllocation = realChain.streamAllocation();

    // We need the network to satisfy this request. Possibly for validating a conditional GET.
    boolean doExtensiveHealthChecks = !request.method().equals("GET");

    // new Stream  具体做了什么事情
    // 从缓存池里面获取RealConnection 若是缓冲池里面没有RealConnection 就创建一个RealConnection 放入到缓存池
    // 获取到RealConnection 对象之后就调用connection  打开socket链接
    HttpCodec httpCodec = streamAllocation.newStream(client, chain, doExtensiveHealthChecks);

    // 获取realConnection
    RealConnection connection = streamAllocation.connection();

    return realChain.proceed(request, streamAllocation, httpCodec, connection);
  }
}
