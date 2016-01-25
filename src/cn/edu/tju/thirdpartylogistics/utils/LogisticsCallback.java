package cn.edu.tju.thirdpartylogistics.utils;

public class LogisticsCallback {
	public static interface ReloginCallback {
		public void onReloginSuccess(int type);

		public void onReloginError(int code);
	}

	public static interface LoginCallback {
		public void onLoginSuccess(int type);

		public void onLoginError(int code);
	}

	public static interface LogoutCallback {
		public void onLogoutSuccess();

		public void onLogoutError(int code);
	}
	
	public static interface ScanCodeCallback {
		public void onScanCodeSuccess();

		public void onScanCodeError(int code);
	}
}
