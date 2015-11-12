package com.pengshang.restart.listener;

import java.util.Map;

public interface ZDTPayCallbackListener {

	/**
	 * 支付失败
	 * @param result
	 */
	public void onFail(Map result);
	/**
	 * 支付成功
	 * @param result
	 */
	public void onSuccess(Map result);
	/**
	 * 用户取消支付
	 * @param result
	 */
	public void onCancel();
}
