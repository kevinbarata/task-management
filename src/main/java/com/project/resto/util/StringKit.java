package com.project.resto.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author: likai
 * @date 创建时间：2016年8月23日
 * @version 1.0 
 * @parameter  
 * @return  
 */
public class StringKit {

	/**
	 * 生成32位随机串
	* @author: likai
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
	* @author: likai
	* @date 创建时间：2016年8月26日
	* @version 1.0 
	* @parameter  
	* @return
	 */
	public static boolean checkStringLength(String msg,int num){
		if(StringUtils.isBlank(msg)){
			return false;
		}
		if(msg.length()!=num){
			return false;
		}
		return true;
	}
	
	/**
	 * 校验金额
	* @author: likai
	* @date 创建时间：2017年11月21日
	* @version 1.0 
	* @parameter  
	* @return
	 */
    public static boolean isNumber(String str){   
         Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
         Matcher match=pattern.matcher(str);   
         if(match.matches()==false){   
            return false;   
         }else{   
            return true;   
         }   
     }
    
	/**
	 * 去除空格,换行符，回车符
	* @author: likai
	* @date 创建时间：2017年11月23日
	* @version 1.0 
	* @parameter  
	* @return
	 */
    public static String replaceBlank(String str) {  
    	return str.trim().replaceAll("\n","").replaceAll("\r","");
    }
    
    
}
