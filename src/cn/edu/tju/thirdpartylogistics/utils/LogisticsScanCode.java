package cn.edu.tju.thirdpartylogistics.utils;

import java.util.Map;
import android.content.Context;
import android.os.AsyncTask;
import cn.edu.tju.thirdpartylogistics.http.HttpActions;
import cn.edu.tju.thirdpartylogistics.listener.OnScanCodeListener;

public class LogisticsScanCode {
	
	public static final int LOGIN_ROLE_SHIPPING_POINT = 1;
	public static final int LOGIN_ROLE_DRIVER = 2;
	
	public static final int RESULT_OK = 0;
	public static final int RESULT_TIMEOUT = 2;
	public static final int RESULT_STATE_ERROR = 3;

	private static LogisticsScanCode instance = new LogisticsScanCode();
	private static Context mContext;
	private String mExpressId;
	private int mScanCodeType;
	private Map<String, String> mResultMap;

	public static LogisticsScanCode getInstance() {
		return instance;
	}

	public static void init(Context context) {
		mContext = context;
	}

	public void scanCode(String expressId, int scanCodeType, OnScanCodeListener listener) {
		this.mExpressId = expressId;
		this.mScanCodeType = scanCodeType;
		this.scanCodeTask(listener);
	}

	private void scanCodeTask(final OnScanCodeListener listener) {
		new AsyncTask<Void, Void, Integer>() {
			@Override
			protected Integer doInBackground(Void... params) {
				mResultMap = HttpActions.scanCode(mExpressId, mScanCodeType, TPLPrefUtils.getCookie(mContext));
				return Integer.parseInt(mResultMap.get("status"));
			}

			@Override
			protected void onPostExecute(Integer resultCode) {
				switch (resultCode) {
				case RESULT_OK:
					L.d("Scan Code Success", "");
//					ConnState.getInstance().onLoginSuccess(mUsername, mPassword);
					listener.onScanCodeSuccess();
					break;
				case RESULT_TIMEOUT:
					L.d("Scan Code Timeout", "");
					listener.onScanCodeError(RESULT_TIMEOUT);
					break;
				case RESULT_STATE_ERROR:
					L.d("Scan Code Failed: STATE", "");
					listener.onScanCodeError(RESULT_STATE_ERROR);
					break;
				}
			}
		}.execute();
	}

}
