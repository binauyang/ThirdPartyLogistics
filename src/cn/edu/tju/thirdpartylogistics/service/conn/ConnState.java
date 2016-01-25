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

import android.content.Context;
import cn.edu.tju.thirdpartylogistics.utils.TPLPrefUtils;


public class ConnState implements ConnStateInterface {

	private static ConnState instance = new ConnState();
	private static Context context;

	public static void initConnState(Context context) {
		ConnState.context = context;
	}

	public static ConnState getInstance() {
		return instance;
	}

	public void setConnState(int state) {
		TPLPrefUtils.setConnState(context, state);
	}

	public int getConnState() {
		return TPLPrefUtils.getConnState(context);
	}

	public boolean isInitState() {
		return this.getConnState() == ConnState.STATE_INIT;
	}

	public boolean isValidState() {
		return this.getConnState() == ConnState.STATE_VALID;
	}

	public boolean isInvalidState() {
		return this.getConnState() == ConnState.STATE_INVALID;
	}

	@Override
	public void onLoginSuccess(String username, String password, String cookie) {
		this.setConnState(STATE_VALID);

		TPLPrefUtils.setUsername(context, username);
		TPLPrefUtils.setPassword(context, password);
		TPLPrefUtils.setCookie(context, cookie);
	}

	@Override
	public void onLoginTimeout() {
		this.setConnState(STATE_INIT);

//		BroadcastUtils.sendTo(context, Actions.USER_LOGIN_TIMEOUT);
	}

	@Override
	public void onLoginIncorrect() {
//		L.e("onLoginIncorrect");
		this.setConnState(STATE_INIT);

//		LogRecorder.save("onLoginIncorrect", "STATE:INIT");
//		BroadcastUtils.sendTo(context, Actions.USER_LOGIN_INCORRECT);
//		NotiManager.build(context).cleanNotify();
	}

	@Override
	public void onLogout() {
		this.setConnState(STATE_INIT);

//		LogRecorder.save("onLogout", "STATE:INIT");
//		BroadcastUtils.sendTo(context, Actions.USER_LOGOUT);
//		ActivityTaskManager.backToLogin(context);
//		NotiManager.build(context).cleanNotify();
	}

	@Override
	public void onReLoginIncorrect() {
//		L.e("onReLoginIncorrect");
		this.setConnState(STATE_INIT);

//		LogRecorder.save("onReLoginIncorrect", "STATE:INIT");
//		ActivityTaskManager.backToLogin(context);
//		NotiManager.build(context).cleanNotify();
	}

	@Override
	public void onReLoginTimeout() {
		switch (getState()) {
		case ConnState.STATE_INIT:
			break;
		case ConnState.STATE_READY:
		case ConnState.STATE_VALID:
		case ConnState.STATE_INVALID:
			this.setConnState(STATE_INVALID);
//			LogRecorder.save("onReLoginTimeout", "STATE:INVALID");
//			BroadcastUtils.sendTo(context, Actions.USER_LOGIN_TIMEOUT);
			break;
		}
	}

//	@Override
//	public void onPingSuccess(String pingId) {
//		L.t("end:   ===============[" + pingId + "]===============");
//		switch (getState()) {
//		case ConnState.STATE_READY:
//		case ConnState.STATE_INIT:
//			break;
//		case ConnState.STATE_VALID:
//		case ConnState.STATE_INVALID:
//			LogRecorder.save("Ping Success", pingId + " STATE:VALID");
//			this.setConnState(STATE_VALID);
//			break;
//		}
//	}
//
//	@Override
//	public void onPingTimeout(String reason) {
//		L.e("end:   ============[PingTimeout]============");
//		switch (getState()) {
//		case ConnState.STATE_READY:
//		case ConnState.STATE_INIT:
//			break;
//		case ConnState.STATE_VALID:
//		case ConnState.STATE_INVALID:
//			this.setConnState(STATE_INVALID);
//			LogRecorder.save("PingTimeout", reason + " STATE:INVALID");
//			break;
//		}
//	}
//
//	@Override
//	public void onMessageReceived() {
//		L.t("onMessageReceived");
//		int state = getState();
//		LogRecorder.save("接受消息状态", getStateContent(state));
//		switch (state) {
//		case ConnState.STATE_READY:
//		case ConnState.STATE_INIT:
//			break;
//		case ConnState.STATE_VALID:
//		case ConnState.STATE_INVALID:
//			this.setConnState(STATE_VALID);
//			break;
//		}
//	}
//
	@Override
	public int getState() {
		return this.getConnState();
	}
//
//	public static String getStateContent(int state) {
//		switch (state) {
//		case ConnState.STATE_INIT:
//			return "STATE:INIT";
//		case ConnState.STATE_READY:
//			return "STATE:READY";
//		case ConnState.STATE_VALID:
//			return "STATE:VALID";
//		case ConnState.STATE_INVALID:
//			return "STATE:INVALID";
//		default:
//			return "" + state;
//		}
//	}
}
