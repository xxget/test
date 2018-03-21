package com.lding.wiqs.general.business.exception;

@SuppressWarnings("serial")
public class BaseWebException extends Exception{
	
	public BaseWebException(String errmsg) {
		super(errmsg); // = errmsg;
	}

}
