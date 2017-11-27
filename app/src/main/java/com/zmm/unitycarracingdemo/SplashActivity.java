package com.zmm.unitycarracingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/11/27
 * Time:上午10:40
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btn_enter)
    public void onViewClicked() {

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
