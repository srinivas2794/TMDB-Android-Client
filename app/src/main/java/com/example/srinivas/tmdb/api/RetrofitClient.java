package com.example.srinivas.tmdb.api;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    private static Retrofit retrofit = null;
    private static Map<String, String> queryParams;

    static Retrofit getClient(String baseUrl, Map<String, String> defaultQueryParams) {
        queryParams = defaultQueryParams;
        if (retrofit == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl.Builder urlBuilder = request.url()
                            .newBuilder();

                    for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                        urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                    }

                    HttpUrl url = urlBuilder
                            .build();
                    Request newRequest = chain.request().newBuilder().url(url).build();
                    return chain.proceed(newRequest);
                }
            });
            OkHttpClient client = clientBuilder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}