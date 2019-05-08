package cn.hisin.demo.http;

import okhttp3.*;

import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * 基于ok HTTP演示案例一
 * @author hisin
 */
public class OkHttpDemo1 {

    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://movie.douban.com/trailer/242330/#content")
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("调用失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String s = response.body().string();
                    System.out.println(s);
                }
            }
        });
    }
}


