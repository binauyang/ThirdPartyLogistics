package cn.edu.tju.thirdpartylogistics.handler;

import org.apache.http.NameValuePair;

import cn.edu.tju.thirdpartylogistics.http.HttpActions;
import cn.edu.tju.thirdpartylogistics.listener.OnLoginListener;
import cn.edu.tju.thirdpartylogistics.listener.OnLogoutListener;
import cn.edu.tju.thirdpartylogistics.utils.L;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsCallback;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsLogin;

import android.content.Context;


/**
 * @author yun
 */
public class LoginHandler {

	private static void loginInit(final Context context, final String account, final String authcookie) {
		new Thread(new Runnable() {
			@Override
			public void run() {
//				Avatars.initAvatars();
//				HCPrefUtils.setCName(context, BasicData.instance(context).getNameByAccount(HCPrefUtils.getUserName(context)));
//				HCHttpActions.repoAction(context, 0);
//				String whiteList = HttpActions.getWhiteList();
//				HCPrefUtils.setAdmin(context, whiteList.contains(HCTools.subNameByEmail(account)));
//				HCPrefUtils.setDebugAccount(context, whiteList.contains("{" + HCTools.subNameByEmail(account) + "}"));
//				SIPRegister.registerSIP(account, authcookie);
			}
		}).start();
	}

	public static void login(final Context context, final String account, final String password, final LogisticsCallback.LoginCallback callback) {
		final OnLoginListener onLoginListener = new OnLoginListener() {
			@Override
			public void onLoginSuccess() {
				loginInit(context, account, password);
				callback.onLoginSuccess();
			}

			@Override
			public void onLoginError(int code) {
				callback.onLoginError(code);
			}
		};
		new Thread(new Runnable() {
			@Override
			public void run() {
				NameValuePair pair = HttpActions.loginPassport(account, password);
				String authcookie = pair != null ? pair.getValue() : password;
				LogisticsLogin.getInstance().login(account, authcookie, onLoginListener);
			}
		}).start();
	}

//	public static void relogin(final Context context, final HCCallback.ReloginCallback callback) {
//		final OnReloginListener onReloginListener = new OnReloginListener() {
//			@Override
//			public void onReloginSuccess() {
//				String account = HCPrefUtils.getAccount(context);
//				String authcookie = HCPrefUtils.getAuthcookie(context);
//				loginInit(context, account, authcookie);
//				callback.onReloginSuccess();
//			}
//
//			@Override
//			public void onReloginError(int code) {
//				if (HCLogin.RESULT_TIMEOUT == code) Avatars.initAvatars();
//				callback.onReloginError(code);
//			}
//		};
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				HCLogin.getInstance().relogin(onReloginListener);
//			}
//		}).start();
//	}
//
//	public static void logout(final Context context, final HCCallback.LogoutCallback callback) {
//		final OnLogoutListener onLogoutListener = new OnLogoutListener() {
//			@Override
//			public void onLogoutError(int code) {
//				L.e("[c][Handler][Logout] failure: " + code);
//				callback.onLogoutError(code);
//			}
//			@Override
//			public void onLogout() {
//				L.i("[c][Handler][Logout] success!");
//				callback.onLogoutSuccess();
//			}
//		};
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
////				HCLogin.getInstance().logout(onLogoutListener);
////				SIPRegister.unRegisterSIP();
////				MultimediaFileOperate.getInstance(context.getApplicationContext()).userLogout();
////				NotiManager.getInstance().cleanNotify();
//			}
//		}).start();
//	}
}
