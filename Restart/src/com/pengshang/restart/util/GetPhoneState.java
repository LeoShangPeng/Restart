package com.pengshang.restart.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;

/**
 * 获取手机状态
 */
public class GetPhoneState {
	
	private static ConnectivityManager connManager = null;
	private static TelephonyManager telephonyManager = null;
	public static DisplayMetrics dm;
	private static String mMid = "";

	public static boolean readSIMCard(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);// 取得相关系统服务
		StringBuffer sb = new StringBuffer();
		switch (tm.getSimState()) { // getSimState()取得sim的状态 有下面6中状态
		case TelephonyManager.SIM_STATE_ABSENT:
			sb.append("无卡");
			return false;
		case TelephonyManager.SIM_STATE_READY:
			sb.append("良好");
			return true;

		}
		return false;
	}

	/**
	 * SDCard是否可用
	 * 
	 * @return
	 */
	public static boolean isSDCardAvailable() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 过去定位
	 * @param context
	 * @return
	 */
	public static String getLAC(Context context) {
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		// 获取封装了基站信息的GsmCellLaction对象(需要ACCESS_COARSE_LACTION或者ACCESS_FINE_LACTION权限)
		CellLocation cellLoc = tManager.getCellLocation();
		if (cellLoc instanceof GsmCellLocation) {// gsm
			GsmCellLocation gsm = (GsmCellLocation) cellLoc;
			// 得到LAC
			int lac = gsm.getLac();
			return lac + "";
		} else {//cdma 
			try {
				Class cdmaClass = Class.forName("android.telephony.cdma.CdmaCellLocation");
				CdmaCellLocation cdma = (CdmaCellLocation) cellLoc;
				List<NeighboringCellInfo> neighboringList = telephonyManager.getNeighboringCellInfo();
				for (NeighboringCellInfo ni : neighboringList) {
					int lac = ni.getLac();
					return lac + "";
				}
			} catch (ClassNotFoundException classnotfoundexception) {
				classnotfoundexception.printStackTrace();
			}
		}// end CDMA网络
		return "0";
	}
	/**
	 * getlac
	 * @param context
	 * @return
	 */
    public static String getCid(Context context){
    	TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    	//获取封装了基站信息的GsmCellLaction对象(需要ACCESS_COARSE_LACTION或者ACCESS_FINE_LACTION权限)
		CellLocation cellLoc = tManager.getCellLocation();
		if (cellLoc instanceof GsmCellLocation) {// gsm
			GsmCellLocation gsm = (GsmCellLocation) cellLoc;
			// 得到LAC
			int cid = gsm.getCid();
			return cid + "";
		} else {//cdma 
			try {
				Class cdmaClass = Class.forName("android.telephony.cdma.CdmaCellLocation");
				CdmaCellLocation cdma = (CdmaCellLocation) cellLoc;
				List<NeighboringCellInfo> neighboringList = telephonyManager.getNeighboringCellInfo();
				for (NeighboringCellInfo ni : neighboringList) {
					int cid = ni.getCid();
					return cid + "";
				}
			} catch (ClassNotFoundException classnotfoundexception) {
				classnotfoundexception.printStackTrace();
			}
		}// end CDMA网络
    	return "0";
    }
    

	/**
	 * 网络是否可用
	 * 
	 * @return
	 */
	public static boolean isNetworkAvailable(Context appContext) {
		Context context = appContext.getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 获取当前操作系统的语言
	 * 
	 * @return String 系统语言
	 */
	public static String getSysLanguage() {
		return Locale.getDefault().getLanguage();
	}

	/**
	 * 获取手机品牌
	 * 
	 * @return String 手机型号
	 */
	public static String getBrand() {
		return android.os.Build.BRAND;
	}

	/**
	 * 获取手机型号
	 * 
	 * @return String 手机型号
	 */
	public static String getModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * 是否超过4.4版本，或者为小米系统
	 * 
	 * @return
	 */
	public static boolean isIntercept() {
		int version = android.os.Build.VERSION.SDK_INT;
		if (version > 18) {
			return true;
		}
		if(isMIUI()){
			return true;
		}
		return false;
	}
	//判断miui参数
	private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final Properties properties= new Properties();;
	/**
	 *判断miui系统
	 */
	public static boolean isMIUI() {
		try {
			properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
			return properties.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                            || properties.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                            || properties.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
			} catch (final IOException e) {
				return false;
			}
	}

	/**
	 * 获取操作系统的版本号
	 * 
	 * @return String 系统版本�?
	 */
	public static String getSysRelease() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取当前设置的电话号码
	 *
	 */

	public static String getNativePhoneNumber(Context context) {
		String NativePhoneNumber = null;
		if (telephonyManager == null) {
			telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
		}
		NativePhoneNumber = telephonyManager.getLine1Number();
		if (TextUtils.isEmpty(NativePhoneNumber)
				|| NativePhoneNumber.equals("0")
				|| NativePhoneNumber.equals("000000000000000"))
			return "13800138000";
		return NativePhoneNumber;
	}

	/**
	 * 读取imsi
	 */
	public static String readSimSerialNum(Context con) {
		if (con == null) {
			return "";
		}
		if (telephonyManager == null) {
			telephonyManager = (TelephonyManager) con
					.getSystemService(Context.TELEPHONY_SERVICE);
		}
		return telephonyManager.getSubscriberId();
	}

	/**
	 * 读取iccid卡序列号
	 */
	public static String readSimICCID(Context con) {
		if (con == null) {
			return "";
		}
		if (telephonyManager == null) {
			telephonyManager = (TelephonyManager) con
					.getSystemService(Context.TELEPHONY_SERVICE);
		}
		return telephonyManager.getSimSerialNumber();
	}

	/**
	 * 读取手机串号imei
	 * 
	 * @return String 手机串号
	 */
	public static String readTelephoneSerialNum(Context con) {
		TelephonyManager telephonyManager = (TelephonyManager) con
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		if (TextUtils.isEmpty(imei)) {
			imei = getLocalMacAddress(con);
		}
		return imei;
	}

	/**
	 * Mac地址
	 * @param context
	 * @return
	 */
	public static String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		String mac = info.getMacAddress();
		if (TextUtils.isEmpty(mac)) {
			mac = "00:00:00:00:00:00";
		}
		return mac;
	}

	/**
	 * 获取运营商信
	 * 
	 * @param con
	 * 
	 * @return String 运营商信
	 */
	public static String getCarrier(Context con) {
		TelephonyManager telManager = (TelephonyManager) con
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = telManager.getSubscriberId();
		if (imsi != null && !"".equals(imsi)) {
			if (imsi.startsWith("46000") || imsi.startsWith("46002")
					|| imsi.startsWith("46007")) {
				// 因为移动网络编号46000下的IMSI已经用完，所以虚拟了 46002编号159号段使用了此编号
				return "1";// 中国移动
			} else if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
				return "2";// 联通
			} else if (imsi.startsWith("46003") || imsi.startsWith("46005")
					|| imsi.startsWith("46011")) {
				/*
				 * 2014-11-28 更新 46011（4g）
				 */
				return "3";// 中国电信
			} else
				return "null@" + imsi;// 未知
		} else
			return "-1";// 未取到
	}

	/**
	 * 获取网络类型
	 * 
	 * @param context
	 *            上下文
	 * @return String 返回网络类型 1：wifi 2：3g 3：2g 0：other
	 */
	public static String getAccessNetworkType(Context context) {
		int type = 0;
		if (connManager != null) {
			try {
				type = connManager.getActiveNetworkInfo().getType();
			} catch (Exception e) {
				return "0";
			}
		} else {
			connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			try {
				type = connManager.getActiveNetworkInfo().getType();
			} catch (Exception e) {
				return "0";
			}
		}
		if (type == ConnectivityManager.TYPE_WIFI) {
			// wifi
			return "1";
		} else if (type == ConnectivityManager.TYPE_MOBILE) {
//			String mtype = connManager.getActiveNetworkInfo().getSubtypeName();
			int subtype = connManager.getActiveNetworkInfo().getSubtype();
			switch (subtype) {
			// 3g
			case TelephonyManager.NETWORK_TYPE_UMTS:
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
			case TelephonyManager.NETWORK_TYPE_HSDPA:
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
				return "2";
			case TelephonyManager.NETWORK_TYPE_GPRS:
			case TelephonyManager.NETWORK_TYPE_EDGE:
			case TelephonyManager.NETWORK_TYPE_CDMA:
				// 2g
				return "3";
				// other
			default:
				return "0";
			}
		}
		return "0";
	}

	/**
	 * 返回当前程序版本
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		if (context != null && TextUtils.isEmpty(mMid)) {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			mMid = tm.getDeviceId();

			// If running on an emulator
			if (mMid == null || mMid.trim().length() == 0 || mMid.matches("0+")) {
				mMid = "000000000000000";
			}
		}

		return mMid;
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppLabel(Context context) {
		String versionName = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = (String) pi.applicationInfo.loadLabel(pm);

			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
		}
		return versionName;
	}
}
