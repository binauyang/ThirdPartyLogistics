package cn.edu.tju.thirdpartylogistics.http;

import java.util.Map;

import cn.edu.tju.thirdpartylogistics.constants.Apis;
import cn.edu.tju.thirdpartylogistics.constants.ScanCodeType;
import cn.edu.tju.thirdpartylogistics.utils.HttpUtils;
import cn.edu.tju.thirdpartylogistics.utils.JsonUtils;

import android.os.Bundle;

public class HttpActions {

	public static Map<String, String> login(String username, String password) {
		Bundle params = new Bundle();
		params.putString("userName", username);
		params.putString("passWord", password);
		String url = HttpUtils.appendParams(Apis.GET_LOGIN, params);

		String res = HttpUtils.doGetRequestForString(url, null);
		String cookie = HttpUtils.getCookie();
		return JsonUtils.parseUserLoginResult(res, cookie);
	}
	
	public static Map<String, String> scanCode(String expressId, int scanCodeType, String cookie) {
		Bundle params = new Bundle();
		params.putString("shipNo", expressId);
		String url = "";
		if (scanCodeType == ScanCodeType.SCAN_TYPE_IN_PORT) {
			url = HttpUtils.appendParams(Apis.IN_PORT_SCAN_QR_CODE, params);
		} else if (scanCodeType == ScanCodeType.SCAN_TYPE_OUT_PORT) {
			url = HttpUtils.appendParams(Apis.OUT_PORT_SCAN_QR_CODE, params);
		} else if (scanCodeType == ScanCodeType.SCAN_TYPE_DELIVER) {
			url = HttpUtils.appendParams(Apis.DELIVER_SCAN_QR_CODE, params);
		}
		
		String res = HttpUtils.doGetRequestForString(url, cookie);
		return JsonUtils.parseScanCodeResult(res);
	}

}
