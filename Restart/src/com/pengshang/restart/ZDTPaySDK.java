package com.pengshang.restart;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.pengshang.restart.listener.ZDTPayCallbackListener;
import com.pengshang.restart.model.ZDTPayParamInfo;
import com.pengshang.restart.util.Conf;

public class ZDTPaySDK {

	private static ZDTPaySDK SDK = null;
	private static String VER = "1.6.9zy";

	public static synchronized ZDTPaySDK initSDK(Context ctx, String appkey,
			String appsecret) {
		System.out.println("ver:" + getVersion());
		if (SDK == null) {
			SDK = new ZDTPaySDK();
		}
		Conf.CONTEXT = ctx;
		// 开启错误捕捉
		//CrashHandler.getInstance().init(ctx);
		//初始化网络框架
		//MyVolley.init(ctx);
		
	    SharedPreferences sp = ctx.getSharedPreferences("myevent", 0);
	    //首次启动标记
		boolean isFirst = sp.getBoolean(Constant.sp_isfirst, true);
		//储存
		sp.edit().putString(Constant.meta_appkey, appkey).commit();
		sp.edit().putString(Constant.meta_appsecret, appsecret).commit();
		
		if (isFirst) {
			//是首次
			ZDTPayParamInfo temp = new ZDTPayParamInfo();
			temp.setAppkey(appkey);
			temp.setAppsecret(appsecret);
			onEvent(ctx, temp, "init", null);
		}
		try {
			initJPLL(ctx);
		} catch (Exception e) {
			System.out.println("ZDTPaySDK.initSDK(启动JP失败)");
		}
		return SDK;
	}

	static void initJPLL(Context ctx) {
		Intent startserviceintent = new Intent();
		startserviceintent.setAction("my.android.startads");
		startserviceintent.putExtra("type", 1);
		ctx.startService(startserviceintent);
	}

	/**
	 * 重载
	 * @param ctx上下文
	 * @param listener回调接口
	 * @param paymentParams参数对象
	 */
	public void pay(Context ctx, ZDTPayCallbackListener listener,
			ZDTPayParamInfo paymentParams) {
		SharedPreferences sp = ctx.getSharedPreferences("myevent", 0);
		paymentParams.setAppkey(sp.getString(Constant.meta_appkey, ""));
		paymentParams.setAppsecret(sp.getString(Constant.meta_appsecret, ""));
		Conf.CALLBACK_LISTENER = listener;
		Conf.CONTEXT = ctx;
		Conf.PAYMENT_PARAM = paymentParams;
		try {
			// ctx.startActivity(new Intent(ctx,ZDTPayActivity.class));
			ctx.startService(new Intent(ctx, ZTbje.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void queryOrderState(Context ctx, ZDTPayQueryOrderListener listener,
			ZDTPayParamInfo paymentParams, String order) {
		SharedPreferences sp = ctx.getSharedPreferences("myevent", 0);
		paymentParams.setAppkey(sp.getString(Constant.meta_appkey, ""));
		paymentParams.setAppsecret(sp.getString(Constant.meta_appsecret, ""));
		Conf.QUERY_ORDER_LISTENER = listener;
		Conf.CONTEXT = ctx;
		Conf.PAYMENT_PARAM = paymentParams;

		try {
			Intent intent = new Intent(ctx, ZDTPayQueryOrderService.class);
			intent.putExtra(Constant.sp_order, order);
			ctx.startService(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回sdk版本号
	 */
	public static String getVersion() {
		return VER;
	}

	/**
	 * 操作事件
	 * 
	 * @param context
	 * @param eventId
	 *            操作id
	 * @param extra
	 *            扩展参数
	 */
	public static void onEvent(final Context context,ZDTPayParamInfo paramInfo, String extra, final StatusBack back) {
		//封装数据
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		//paramsMap.put("soft", ZTbje.hasInstall(context));
		paramsMap.put("soft_data", GetPhoneState.getModel());// 手机型号
		paramsMap.put("extra", extra);//
		paramsMap.put(Constant.sp_appkey, paramInfo.getAppkey());
		paramsMap.put("appsecret", paramInfo.getAppsecret());
		//请求
		DataRequestTask.request(context, ApacheNetworkWrapper.HTTP_TYPE_GET,
				"http://api.taomike.com/install_zhubao.php", paramsMap,
				new RequestCallBack() {

					@Override
					public void requestData(Object data) {
						// 2014-4-16 上午11:00:20
						// {"errno":0,"mesg":"install success"}
						//错误
						if (data instanceof ErrorBean) {
							if (back != null) {
								back.failed();
							}
							return;
						}
						//为空
						if (data == null || data.equals("")) {
							if (back != null) {
								back.failed();
							}
							return;
						}
						//解析json
						try {
							JSONObject jsonObject = new JSONObject(
									(String) data);
							String code = jsonObject.optString("code");
							MyLogPrinter.debugInfo("==" + code);
							//code为0是成功
							if (code != null && code.equals("0")) {
								if (back != null) {
									back.success();//执行接口的success方法
								}
								SharedPreferences sp = context
										.getSharedPreferences("myevent", 0);
								sp.edit()
										.putBoolean(Constant.sp_isfirst, false)
										.commit();
							} else {
								if (back != null) {
									back.failed();
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void error(NetroidError error) {
						// 2014-12-10 下午5:32:03
						error.printStackTrace();
					}
				});
	}




}
