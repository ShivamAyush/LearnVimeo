package com.ayushsingh.learnvimeo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.vimeo.networking.Configuration;
import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.callbacks.AuthCallback;
import com.vimeo.networking.model.error.VimeoError;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private WebView videoView;
    private Button button;
    private VimeoApi vimeoApi;
    private TextView t1;
    private static final String ad = "https://vimeo.com/351683499";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.video1);
        button = findViewById(R.id.b1);
        t1 = findViewById(R.id.t1);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://vimeo.com/api/").addConverterFactory(GsonConverterFactory.create()).build();

        vimeoApi = retrofit.create(VimeoApi.class);

        Call<Frame> call = vimeoApi.getFrame(ad);

        call.enqueue(new Callback<Frame>() {
            @Override
            public void onResponse(Call<Frame> call, Response<Frame> response) {

                if(!response.isSuccessful()){
                    t1.setText("code : " + response.code());
                    return;
                }

                Frame frame = response.body();
                videoView.loadData(frame.getAddress() , "text/html" , "utf-8");
                String content = "Title : " + frame.getTitle() + "\n" + "Description : " + frame.getDescription() + "\n" + "Frame : " + frame.getAddress() + "\n";
                t1.setText(content);

            }

            @Override
            public void onFailure(Call<Frame> call, Throwable t) {
                t1.setText(t.getMessage());
            }
        });
    }

}
