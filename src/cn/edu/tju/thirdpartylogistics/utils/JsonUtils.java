package cn.edu.tju.thirdpartylogistics.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.text.TextUtils;


public class JsonUtils {

	private static final String VER_NAME = "v_name";
	private static final String VER_LOG = "v_log";
	private static final String VER_CODE = "v_code";
	private static final String VER_CODE_MIN = "v_code_min";
	private static final String VER_DB_CHANGED = "db_changed";

//	public static VersionInfo parseVersion(String json) {
//		try {
//			JSONObject jobj = new JSONObject(json);
//			VersionInfo vi = new VersionInfo();
//			vi.versionName = jobj.getString(VER_NAME);
//			vi.versionCode = jobj.optInt(VER_CODE, Integer.MIN_VALUE);
//			vi.minVersionCode = jobj.optInt(VER_CODE_MIN, Integer.MIN_VALUE);
//			vi.dbChanged = jobj.optBoolean(VER_DB_CHANGED, false);
//			vi.versionLog = jobj.getString(VER_LOG);
//			return vi;
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	public static String createWebcamMessage(int messageType) {
		JSONObject jobj = new JSONObject();
		try {
			jobj.put("messageType", messageType);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jobj.toString();
	}

	public static String createWebcamInfo(String networkType, String dssServerIp, int messageType, int sdpName) {
		JSONObject jobj = new JSONObject();
		try {
			jobj.put("messageType", messageType);
			jobj.put("sdk", android.os.Build.VERSION.SDK_INT);
			jobj.put("os", 1);
			jobj.put("model", android.os.Build.MODEL);
			jobj.put("networkType", networkType);
			jobj.put("dssServerIp", dssServerIp);
			jobj.put("sdpName", sdpName);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jobj.toString();
	}

//	public static WebcamEntity parseWebcamInfo(String json) {
//		WebcamEntity entity = new WebcamEntity();
//		try {
//			JSONObject jobj = new JSONObject(json);
//			entity.messageType = jobj.optInt("messageType");
//			entity.sdk = jobj.optInt("sdk");
//			entity.os = jobj.optInt("os");
//			entity.model = jobj.optString("model");
//			entity.networkType = jobj.optString("networkType");
//			entity.dssServerIp = jobj.optString("dssServerIp");
//			entity.sdpName = jobj.optInt("sdpName");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return entity;
//	}

	public static NameValuePair parseUserAuthcookie(String res) {
		try {
			JSONObject json = new JSONObject(res);
			String code = json.optString("code");
			if ("A00000".equals(code) && !TextUtils.isEmpty(json.optString("data"))) {
				JSONObject jobj = new JSONObject(json.optString("data"));
				JSONObject jInfo = jobj.optJSONObject("pps_vip_info");
				String userId = jInfo.optString("user_id");
				String authcookie = jobj.optString("authcookie");
				return new BasicNameValuePair(userId, authcookie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static NameValuePair parseQijuTripartite(String res) {
		try {
			JSONObject json = new JSONObject(res);
			String code = json.optString("code");
			if ("200".equals(code)) {
				JSONObject jobj = json.optJSONObject("data");
				String meetingId = jobj.optString("meetingid");
				String password = jobj.optString("passwd");
				return new BasicNameValuePair(meetingId, password);
			} else {
				L.e("[c][JSON][Qiju] »ñÈ¡Ææ¾ÛÈý·½»áÒéIDÊ§°Ü: " + res);
			}
		} catch (Exception e) {
			L.e("[c][JSON][Qiju] »ñÈ¡Ææ¾ÛÈý·½»áÒéIDÊ§°Ü: " + res);
			e.printStackTrace();
		}
		return null;
	}
	
	// add by ouyangbin
	public static List<String> parseLdapMessage(String res) {
		try {
			List<String> userAccountInfoList = new ArrayList<String>();
			JSONObject json;
			json = new JSONObject(res);
			String code = json.optString("code");
			if ("200".equals(code) && !TextUtils.isEmpty(json.optString("data"))) {
				JSONObject jData = new JSONObject(json.optString("data"));
				Iterator<?> itr = jData.keys();
				String uid = "";
				String chName = "";
				while (itr.hasNext()) {
					String mailboxPrefix = itr.next().toString();
					JSONObject obj = new JSONObject(jData.optString(mailboxPrefix));
					uid = obj.optString("uid");
					chName = obj.optString("chname");
					userAccountInfoList.add(uid + ":" + mailboxPrefix + ":" + chName);
				}
				return userAccountInfoList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// add by ouyangbin
	public static List<String> parseLdapChNameMessage(String res) {
		try {
			List<String> chNameList = new ArrayList<String>();
			JSONObject json;
			json = new JSONObject(res);
			String code = json.optString("code");
			if ("200".equals(code) && !TextUtils.isEmpty(json.optString("data"))) {
				JSONObject jData = new JSONObject(json.optString("data"));
				Iterator<?> itr = jData.keys();
				String chName = "";
				while (itr.hasNext()) {
					String mailboxPrefix = itr.next().toString();
					JSONObject obj = new JSONObject(jData.optString(mailboxPrefix));
					chName = obj.optString("chname");
					chNameList.add(mailboxPrefix + ":" + chName);
				}
				return chNameList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// add by ouyangbin
	public static List<String> parseLdapAccountMessage(String res) {
		try {
			List<String> accountList = new ArrayList<String>();
			JSONObject json;
			json = new JSONObject(res);
			String code = json.optString("code");
			if ("200".equals(code) && !TextUtils.isEmpty(json.optString("data"))) {
				JSONObject jData = new JSONObject(json.optString("data"));
				Iterator<?> itr = jData.keys();
				while (itr.hasNext()) {
					String mailboxPrefix = itr.next().toString();
					JSONObject obj = new JSONObject(jData.optString(mailboxPrefix));
					String uid = obj.optString("uid");
					accountList.add(uid + ":" + mailboxPrefix);
				}
				return accountList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
