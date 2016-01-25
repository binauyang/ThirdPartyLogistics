package cn.edu.tju.thirdpartylogistics.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import cn.edu.tju.thirdpartylogistics.service.conn.ConnState;

public class TPLPrefUtils {

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String COOKIE = "cookie";
	private static final String CONN_STATE = "login_state";
	
	private static List<String> listKeys = new ArrayList<String>();

	public static void clean(Context context) {
		for (String key : listKeys) {
			PrefHelper.remove(context, key);
		}
		listKeys.clear();
	}

	public static int getConnState(Context context) {
		return PrefHelper.getInt(context, CONN_STATE, ConnState.STATE_INIT);
	}

	public static void setConnState(Context context, int state) {
		listKeys.add(CONN_STATE);
		PrefHelper.putInt(context, CONN_STATE, state);
	}

	/**
	 * 保存用户帐号到本地
	 * @param username 用户完整帐号
	 */
	public static void setUsername(Context context, String username) {
		PrefHelper.putString(context, USERNAME, username);
	}

	/**
	 * 获取用户帐号
	 * @return 返回用户帐号
	 */
	public static String getUsername(Context context) {
		return PrefHelper.getString(context, USERNAME, "");
	}
	
	/**
	 * 保存用户帐号到本地
	 * @param username 用户完整帐号
	 */
	public static void setPassword(Context context, String password) {
		PrefHelper.putString(context, PASSWORD, password);
	}

	/**
	 * 获取用户帐号
	 * @return 返回用户帐号
	 */
	public static String getPassword(Context context) {
		return PrefHelper.getString(context, PASSWORD, "");
	}
	
	/**
	 * 保存cookie到本地
	 * @param cookie
	 */
	public static void setCookie(Context context, String cookie) {
		PrefHelper.putString(context, COOKIE, cookie);
	}
	
	/**
	 * 获取cookie
	 * @return cookie
	 */
	public static String getCookie(Context context) {
		return PrefHelper.getString(context, COOKIE, "");
	}

}
