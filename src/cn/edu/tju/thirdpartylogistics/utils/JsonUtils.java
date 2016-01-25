package cn.edu.tju.thirdpartylogistics.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class JsonUtils {

	public static Map<String, String> parseUserLoginResult(String res, String cookie) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cookie", cookie);
		try {
			JSONObject json = new JSONObject(res);
			String status = json.optString("status");
			map.put("status", status);
			if ("0".equals(status) && !TextUtils.isEmpty(json.optString("data"))) {
				JSONObject jobj = new JSONObject(json.optString("data"));
				String roleType = jobj.optString("roleType");
				map.put("roleType", roleType);
			} else {
				map.put("roleType", null);
			}
			return map;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> parseScanCodeResult(String res) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			JSONObject json = new JSONObject(res);
			String status = json.optString("status");
			map.put("status", status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}

}
