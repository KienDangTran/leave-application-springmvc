package com.giong.web.persistence;

import java.io.Serializable;

public class JsonResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String RESPONSE_STATUS_FAIL = "FAIL";
	public static final String RESPONSE_STATUS_SUCCESS = "SUCCESS";

	private String status = JsonResponse.RESPONSE_STATUS_FAIL;
	private Object result = null;

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return this.result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
