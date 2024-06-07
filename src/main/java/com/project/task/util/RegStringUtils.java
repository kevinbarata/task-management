package com.project.task.util;


import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegStringUtils {

    public static String getRevertStr(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return "";
        }
        String str = phone.replaceAll(" +", "");
        str = str.replace("+62", "0").replaceAll("-", "").replaceAll("—", "").trim();
        if (!"0".equals(str.subSequence(0, 1))) {
            str = "0" + str;
        }
        return str;
    }

    public static void main(String[] args) {
        String phone = unifyPhoneFormat("  0218 21-666 9-89  03");
        System.out.println(phone);
    }

    /**
     * 统一各项资料存储的手机号码格式（注册手机号、填写的联系人、抓取的通讯录和通话记录）
     *
     * @param phoneNum
     * @return
     */
    public static String unifyPhoneFormat(String phoneNum) {
        if (StringUtils.isEmpty(phoneNum)) {
            return "";
        }
        phoneNum = phoneNum.replaceAll(" +", "").replaceAll("-", "").replaceAll("—", "").trim();

        if (phoneNum.startsWith("+62")) {
            phoneNum = phoneNum.substring(3);
        } else if (phoneNum.startsWith("62")) {
            phoneNum = phoneNum.substring(2);
        }

        if (phoneNum.startsWith("+")) {
            phoneNum = phoneNum.substring(1);
        }

        if (!phoneNum.startsWith("0")) {
            phoneNum = "0" + phoneNum;
        }

        return phoneNum;
    }

    public static String validatePhoneFormat(String phoneNum) {
        String result;
        if (StringUtils.isEmpty(phoneNum)) {
            return "false";
        }

        // Ekspresi reguler untuk memeriksa apakah nomor telepon hanya terdiri dari digit
        String regex = "^[0-9]+$";

        // Membuat pattern dari regex
        Pattern pattern = Pattern.compile(regex);

        // Membuat matcher dari phoneNum
        Matcher matcher = pattern.matcher(phoneNum);

        if (!matcher.matches()) {
            return "false"; // Nomor telepon mengandung karakter selain digit
        }

        if (phoneNum.startsWith("08")) {
            result = "true";
        } else {
            result = "false";
        }

        return result;
    }


}
