package com.fortunetree.basic.support.commons.business.result;

/**
 * Response 实体类
 *
 */
public class ResultMessage {

	public ResultMessage() {
	}

	public ResultMessage(boolean isError, Object message) {
		this.isError = isError;
		this.message = message;
	}

	public ResultMessage setVal(boolean isError, Object message) {
		this.isError = isError;
		this.message = message;
		return this;
	}

	public static ResultMessage success() {
		return success(null);
	}

	public static ResultMessage success(Object message) {
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setCode(ResponseCode.SUCCESS.value());
		resultMessage.setIsError(false);
		resultMessage.setMessage(message);
		return resultMessage;
	}

	public static ResultMessage error() {
		return error(null);
	}

	public static ResultMessage error(Object message) {
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setCode(ResponseCode.ERROR.value());
		resultMessage.setIsError(true);
		resultMessage.setMessage(message);
		return resultMessage;
	}

	public static ResultMessage error(int code, Object message) {
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setIsError(true);
		resultMessage.setCode(code);
		resultMessage.setMessage(message);
		return resultMessage;
	}

	/**
	 * 是否错误
	 */
	private boolean isError;
	/**
	 * 建议错误编码：
	 * @see ResponseCode
	 */
	private int code;
	/**
	 * 信息
	 */
	private Object message;

	public boolean getIsError() {
		return isError;
	}

	public void setIsError(boolean isError) {
		this.isError = isError;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public ResultMessage setCode(int code) {
		this.code = code;
		return this;
	}

	public ResultMessage setSuccess() {
		this.setIsError(false);
		return this;
	}
}