package cn.edu.tju.thirdpartylogistics.utils;

public class LogisticsCallback {
	public static interface ReloginCallback {
		public void onReloginSuccess();

		public void onReloginError(int code);
	}

	public static interface LoginCallback {
		public void onLoginSuccess();

		public void onLoginError(int code);
	}

	public static interface LogoutCallback {
		public void onLogoutSuccess();

		public void onLogoutError(int code);
	}
}
