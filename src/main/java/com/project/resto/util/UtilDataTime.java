package com.project.resto.util;

import java.util.Date;

public class UtilDataTime {
	/**
	 * 获取当前时间戳
	 * 
	 * @author: liuyafei
	 * @date 创建时间：2016年8月23日
	 * @version 1.0
	 * @parameter
	 * @return
	 */
	public static Long getNowTime() {
		Date date = new Date();
		return date.getTime() / 1000;
	}
}
