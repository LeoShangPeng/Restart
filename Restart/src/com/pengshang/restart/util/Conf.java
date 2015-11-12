package com.pengshang.restart.util;

import android.content.Context;

import com.pengshang.restart.listener.ZDTPayCallbackListener;
import com.pengshang.restart.model.CodeSmsBean;
import com.pengshang.restart.model.ZDTPayParamInfo;



public class Conf {
	
	public final static String PERSON_STORAGE_DIR_NAME = "/apps/FrontiaDevDemo/pic";
	public final static String PERSON_STORAGE_FILE_NAME = "/apps/FrontiaDevDemo/pic/custom.jpg";
	public static ZDTPayCallbackListener CALLBACK_LISTENER;
	
	public static Context CONTEXT;
	public static ZDTPayParamInfo PAYMENT_PARAM;
	public static CodeSmsBean CODEBEAN;
}
