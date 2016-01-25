/*******************************************************
 * Copyright (C) 2014 iQIYI.COM - All Rights Reserved
 * 
 * This file is part of QiyiWeMedia
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * 
 * Author(s): Dan Yun <danyun@qiyi.com>
 * Date: 2015年1月30日
 * 
 *******************************************************/
package cn.edu.tju.thirdpartylogistics.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * @author danyun
 *
 */
public class PrefHelper {

	public static boolean getBoolean(Context context, String prefKey, boolean defaultValue) {
		return getSharedPreferences(context).getBoolean(prefKey, defaultValue);
	}

	public static float getFloat(Context context, String prefKey, float defaultValue) {
		return getSharedPreferences(context).getFloat(prefKey, defaultValue);
	}

	public static int getInt(Context context, String prefKey, int defaultValue) {
		return getSharedPreferences(context).getInt(prefKey, defaultValue);
	}

	public static long getLong(Context context, String prefKey, long defaultValue) {
		return getSharedPreferences(context).getLong(prefKey, defaultValue);
	}

	public static String getString(Context context, String prefKey, String defaultValue) {
		return getSharedPreferences(context).getString(prefKey, defaultValue);
	}

	public static void putBoolean(Context context, String prefKey, boolean value) {
		getSharedPreferences(context).edit().putBoolean(prefKey, value).commit();
	}

	public static void putFloat(Context context, String prefKey, float value) {
		getSharedPreferences(context).edit().putFloat(prefKey, value).commit();
	}

	public static void putInt(Context context, String prefKey, int value) {
		getSharedPreferences(context).edit().putInt(prefKey, value).commit();
	}

	public static void putLong(Context context, String prefKey, long value) {
		getSharedPreferences(context).edit().putLong(prefKey, value).commit();
	}

	public static void putString(Context context, String prefKey, String value) {
		getSharedPreferences(context).edit().putString(prefKey, value).commit();
	}

	public static void remove(Context context, String prefKey) {
		getSharedPreferences(context).edit().remove(prefKey).commit();
	}

	public static SharedPreferences getSharedPreferences(Context context) {
		return getSharedPreferences(context, null);
	}

	public static SharedPreferences getSharedPreferences(Context context, String prefName) {
		if (TextUtils.isEmpty(prefName)) {
			return PreferenceManager.getDefaultSharedPreferences(context);
		} else {
			return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		}
	}

	public static void registerOnSharedPreferenceChangeListener(Context context, OnSharedPreferenceChangeListener listener) {
		getSharedPreferences(context).registerOnSharedPreferenceChangeListener(listener);
	}

	public static void unregisterOnSharedPreferenceChangeListener(Context context, OnSharedPreferenceChangeListener listener) {
		getSharedPreferences(context).unregisterOnSharedPreferenceChangeListener(listener);
	}

}
