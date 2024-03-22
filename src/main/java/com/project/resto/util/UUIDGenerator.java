package com.project.resto.util;

import java.util.UUID;

public class UUIDGenerator {
	  
	 public static String getUUID() {   
		 UUID uuid = UUID.randomUUID();   
		 String str = uuid.toString().replaceAll("-", "");   
	     return str;   
	 }   
	  
}
