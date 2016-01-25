package cn.edu.tju.thirdpartylogistics.handler;

import android.content.Context;
import cn.edu.tju.thirdpartylogistics.listener.OnLoginListener;
import cn.edu.tju.thirdpartylogistics.listener.OnReloginListener;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsCallback;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsLogin;
import cn.edu.tju.thirdpartylogistics.utils.TPLPrefUtils;


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

	public static void login(final Context context, final String username, final String password, final LogisticsCallback.LoginCallback callback) {
		final OnLoginListener onLoginListener = new OnLoginListener() {
			@Override
			public void onLoginSuccess(int type) {
				loginInit(context, username, password);
				callback.onLoginSuccess(type);
			}

			@Override
			public void onLoginError(int code) {
				callback.onLoginError(code);
			}
		};
		new Thread(new Runnable() {
			@Override
			public void run() {
				LogisticsLogin.getInstance().login(username, password, onLoginListener);
			}
		}).start();
	}

	public static void relogin(final Context context, final LogisticsCallback.ReloginCallback callback) {
		final OnReloginListener onReloginListener = new OnReloginListener() {
			@Override
			public void onReloginSuccess(int type) {
				String account = TPLPrefUtils.getUsername(context);
				String authcookie = TPLPrefUtils.getPassword(context);
				loginInit(context, account, authcookie);
				callback.onReloginSuccess(type);
			}

			@Override
			public void onReloginError(int code) {
				callback.onReloginError(code);
			}
		};
		new Thread(new Runnable() {
			@Override
			public void run() {
				LogisticsLogin.getInstance().relogin(onReloginListener);
			}
		}).start();
	}
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
