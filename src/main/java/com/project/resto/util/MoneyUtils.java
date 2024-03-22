package com.project.resto.util;

import java.text.DecimalFormat;

/**
 * Created by DELL on 2018/6/21.
 */
public class MoneyUtils {
    //金额格式化
    public static String formatTosepara(double data) {
        DecimalFormat df = new DecimalFormat("#,###");
        String moneyf =df.format(data);
        String money = moneyf.replaceAll(",",".");
        return "Rp"+money;
    }

    //保留两位小数
    public static String formatDouble(double data) {
        DecimalFormat    df   = new DecimalFormat("######0.00");
        return df.format(data);
    }
}
