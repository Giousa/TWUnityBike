package com.zmm.unitycarracingdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.unity_framelayout)
    FrameLayout mUnityFramelayout;
    @InjectView(R.id.tv_offset_value)
    TextView mTvOffsetValue;
    @InjectView(R.id.tv_speed_value)
    TextView mTvSpeedValue;
    @InjectView(R.id.activity_main)
    LinearLayout mActivityMain;
    @InjectView(R.id.rel_splash)
    RelativeLayout mRelSplash;


    private PermanentUnityPlayer mUnityPlayer;
    private boolean isToggle = false;
    private CountDownTimer mCountDownTimer;


    private String model = "模式：被动";
    private float speed;
    private String time;
    private String mileage;
    private String cal;
    private String resistance;
    private String resistanceLevel;
    private int offset;
    private String spasm;
    private String spasmLevel;

    private int timeSecond = 0;
    private float distance;
    private int resistanceCount;
    private int resistanceLevelCount;
    private int spasmCount;
    private int spasmLevelCount;
    private UnityValueModel mUnityValueModel;
    private DecimalFormat mDecimalFormat;

    public static MainActivity Instance;

    private boolean isLoading = false;
    private boolean isStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instance = this;
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initView();
    }

    private void initView() {

        mUnityPlayer = new PermanentUnityPlayer(this);
        mUnityFramelayout.addView(mUnityPlayer);

        mDecimalFormat = new DecimalFormat("######0.00");
        mUnityValueModel = new UnityValueModel();
    }


    private void gameLoading() {

        if (isLoading) {
            return;
        }

        isLoading = true;

        System.out.println("Unity发来消息：开启进度");

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRelSplash.setVisibility(View.GONE);
            }
        });


    }

    private void gameStart() {

        if (isStart) {
            return;
        }

        isStart = true;
        System.out.println("Unity发来消息：游戏开始");

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCountDownTimer = new CountDownTimer(10000000, 1000) {
                    @Override
                    public void onTick(long l) {
                        timeSecond++;
                        int speed = Integer.parseInt(mTvSpeedValue.getText().toString());
                        distance += speed;
                        mileage = "里程：" + mDecimalFormat.format(distance * 0.001f) + "km";
                        cal = "卡路里：" + mDecimalFormat.format(60 * 1.036 * distance * 0.001f) + "千卡";
                        if (speed > 30) {
                            model = "模式：主动";
                        } else {
                            model = "模式：被动";
                        }

                        if (l % 10 == 0) {
                            resistanceCount++;
                            resistance = "阻力：" + resistanceCount + "值";
                            if (resistanceLevelCount >= 12) {
                                resistanceLevelCount = 0;
                            }
                            resistanceLevelCount++;
                            resistanceLevel = "阻力强度：" + resistanceLevelCount + "级";

                        }

                        if (l % 20 == 0) {
                            spasmCount++;
                            spasm = "痉挛：" + spasmCount + "次";
                            if (spasmCount > 12) {
                                spasmCount = 0;
                            }

                            if (spasmLevelCount >= 12) {
                                spasmLevelCount = 0;
                            }
                            spasmLevelCount = spasmLevelCount + 2;
                            spasmLevel = "痉挛等级：" + spasmLevelCount + "级";

                        }

                        if (mUnityPlayer != null) {
                            sendValueToUnity();
                        }


                    }

                    @Override
                    public void onFinish() {
                    }
                };

                mCountDownTimer.start();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.resume();


//        mCountDownTimer = new CountDownTimer(10000000, 1000) {
//            @Override
//            public void onTick(long l) {
//                timeSecond++;
//                int speed = Integer.parseInt(mTvSpeedValue.getText().toString());
//                distance += speed;
//                mileage = "里程：" + mDecimalFormat.format(distance * 0.001f) + "km";
//                cal = "卡路里：" + mDecimalFormat.format(60 * 1.036 * distance * 0.001f) + "千卡";
//                if (speed > 30) {
//                    model = "模式：主动";
//                } else {
//                    model = "模式：被动";
//                }
//
//                if (l % 10 == 0) {
//                    resistanceCount++;
//                    resistance = "阻力：" + resistanceCount + "值";
//                    if (resistanceLevelCount >= 12) {
//                        resistanceLevelCount = 0;
//                    }
//                    resistanceLevelCount++;
//                    resistanceLevel = "阻力强度：" + resistanceLevelCount + "级";
//
//                }
//
//                if (l % 20 == 0) {
//                    spasmCount++;
//                    spasm = "痉挛：" + spasmCount + "次";
//                    if (spasmCount > 12) {
//                        spasmCount = 0;
//                    }
//
//                    if (spasmLevelCount >= 12) {
//                        spasmLevelCount = 0;
//                    }
//                    spasmLevelCount = spasmLevelCount + 2;
//                    spasmLevel = "痉挛等级：" + spasmLevelCount + "级";
//
//                }
//
//                if (mUnityPlayer != null) {
//                    sendValueToUnity();
//                }
//
//
//            }
//
//            @Override
//            public void onFinish() {
//            }
//        };
//
//        mCountDownTimer.start();
    }


    private void sendValueToUnity() {
        //主动还是被动 速度 时间 里程 卡路里 阻力 阻力强度 偏移 痉挛次数 痉挛强度
        offset = Integer.parseInt(mTvOffsetValue.getText().toString());
        speed = Integer.parseInt(mTvSpeedValue.getText().toString());

//        speed = "速度："+speedValue+"km/h";
        time = "时间：" + TimeUtil.getStringTime(timeSecond);

        mUnityValueModel.setModel(model);
        mUnityValueModel.setSpeed(speed);
        mUnityValueModel.setTime(time);
        mUnityValueModel.setMileage(mileage);
        mUnityValueModel.setCal(cal);
        mUnityValueModel.setResistance(resistance);
        mUnityValueModel.setResistanceLevel(resistanceLevel);
        mUnityValueModel.setOffset(offset);
        mUnityValueModel.setSpasm(spasm);
        mUnityValueModel.setSpasmLevel(spasmLevel);

        mUnityPlayer.UnitySendMessage("Bike", "AndroidToUnityValue", JSON.toJSONString(mUnityValueModel));

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnityPlayer != null) {
            this.mUnityPlayer.quit();
            this.mUnityPlayer = null;
        }

        Instance = null;

    }

    public void onConfigurationChanged(Configuration var1) {
        super.onConfigurationChanged(var1);
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.configurationChanged(var1);
    }

    public void onWindowFocusChanged(boolean var1) {
        super.onWindowFocusChanged(var1);
        if (mUnityPlayer == null) {
            return;
        }
        this.mUnityPlayer.windowFocusChanged(var1);
    }

    public boolean dispatchKeyEvent(KeyEvent var1) {
        return var1.getAction() == 2 ? this.mUnityPlayer.injectEvent(var1) : super.dispatchKeyEvent(var1);
    }

    public boolean onKeyUp(int var1, KeyEvent var2) {

        return this.mUnityPlayer.injectEvent(var2);
    }


    public boolean onKeyDown(int var1, KeyEvent var2) {
        return this.mUnityPlayer.injectEvent(var2);
    }

    public boolean onTouchEvent(MotionEvent var1) {
        return this.mUnityPlayer.injectEvent(var1);
    }

    public boolean onGenericMotionEvent(MotionEvent var1) {
        return this.mUnityPlayer.injectEvent(var1);
    }

    @OnClick({R.id.btn_offset_dec, R.id.btn_offset_add, R.id.btn_speed_dec, R.id.btn_speed_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_offset_dec:
                decOffset();
                break;
            case R.id.btn_offset_add:
                addOffset();
                break;
            case R.id.btn_speed_dec:
                decSpeed();
                break;
            case R.id.btn_speed_add:
                addSpeed();
                break;
        }

        if (mUnityPlayer != null) {
            sendValueToUnity();
        }

    }


    private void decOffset() {
        int offset = Integer.parseInt(mTvOffsetValue.getText().toString());
        if (offset > -15) {
            offset--;
        }
        mTvOffsetValue.setText(offset + "");
    }

    private void addOffset() {
        int offset = Integer.parseInt(mTvOffsetValue.getText().toString());
        if (offset < 15) {
            offset++;
        }
        mTvOffsetValue.setText(offset + "");
    }

    private void decSpeed() {
        int speed = Integer.parseInt(mTvSpeedValue.getText().toString());
        if (speed > 0) {
            speed--;
        }
        mTvSpeedValue.setText(speed + "");
    }

    private void addSpeed() {
        int speed = Integer.parseInt(mTvSpeedValue.getText().toString());
        if (speed < 60) {
            speed++;
        }
        mTvSpeedValue.setText(speed + "");
    }
}
