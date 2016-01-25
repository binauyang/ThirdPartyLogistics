/*******************************************************
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * 
 * This file is part of Hotchat
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * 
 * Author(s): Dan Yun <danyun@qiyi.com>
 * Date: 2014��11��14��
 * 
 *******************************************************/
package cn.edu.tju.thirdpartylogistics.service.conn;

public interface ConnStateInterface {
	public static final int STATE_INIT = 6000;
	public static final int STATE_VALID = 6001;
	public static final int STATE_INVALID = 6002;
	public static final int STATE_READY = 6003;

	public void onLoginSuccess(String username, String password, String cookie);
	public void onLoginIncorrect();
	public void onLoginTimeout();
	public void onLogout();
	public void onReLoginIncorrect();
	public void onReLoginTimeout();
//	public void onPingSuccess(String pingId);
//	public void onPingTimeout(String reason);
//	public void onMessageReceived();
	public int getState();
}
