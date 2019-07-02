package com.kgc.utils;

public class SmsUtil {
    //用户名
    private static String Uid = "lijiajiji66";
    //接口安全秘钥
    private static String Key = "d41d8cd98f00b204e980";

    //手机号码，多个号码如13800000000,13800000001,13800000002
    //private static String smsMob = "13800000000";

    //短信内容
    //private static String smsText = "验证码：8888";
    public static int sendMsm(String smsMob,String smsText) {
        HttpClientUtil client = HttpClientUtil.getInstance();
        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
        return result;
    }

}
