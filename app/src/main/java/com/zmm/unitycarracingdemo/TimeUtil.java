package com.zmm.unitycarracingdemo;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/8/3
 * Time:上午9:54
 */

public class TimeUtil {

    //根据秒数转化为时分秒   00:00:00
    public static String getTime(int second) {
        if (second < 10) {
            return "00:0" + second;
        }
        if (second < 60) {
            return "00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + ":0" + second;
                }
                return "0" + minute + ":" + second;
            }
            if (second < 10) {
                return minute + ":0" + second;
            }
            return minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + minute + ":0" + second;
            }
            return "0" + hour + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + minute + ":0" + second;
        }
        return hour + minute + ":" + second;
    }

    //根据秒数转化为分秒   11分钟11秒
    public static String getStringTime(int second) {

        if (second < 10) {
            return "0" + second + "秒";
        }else if (second >= 10 && second < 60) {
            return second + "秒";
        }else if (second >= 60 && second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + "分0" + second + "秒";
                }
                return "0" + minute + "分" + second + "秒";
            }
            if (second < 10) {
                return minute + "分0" + second + "秒";
            }
            return minute + "分" + second + "秒";
        }


        return null;
    }

    //根据秒数转化为时分秒   00:00:00
    public static String getAllTime(int second) {
        if (second < 10) {
            return "00:0" + second;
        }
        if (second < 60) {
            return "00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + ":0" + second;
                }
                return "0" + minute + ":" + second;
            }
            if (second < 10) {
                return minute + ":0" + second;
            }
            return minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + minute + ":0" + second;
            }
            return "0" + hour + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + minute + ":0" + second;
        }
        return hour + minute + ":" + second;
    }

    //根据秒数转化为时分秒   00时00分00秒
    public static String getAllStringTime(int second) {
        if (second < 10) {
            return "00分0" + second+"秒";
        }
        if (second < 60) {
            return "00分" + second+"秒";
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + "分0" + second+"秒";
                }
                return "0" + minute + "分" + second+"秒";
            }
            if (second < 10) {
                return minute + "分0" + second+"秒";
            }
            return minute + "分" + second+"秒";
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + "时0" + minute + "分0" + second+"秒";
                }
                return "0" + hour + "时0" + minute + "分" + second+"秒";
            }
            if (second < 10) {
                return "0" + hour +"时"+ minute + "分0" + second+"秒";
            }
            return "0" + hour +"时"+ minute + "分" + second+"秒";
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + "时0" + minute + "分0" + second+"秒";
            }
            return hour + "时0" + minute + "分" + second+"秒";
        }
        if (second < 10) {
            return hour +"时"+ minute + "分0" + second+"秒";
        }
        return hour +"时"+ minute + "分" + second+"秒";
    }
}
