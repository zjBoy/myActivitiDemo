package utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorUtil {

	public ErrorUtil errCode(String errCode) {
		setErrCode(errCode);
		return this;
	}

	public static ErrorUtil create() {
		return new ErrorUtil();
	}
	public static ErrorUtil create(String errCode, String errMsg) {
		ErrorUtil result = create();
		result.setErrCode(errCode);
		result.setErrMsg(errMsg);
		return result;
	}

	public ErrorUtil errMsg(String errMsg) {
		setErrMsg(errMsg);
		return this;
	}

	private String errCode;
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String toJSONString(){
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRes="";
		try {
			jsonRes = objectMapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonRes;
	}
	public static String toJSONString(Object obj){
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRes="";
		try {
			jsonRes = objectMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonRes;
	}
}
