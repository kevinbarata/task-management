package com.project.resto.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by zhouyaxin on 2017/12/7.
 * 过滤Emoji表情符
 */
public class EmojiFilterUtil {
    public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {
                    conbyte[i + j] = 0x30;// 0x30 int=48   字符=0
                }
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }

    public static String filterEmoji(String source) {
        if(StringUtils.isBlank(source)){
            return source;
        }else{
            if (source.indexOf("\\xF0\\x9F\\xA4\\x97")!=-1) {
                source = source.replaceAll("\\xF0\\x9F\\xA4\\x97","");
            }
            if (source.indexOf("\\xF0\\x9F\\xA4\\xB4")!=-1) {
                source = source.replaceAll("\\xF0\\x9F\\xA4\\xB4","");
            }
            if (source.indexOf("\\xF0\\x9F\\xA4\\x94")!=-1) {
                source = source.replaceAll("\\xF0\\x9F\\xA4\\x94","");
            }
            if (source.indexOf("\\xF0\\x9F\\xA4\\xA3")!=-1) {
                source = source.replaceAll("\\xF0\\x9F\\xA4\\xA3","");
            }
            if (source.indexOf("\\xF0\\x9F\\xA7\\x95")!=-1) {
                source = source.replaceAll("\\xF0\\x9F\\xA7\\x95","");
            }
            if (source.indexOf("\\xE2\\x80\\x86")!=-1) {
                source = source.replaceAll("\\xE2\\x80\\x86","");
            }
            return source.replaceAll("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]", "");
        }
    }


    public static String stringFilter(String str){
        // 只允许字母和数字和中文、下划线//[\\pP‘’“”
        String regEx = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$";
        Pattern p = Pattern.compile(regEx);
        StringBuilder sb = new StringBuilder(str); 

        for (int len = str.length(), i = len - 1; i >= 0; --i) {
            if (!p.matches(regEx,String.valueOf(str.charAt(i)))){

                sb.deleteCharAt(i);
            }
        }
        return sb.toString();
    }
}



