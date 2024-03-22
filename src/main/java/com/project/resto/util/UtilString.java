package com.project.resto.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilString {

	/**
	 * 生成32位随机串
	* @author: liuyafei
	* @date 创建时间：2016年8月23日
	* @version 1.0 
	* @parameter  
	* @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	/**
	 * 校验字符串的长度
	* @author: liuyafei
	* @date 创建时间：2016年8月26日
	* @version 1.0 
	* @parameter  
	* @return
	 */
	public static boolean checkStringLength(String msg,int num){
		if(msg.isEmpty()){
			return false;
		}
		if(msg.length()!=num){
			return false;
		}
		return true;
	}
	
	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(Long time) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time * 1000);;
		res = simpleDateFormat.format(date);
		return res;
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) throws java.text.ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}
	
	/**
	 * 当前时间点的秒数.<br>
	 * 
	 * @return
	 */
	public static int currentTimeSecond() {
		LocalTime time = LocalTime.now();
		return time.getSecond();
	}

    /**
     * 验证电话号码 匹配格式：11位手机号码
     */
    public static boolean checkPhone(String str) {
        if (str.isEmpty()) {
            return false;
        }
        String regex = "^1[3|4|5|7|8]\\d{9}$";
        if (!match(regex, str)) {
            return false;
        }
        return true;
    }

    /**
     * 校验密码
     */
    public static boolean checkPassword(String pwd) {
        if (pwd == null || pwd.length() != 32) {
            return false;
        }
        return true;
    }

    /**
     * 校验密码
     */
    public static boolean checkVerifyCode(String verifyCode) {
        if (verifyCode == null || verifyCode.length() != 6) {
            return false;
        }
        return true;
    }

    public static boolean isCardId(String str) {
        if (str.isEmpty()) {
            return false;
        }
        String regex = "([0-9]{17}([0-9]|X))|([0-9]{15})";
        return match(regex, str);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isHttpRequestSuccess(JSONObject jsonObj){
		if("1".equals(jsonObj.getString("status")) && "00000000".equals(jsonObj.getString("error"))) {
			return true;
		}
    	return false;
    }
    
    public static boolean isHttpRequestSuccess2(JSONObject jsonObj){
		if("1".equals(jsonObj.getString("status"))) {
			return true;
		}
    	return false;
    }
    
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    
    
    /**
	 * 获取终端信息
	* @version 1.0 
	* @parameter  type 0 获取type 1 获取值
	* @return
	 */
	public static String getDeviceInfo(String idfa,String imei,String mac,int type){
		if(!StringUtils.isEmpty(idfa)){
			if(type==0){
				return "idfa";
			}else{
				return idfa;
			}
		}else if(!StringUtils.isEmpty(imei)){
			if(type==0){
				return "imei";
			}else{
				return imei;
			}
		}else if(!StringUtils.isEmpty(mac)){
			if(type==0){
				return "mac";
			}else{
				return mac;
			}
		}
		return "";
	}
}
