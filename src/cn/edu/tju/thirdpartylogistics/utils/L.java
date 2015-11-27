package cn.edu.tju.thirdpartylogistics.utils;

import android.util.Log;

public class L {
	private static final String TAG = "logistics";
	private static boolean logOn = true;

	public static void i(String s) {
		if (logOn)
			Log.i(TAG, s);
	}

	public static void i(String title, String content) {
		if (logOn)
			Log.i(TAG, "[" + title + "]::" + content);
	}

	@Deprecated
	public static void t(String tag, String content) {
		String classname = new Exception().getStackTrace()[1].getClassName();
		classname = classname.substring(classname.lastIndexOf(".") + 1);
		if (logOn)
			Log.i(classname, content);
	}

	public static void t(String content) {
		String classname = new Exception().getStackTrace()[1].getClassName();
		classname = classname.substring(classname.lastIndexOf(".") + 1);
		if (logOn)
			Log.i(classname, content);
	}

	public static void e(String content) {
		String classname = new Exception().getStackTrace()[1].getClassName();
		classname = classname.substring(classname.lastIndexOf(".") + 1);
		if (logOn)
			Log.e(classname, content);
	}

	public static void d(String title, String content) {
		if (logOn)
			Log.d("HOTCHAT", "[LOGISTICS][" + title + "] " + content);
	}

	public static void d(String content) {
		if (logOn)
			L.d("SDK", content);
	}
}
