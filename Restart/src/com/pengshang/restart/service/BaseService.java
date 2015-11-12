package com.pengshang.restart.service;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * 
 * service基类
 *
 */
public class BaseService extends Service {
	//dialog
	protected AlertDialog mSimpleAlertDialog;
	//进度dialog
	protected ProgressDialog mLoadingDialog;
	
	protected AlertDialog.Builder builer;
	
	protected SharedPreferences mysp;
	
	public Dialog dialog;
	public TextView price;
	public TextView product;
	public TextView desc;
	protected Context context;
	
	//构造方法
	public BaseService() {
	}
	
	@Override
	public void onCreate() {
		// 2014-8-21 上午10:57:58
		builer = new AlertDialog.Builder(this);
		context=this;
		mysp=getSharedPreferences("mysp", 0);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// 2014-8-21 上午10:51:37
		return null;
	}
	
	public void closePay(){
		try {
			dismissLoadingDialog();
			dismissSimpleAlertDialog();
			if(dialog!=null&&dialog.isShowing())
			dialog.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Loading按钮监听事件
	 */
	private OnKeyListener mOnKeyListener = new OnKeyListener() {

		@Override
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			MyLogPrinter.debugInfo("onKey");
			if (keyCode == KeyEvent.KEYCODE_BACK
					&& event.getAction() == KeyEvent.ACTION_DOWN) {
				if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
					dismissLoadingDialog();
				}
			}
			return true;
		}
	};
    
    /**
	 * 展示loading进度�?
	 * 
	 * @param msg
	 */
	public void showLoadingDialog(String msg) {
//		MyLogPrinter.debugInfo("showLoadingDialog  mIsActive = ");
//		
//		if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
//			return;
//		}
//		mLoadingDialog = new ProgressDialog(this);
//		mLoadingDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);
//		mLoadingDialog.setCanceledOnTouchOutside(true);
//		mLoadingDialog.setMessage(msg);
//		mLoadingDialog.setOnKeyListener(mOnKeyListener);
//		
//		mLoadingDialog.show();
//		
//		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
//		((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
//				.getDefaultDisplay().getMetrics(mDisplayMetrics);
//		WindowManager.LayoutParams params = mLoadingDialog.getWindow().getAttributes();
//		mLoadingDialog.getWindow().setLayout(mDisplayMetrics.widthPixels/2, -2);
	}

	/**
	 * 取消loading进度
	 * 
	 * @param msg
	 */
	public void dismissLoadingDialog() {
		if (mLoadingDialog != null&&mLoadingDialog.isShowing()) {
			mLoadingDialog.dismiss();
		}
	}
	
	public void showSimpleAlertDialog(String msg) {
		if (mSimpleAlertDialog != null && mSimpleAlertDialog.isShowing()) {
			return;
		}
		mSimpleAlertDialog = builer.create();
		mSimpleAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		mSimpleAlertDialog.setMessage(msg);
		mSimpleAlertDialog.show();
	}


	public void dismissSimpleAlertDialog() {
		if (mSimpleAlertDialog != null&&mSimpleAlertDialog.isShowing()) {
			mSimpleAlertDialog.dismiss();
		}
	}
	
	/**
	 * 获得资源id
	 * @param name
	 * @param defType
	 * @return
	 */
	public int getResId(String name,String defType){
		return getResources().getIdentifier(name, defType, getPackageName());
	}

}
