package com.pengshang.restart.model;

import java.util.ArrayList;

public class CodeSmsBean {
		private String code;
		private String zf_view;
		private String err_msg;

		public String getErr_msg() {
			return err_msg;
		}

		public void setErr_msg(String err_msg) {
			this.err_msg = err_msg;
		}

		ArrayList<ZDTOrder> orders;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getZf_view() {
			return zf_view;
		}

		public void setZf_view(String zf_view) {
			this.zf_view = zf_view;
		}

		public ArrayList<ZDTOrder> getOrders() {
			return orders;
		}

		public void setOrders(ArrayList<ZDTOrder> orders) {
			this.orders = orders;
		}

		public class ZDTOrder {

			String order;
			String zl;
			String receive;
			ArrayList<String> keyword;
			String ec_port;// 上行号码
			ArrayList<String> ec_spport;// 下行号码
			String ec_mo;// 上行内容
			String isdel;
			int yz;
			String yzm;//验证码
	        String yzm_qz;//验证码前缀 有些匹配出来的验证码还要加前缀
			/**
			 * 2015-05-21
			 * 日|周|月|单次    0 表示不限
			 */
			String limit;
			String yzm_url;//验证码传给url
			String tongdao_id;//通道id
			String smsMessage;//发送方式
			String managerPort;//data方式端口号
			
			public String getManagerPort() {
				return managerPort;
			}

			public void setManagerPort(String managerPort) {
				this.managerPort = managerPort;
			}

			public String getSmsMessage() {
				return smsMessage;
			}

			public void setSmsMessage(String smsMessage) {
				this.smsMessage = smsMessage;
			}
			
			public void setTongdao_id(String id){
				tongdao_id=id;
			}
			
			public String getTongdao_id() {
				return tongdao_id;
			}
			
			public String getYzm_url() {
				return yzm_url;
			}

			public void setYzm_url(String yzm_url) {
				this.yzm_url = yzm_url;
			}

	        public String getYzmqz() {
	            return yzm_qz;
	        }

	        public void setYzmqz(String yzmqz) {
	            this.yzm_qz = yzmqz;
	        }
			
			public String getYzm() {
				return yzm;
			}

			public void setYzm(String yzm) {
				this.yzm = yzm;
			}

			public String getLimit() {
				return limit;
			}

			public void setLimit(String limite) {
				this.limit = limite;
			}

			public ArrayList<String> getDownAddre() {
				return ec_spport;
			}

			public void setDownAddre(ArrayList<String> downAddre) {
				this.ec_spport = downAddre;
			}

			public String getUpAddre() {
				return ec_port;
			}

			public void setUpAddre(String upAddre) {
				this.ec_port = upAddre;
			}

			public String getUpBody() {
				return ec_mo;
			}

			public void setUpBody(String upBody) {
				this.ec_mo = upBody;
			}

			public String getOrder() {
				return order;
			}

			public void setOrder(String order) {
				this.order = order;
			}

			public String getZl() {
				return zl;
			}

			public void setZl(String zl) {
				this.zl = zl;
			}

			public String getReceive() {
				return receive;
			}

			public void setReceive(String receive) {
				this.receive = receive;
			}

			public ArrayList<String> getKeyword() {
				return keyword;
			}

			public void setKeyword(ArrayList<String> keyword) {
				this.keyword = keyword;
			}

			public String getIsdel() {
				return isdel;
			}

			public void setIsdel(String isdel) {
				this.isdel = isdel;
			}
			public int getYZ() {
				return yz;
			}

			public void setYZ(int yz) {
				this.yz = yz;
			}

		}


}
