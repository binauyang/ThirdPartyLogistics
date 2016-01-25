package cn.edu.tju.thirdpartylogistics.handler;

import android.content.Context;
import cn.edu.tju.thirdpartylogistics.listener.OnScanCodeListener;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsCallback;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsScanCode;

public class ScanCodeHandler {

	public static void scanCode(final Context context, final String expressId, final int scanCodeType, final LogisticsCallback.ScanCodeCallback callback) {
		final OnScanCodeListener onScanCodeListener = new OnScanCodeListener() {
			@Override
			public void onScanCodeSuccess() {
				callback.onScanCodeSuccess();
			}

			@Override
			public void onScanCodeError(int code) {
				callback.onScanCodeError(code);
			}
		};
		new Thread(new Runnable() {
			@Override
			public void run() {
				LogisticsScanCode.getInstance().scanCode(expressId, scanCodeType, onScanCodeListener);
			}
		}).start();
	}
}
