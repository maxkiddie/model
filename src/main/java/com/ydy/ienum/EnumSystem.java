/**
 * 
 */
package com.ydy.ienum;

import com.ydy.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2018年11月12日 上午9:41:48
 */
public enum EnumSystem implements IErrorEnum{
	SUSS(0, "请求成功"),
	SYSTEM_ERROR(1000, "system error"),
	PARAM_ERROR(1001, "parameter miss"),
	PARAM_FORMAT_ERROR(1002, "parameter's format error"),
	DATA_NOT_MATCH(1003, "parameter's data mistake"),
	DATA_NOT_FOUND(1004, "can not found data"),
	TOKEN_HAD_MODIFY(1005, "token had been modify"),
	TOKEN_EXPIRED(1005, "token is expired"),
	TOKEN_NOT_MATCH(1006, "token not fit"),
	TOKEN_NOT_EXSIT(1007, "token is necessary parameter"),
	USER_CAN_NOT_GET(1008, "can not found user's info"),
	ADMIN_CAN_NOT_GET(1009, "can not found admin's info"),
	CODE_EXPIRED(1010, "Verification code had expired"),
	CODE_ERROR(1011, "Verification code not fit"),
	DATA_REPEAT(1012, "Form had commited,please wait"),
	NO_AUTH(1013, "no auth"),
	PWD_NOT_FIT(1014, "comfirm password not fit"),
	FILE_TYPE_NOT_FIT(1015, "file type not fit"),
	CODE_PRD_ERROR(1016, "please refresh your html to get verification code"),
	REMOTE_IP_ERROR(1017, "remote netword error"),
	CREATE_FILE_ERROR(1018, "file create error"),
	FILE_IO_ERROR(1019, "File IO stream error"),
	FILE_NOT_FOUND(1020, "File not found"),
	PACK_FILE_ERROR(1021, "Pack file error"),
	DEL_FILE_ERROR(1022, "delete file error"),
	ENCODE_ERROR(1023, "encode error"),
	AES_ENCODE_ERROR(1024, "AES encode errror"),
	AES_DECODE_ERROR(1025, "AES decode error"),
	SIGN_EXPIRE(1026, "sign expire"),
	EMAIL_SEND_FAST(1027, "email had send,please wait a minute"),
	NET_IO_ERROR(1028, "File download error,IO exception"),
	DOWNLOAD_TO_FAST(1029, "File download to many,please wait a minute");
	
	private Integer code;
	private String msg;

	private EnumSystem(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}
	
	@Override
	public String getMsg() {
		return msg;
	}

}
