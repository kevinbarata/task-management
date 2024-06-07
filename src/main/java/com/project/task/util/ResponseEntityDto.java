package com.project.task.util;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
public class ResponseEntityDto<T> implements Serializable {

	private static final long serialVersionUID = -2644259941898334897L;

	private String status;
	private String error;
	private String msg;
	private T data;

	public ResponseEntityDto(){

	}

	public ResponseEntityDto(String status){
		this.status = status;
	}

	public ResponseEntityDto(String status, String error){
		this.status = status;
		this.error = error;
	}

	public ResponseEntityDto(String status, T data){
		this.status = status;
		this.data = data;
	}

	public ResponseEntityDto(String status, String error, String msg, T data){
		this.status = status;
		this.error = error;
		this.msg = msg;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public ResponseEntityDto<T> setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getError() {
		return error;
	}

	public ResponseEntityDto<T> setError(String error) {
		this.error = error;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseEntityDto<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		return data;
	}

	public ResponseEntityDto<T> setData(T data) {
		this.data = data;
		return this;
	}
}
