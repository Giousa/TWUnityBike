package com.zmm.unitycarracingdemo;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/11/23
 * Time:下午3:18
 */

public class UnityValueModel {
    //主动还是被动 速度 时间 里程 卡路里 阻力 阻力强度 偏移 痉挛次数 痉挛强度
    private String model;
    private float speed;
    private String time;
    private String mileage;
    private String cal;
    private String resistance;
    private String resistanceLevel;
    private int offset;
    private String spasm;
    private String spasmLevel;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public String getResistanceLevel() {
        return resistanceLevel;
    }

    public void setResistanceLevel(String resistanceLevel) {
        this.resistanceLevel = resistanceLevel;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSpasm() {
        return spasm;
    }

    public void setSpasm(String spasm) {
        this.spasm = spasm;
    }

    public String getSpasmLevel() {
        return spasmLevel;
    }

    public void setSpasmLevel(String spasmLevel) {
        this.spasmLevel = spasmLevel;
    }
}
