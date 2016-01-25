package cn.edu.tju.thirdpartylogistics.utils;

import java.util.Map;

import android.content.Context;
import android.os.AsyncTask;
import cn.edu.tju.thirdpartylogistics.http.HttpActions;
import cn.edu.tju.thirdpartylogistics.listener.OnLoginListener;
import cn.edu.tju.thirdpartylogistics.listener.OnReloginListener;
import cn.edu.tju.thirdpartylogistics.service.conn.ConnState;

public class LogisticsLogin {
	
	public static final int LOGIN_ROLE_SHIPPING_POINT = 1;
	public static final int LOGIN_ROLE_DRIVER = 2;
	
	public static final int RESULT_OK = 0;
	public static final int RESULT_TIMEOUT = 2;
	public static final int RESULT_AUTH_FAILED = 1;
	public static final int RESULT_STATE_ERROR = 3;

	private static LogisticsLogin instance = new LogisticsLogin();
	private static Context mContext;
	private String mUsername;
	private String mPassword;
	private Map<String, String> mResultMap;

	public static LogisticsLogin getInstance() {
		return instance;
	}

	public static void init(Context context) {
		mContext = context;
	}

	public void login(String username, String password, OnLoginListener listener) {
		this.mUsername = username;
		this.mPassword = password;
		this.loginTask(listener);
	}

	public void relogin(OnReloginListener listener) {
		new ReloginTask(listener).execute();
	}
//	
//	public void logout(OnLogoutListener listener) {
//		this.mPublicLogoutLestener = listener;
//		new LogoutTask().execute();
//	}

//	private static String genResource(Context context) {
//		try {
//			PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
//			String device = !HCTools.isTablet(context) ? "phone" : "pad";
//			String resource = device + "-android-" + info.versionCode + "-" + android.os.Build.MODEL;
//			return resource;
//		} catch (NameNotFoundException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}

	private void loginTask(final OnLoginListener listener) {
		new AsyncTask<Void, Void, Integer>() {
			@Override
			protected Integer doInBackground(Void... params) {
				if (!ConnState.getInstance().isInitState()) return RESULT_STATE_ERROR;
				mResultMap = HttpActions.login(mUsername, mPassword);
				return Integer.parseInt(mResultMap.get("status"));
			}

			@Override
			protected void onPostExecute(Integer resultCode) {
				switch (resultCode) {
				case RESULT_OK:
					L.d("Login Success", "");
					ConnState.getInstance().onLoginSuccess(mUsername, mPassword, mResultMap.get("cookie"));
					listener.onLoginSuccess(Integer.parseInt(mResultMap.get("roleType")));
					break;
				case RESULT_TIMEOUT:
					L.d("Login Timeout", "");
					listener.onLoginError(RESULT_TIMEOUT);
					break;
				case RESULT_AUTH_FAILED:
					L.d("Login Failed: AUTH", "");
					listener.onLoginError(RESULT_AUTH_FAILED);
					break;
				case RESULT_STATE_ERROR:
					L.d("Login Failed: STATE", "");
					listener.onLoginError(RESULT_STATE_ERROR);
					break;
				}
			}
		}.execute();
	}

	private class ReloginTask extends AsyncTask<Void, Void, Integer> {
		private OnReloginListener mPublicReloginLestener;

		protected ReloginTask(OnReloginListener listener) {
			mPublicReloginLestener = listener;
		}

		@Override
		protected Integer doInBackground(Void... params) {
			if (ConnState.getInstance().isInitState()) return RESULT_STATE_ERROR;
			L.d("Relogin", "Username: " + TPLPrefUtils.getUsername(mContext)/* + " Resource: " + mResource*/);
			String username = TPLPrefUtils.getUsername(mContext);
			String password = TPLPrefUtils.getPassword(mContext);
			
			mResultMap = HttpActions.login(username, password);
			return Integer.parseInt(mResultMap.get("status"));
		}
		
		@Override
		protected void onPostExecute(Integer resultCode) {
			switch (resultCode) {
			case RESULT_OK:
				L.d("Relogin Success", "");
				mPublicReloginLestener.onReloginSuccess(Integer.parseInt(mResultMap.get("roleType")));
				break;
			case RESULT_TIMEOUT:
				L.d("Relogin Timeout", "");
				mPublicReloginLestener.onReloginError(RESULT_TIMEOUT);
				break;
			case RESULT_AUTH_FAILED:
				L.d("Relogin Failed: AUTH", "");
				mPublicReloginLestener.onReloginError(RESULT_AUTH_FAILED);
				break;
			case RESULT_STATE_ERROR:
				L.d("Relogin Failed: STATE", "");
				mPublicReloginLestener.onReloginError(RESULT_STATE_ERROR);
				break;
			}
		}
	}
//
//	private class LogoutTask extends AsyncTask<Void, Void, Integer> {
//		@Override
//		protected Integer doInBackground(Void... params) {
//			if (ConnState.getInstance().getState() == ConnState.STATE_INIT) {
//				return RESULT_STATE_ERROR;
//			} else {
//				L.d("Login", "Account: " + mAccount + " Resource: " + mResource);
//				Xmpp.getInstance().logout();
//				return RESULT_OK;
//			}
//		}
//		
//		@Override
//		protected void onPostExecute(Integer resultCode) {
//			switch (resultCode) {
//			case RESULT_OK:
//				L.d("Logout Success", "");
//				mPublicLogoutLestener.onLogout();
//				break;
//			case RESULT_STATE_ERROR:
//				L.d("Logout Failed: STATE", "");
//				mPublicLogoutLestener.onLogoutError(RESULT_STATE_ERROR);
//				break;
//			}
//		}
//	}

}
