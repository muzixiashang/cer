package com.liyunet.common.json;

import com.liyunet.common.ResponseCode;

import java.io.Serializable;

public class BaseJSONModel implements Serializable{
	private static final long serialVersionUID = 7342440767264018199L;
	
	private JSONModelState state;
	
	public static BaseJSONModel defaultError(){
		BaseJSONModel base = new BaseJSONModel();
		JSONModelState baseState = base.createState();
		baseState.ajaxError("error");
		return base;
	}
	
	public static BaseJSONModel defaultError(String msg){
		BaseJSONModel base = new BaseJSONModel();
		JSONModelState baseState = base.createState();
		baseState.ajaxError(msg);
		return base;
	}
	
	
	public static BaseJSONModel systemError(String msg){
		BaseJSONModel base = new BaseJSONModel();
		JSONModelState baseState = base.createState();
		baseState.ajaxError(msg);
		return base;
	}
	
	public static BaseJSONModel serviceError(String msg){
		BaseJSONModel base = new BaseJSONModel();
		JSONModelState baseState = base.createState();
		baseState.ajaxServiceError(msg);
		return base;
	}
	
	public static BaseJSONModel notLoginError(){
		BaseJSONModel base = new BaseJSONModel();
		JSONModelState baseState = base.createState();
		baseState.ajaxNotLoginError("error");
		return base;
	}
	
	
	public JSONModelState getState() {
		return state;
	}
	
	public static BaseJSONModel nullErrorAjaxJson(String msg){
		BaseJSONModel json = new BaseJSONModel();
		JSONModelState state2 = json.createState();
		state2.ajaxError(msg);
		return json;
	}
	
	public static BaseJSONModel nullSuccessAjaxJson(String msg){
		BaseJSONModel json = new BaseJSONModel();
		JSONModelState state2 = json.createState();
		state2.ajaxError(msg);
		return json;
	}
	
	
	public JSONModelState createState(){
		JSONModelState jsonModelState = new JSONModelState();
		this.state = jsonModelState;
		return jsonModelState;
	}
	

	public static class JSONModelState{
		private String code;
		private String msg;
		
		public void success(String msg){
			this.code = ResponseCode.SUCCESS.getValue();
			this.msg = msg;
		}
		
		public void error(String msg){
			this.code = ResponseCode.ERROR.getValue();
			this.msg = msg;
		}
		
		public void ajaxSuccess(String msg){
			this.code = ResponseCode.AJAXSUCCESS.getValue();
			this.msg = msg;
		}
		
		public void ajaxError(String msg){
			this.code = ResponseCode.AJAXSYSTEMERROR.getValue();
			this.msg = msg;
		}
		
		public void ajaxServiceError(String msg){
			this.code = ResponseCode.AJAXSERVICEERROR.getValue();
			this.msg = msg;
		}
		public void ajaxSkipError(String msg){
			this.code = ResponseCode.AJAXSKIPEERROR.getValue();
			this.msg = msg;
		}
		public void ajaxNotLoginError(String msg){
			this.code = ResponseCode.AJAXNOTLOGINERROR.getValue();
			this.msg = msg;
		}
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
