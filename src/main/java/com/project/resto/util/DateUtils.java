package com.project.resto.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: yangguiyun
 * @Date: 2017/10/30 11:59
 * @Description:
 */
public final class DateUtils {
    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     *字符串的日期格式的计算
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
    
    
	/**
	 * 格式化时间-yyyy-MM-dd HH:mm:ss
	 * 
	 * @author: 李凯
	 * @date 创建时间：2017年11月3日
	 * @version 1.0
	 * @parameter
	 * @return
	 */
	public static String getFmtDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}
	
	/**
	 * 美国时间格式化
	 * 
	 * @author: 李凯
	 * @date 创建时间：2017年11月3日
	 * @version 1.0
	 * @parameter
	 * @return
	 */
	public static String getTimestamp(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		if ("".equals(str)||str==null)
			return null;
		else
			return new Timestamp(sdf.parse(str).getTime()).toString();
	}
	
	
	/**
	 * 增加时间
	 * 
	 * @author: 李凯
	 * @date 创建时间：2017年11月3日
	 * @version 1.0
	 * @parameter
	 * @return
	 */
	public static Date addDateMinut(Date date, int hour) throws ParseException {
		 	Calendar cal = Calendar.getInstance();   
	        cal.setTime(date);   
	        cal.add(Calendar.HOUR, hour);// 24小时制   
	        date = cal.getTime();   
	        return date;
	}
	
	
	
	/**
	 * 美国时间格式化
	 * 
	 * @author: 李凯
	 * @date 创建时间：2017年11月3日
	 * @version 1.0
	 * @parameter
	 * @return
	 */
	public static Date getTimestampToDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		if ("".equals(str)||str==null)
			return null;
		else{
//			Timestamp timestamp =	new Timestamp(sdf.parse(str).getTime());
//			Date dateTime = new Date(sdf.parse(str).getTime());//sql类型
//			Timestamp timestamp = (Timestamp) dateTime;
			
//			Timestamp timestamp = new java.sql.Timestamp(new Date(sdf.parse(str).getTime()).getTime());
//			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String date =  sdf1.format(timestamp);
//			System.out.println("date"+date);
			
			return new Date(sdf.parse(str).getTime());
		}
		
	}
	
	
	/**
	 * 美国时间格式化
	 * 
	 * @author: 李凯
	 * @date 创建时间：2017年11月3日
	 * @version 1.0
	 * @parameter
	 * @return
	 */
	public static Timestamp getTimestampDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		if ("".equals(str)||str==null)
			return null;
		else{
			Date date = new Date(sdf.parse(str).getTime());//获得系统时间.

			String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);//将时间格式转换成符合Timestamp要求的格式.
			return Timestamp.valueOf(nowTime);
		}
			
	}
	
	/**
	 * 计算小时差
	 * 
	 * @author: 李凯
	 * @date 创建时间：2017年11月19日
	 * @version 1.0
	 * @parameter
	 * @return
	 */
	public static String computTimeDiff(String str) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    long from = sdf.parse(str).getTime();  
	    long newDate = new Date().getTime();  
	    float hours =  (float) ((newDate - from)/3600000.0);  
	    DecimalFormat   fnum  =   new  DecimalFormat("##0.0");  
		return fnum.format(hours);
	}
	
	/**
	 * 计算小时差
	 * 
	 * @author: 李凯
	 * @date 创建时间：2017年11月19日
	 * @version 1.0
	 * @parameter
	 * @return
	 */
	public static String computTimeDiff(Date date) throws Exception {
	    long from = date.getTime();  
	    long newDate = new Date().getTime();  
	    float hours =  (float) ((newDate - from)/3600000.0);  
	    DecimalFormat   fnum  =   new  DecimalFormat("##0.0");  
		return fnum.format(hours);
	}
	
	
	
	/**
	 * 判断某一时间是否在一个区间内
	 * @author: 李凯
	 * 
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00:00-20:00:00)
	 * @param curTime
	 *            需要判断的时间 如10:00:00
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
		if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
		if (curTime == null || !curTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
		}
		String[] args = sourceTime.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			long now = sdf.parse(curTime).getTime();
			long start = sdf.parse(args[0]).getTime();
			long end = sdf.parse(args[1]).getTime();
			if (args[1].equals("00:00:00")) {
				args[1] = "24:00:00";
			}
			if (end < start) {
				if (now >= end && now < start) {
					return false;
				} else {
					return true;
				}
			} else {
				if (now >= start && now < end) {
					return true;
				} else {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}

	}
	
	
	/** 
	 * 获取从当前时间到今夜24时的时间差
	* @author: likai
	* @date 创建时间：2017年6月1日
	* @version 1.0 
	* @parameter  
	* @return  
	*/
	public static long getLeftTimeTo24Time(){
		
		Calendar calendar = Calendar.getInstance();
		long nowTime = calendar.getTimeInMillis();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long tomorrowTime = calendar.getTimeInMillis();
		return (tomorrowTime - nowTime)/1000;
	}
	
	/**
	 * 获取当前时间String
	* @author: likai
	* @date 创建时间：2017年6月08日
	* @version 1.0 
	* @parameter  
	* @return String
	 */
	public static String getNowDate(){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMdd" );
		Date date = new Date();
		return  sdf.format(date);
	}

	//查看印尼时间两天后的日期
	public static String getTowDaysLaterTime(){
		TimeZone timeZone = TimeZone.getTimeZone("GMT+7:00");
		SimpleDateFormat dft = new SimpleDateFormat("dd-MM-yyyy");
		dft.setTimeZone(timeZone);
		//SimpleDateFormat dft = new SimpleDateFormat("dd-MM-yyyy");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE)+2);
		//Date endDate = dft.parse(dft.format(date.getTime()));
		//System.out.println("=======>"+dft.format(endDate));
		return dft.format(date.getTime());
	}
}
