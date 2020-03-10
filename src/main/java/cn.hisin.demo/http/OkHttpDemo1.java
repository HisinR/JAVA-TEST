package cn.hisin.demo.http;

import okhttp3.*;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * 基于ok HTTP演示案例一
 *
 * @author hisin
 */
public class OkHttpDemo1 {

    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://kapi.qiqiangkeji.com/goods/getAll?lat=30.2756724316&lon=120.1629638672&scope=1&page=1")
                .get()
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


