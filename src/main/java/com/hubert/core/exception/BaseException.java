package com.hubert.core.exception;

/**
 * 异常基类，各个模块的运行期异常均继承与该类
 * @author Hubrt
 *
 */
public class BaseException extends RuntimeException{

	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = 6883336104296151103L;
	/**
	 * message key
	 */
	private String code;
	/**
	 * exception message
	 */
	private String message;
	/**
	 * exception detail infomation
	 */
	private Throwable throwable;
	/**
	 * message params
	 */
	private Object[] values;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BaseException(String message, Throwable cause, String code,
            Object[] values) {
        super(message, cause);
        this.code = code;
        this.values = values;
    }
	
	
}
