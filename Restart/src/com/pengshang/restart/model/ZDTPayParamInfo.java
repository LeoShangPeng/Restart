package com.pengshang.restart.model;
/**
 * 
 * 这是上传参数实体类
 *
 */
public class ZDTPayParamInfo {

	
	/** 不弹出支付界面 **/
	public static final int DIALOG_TYPE_NOWINDOWS = -1;
	/** 两个按钮类型弹出框 **/
	public static final int DIALOG_TYPE_TWOBTN = 2;
	/** 一个按钮类型弹出框 **/
	public static final int DIALOG_TYPE_ONEBTN = 1;
	/** 当前弹出框类型 **/
	public int DIALOG_CURRENT_TYPE = DIALOG_TYPE_ONEBTN;

	/** appsecret **/
	private String appsecret;
	/** 每个渠道包的唯一key. 格式 Uid+a+appid+a+channeled **/
	private String appkey;
	/** 手机号 **/
	private String mobile;
	/** 手机运营商 **/
	private String mobile_type;
	/** 省份 **/
	private String province;
	/** 计费点 不可为空 **/
	private String price;

	/** 商品名称 不可为空 **/
	private String goodsname;

	/** 扩展参数 可为空 **/
	private String extra;

	/** 商品描述,可为空 **/
	private String description;

	/** 计费点标志,可为空 **/
	private String Jfdbz = "";

	private String leftbutton;
	private String rightbutton;

	
	public int getDialogType() {
		return DIALOG_CURRENT_TYPE;
	}

	public void setDialogType(int DIALOG_TYPE) {
		this.DIALOG_CURRENT_TYPE = DIALOG_TYPE;
	}

	public String getLeft() {
		return leftbutton;
	}

	public void setLeft(String leftbutton) {
		this.leftbutton = leftbutton;
	}

	public String getRight() {
		return rightbutton;
	}

	public void setRight(String rightbutton) {
		this.rightbutton = rightbutton;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile_type() {
		return mobile_type;
	}

	public void setMobile_type(String mobile_type) {
		this.mobile_type = mobile_type;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJfdbz() {
		return Jfdbz;
	}

	public void setJfdbz(String Jfdbz) {
		this.Jfdbz = Jfdbz;
	}

}
