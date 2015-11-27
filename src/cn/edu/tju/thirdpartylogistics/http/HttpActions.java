package cn.edu.tju.thirdpartylogistics.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tju.thirdpartylogistics.constants.Apis;
import cn.edu.tju.thirdpartylogistics.utils.HttpUtils;
import cn.edu.tju.thirdpartylogistics.utils.JsonUtils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

//import com.iqiyi.hcim.constants.HCConstants;
//import com.iqiyi.hcim.entity.UserEntity;
//import com.iqiyi.hcim.manager.SDKFiles;
//import com.iqiyi.hcim.utils.HCPrefUtils;
//import com.iqiyi.hcim.utils.L;
//import com.iqiyi.hcim.utils.StandardTimeUtils;
//import com.iqiyi.hcim.utils.http.Downloader;
//import com.iqiyi.hcim.utils.http.HttpUtils;
//import com.iqiyi.hcim.utils.json.HCJsonUtils;
//import com.iqiyi.hotchat.constants.FileURLs;
//import com.iqiyi.hotchat.sip.SIPManager;
//import com.iqiyi.hotchat.utils.JsonUtils;

public class HttpActions {

	public static NameValuePair loginPassport(String username, String password) {
		Bundle params = new Bundle();
		params.putString("account", username);
		params.putString("passwd", password);
		String url = HttpUtils.appendParams(Apis.GET_LOGIN, params);

		String res = HttpUtils.doGetRequestForString(url);
		return JsonUtils.parseUserAuthcookie(res);
	}

//	private static String putFileByToken(String url, String path) {
//		Bundle header = HttpUtils.createHeader("X-Auth-Token: ", "c38bcd05c19a4ec7b7f8d0c58b110aac");
//		return HttpUtils.doPutUpload(url, path, header);
//	}
//
//	public static byte[] getAudio(String url) {
//		HttpEntity entity = HttpUtils.doGetRequest(url);
//		try {
//			if (entity == null) {
//				Thread.sleep(2000);
//				entity = HttpUtils.doGetRequest(url);
//			}
//			return entity == null ? null : EntityUtils.toByteArray(entity);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static byte[] getImage(String url) {
//		HttpEntity entity = HttpUtils.doGetRequest(url);
//		try {
//			return entity == null ? null : EntityUtils.toByteArray(entity);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static long getStandardTime(String url) {
//		HttpEntity entity = HttpUtils.doGetRequest(url);
//		try {
//			String json = entity == null ? null : EntityUtils.toString(entity);
//			long time = HCJsonUtils.getStandardTime(json);
//			return time;
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	public static InputStream getImageStream(String url) {
//		HttpEntity entity = HttpUtils.doGetRequest(url);
//		try {
//			return entity == null ? null : entity.getContent();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static String getUpdateInfo(String updateUrl) {
//		HttpEntity entity = HttpUtils.doGetRequest(updateUrl);
//		if (entity == null) {
//			return "";
//		}
//		try {
//			return EntityUtils.toString(entity, "utf-8");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//
//	public static String uploadImage(String path) {
//		String imageUrl = "";
//		File file = new File(path);
//		L.t("ÕýÔÚÉÏ´«Í¼Æ¬£¬Â·¾¶Îª£º" + path);
//		L.t("ÕýÔÚÉÏ´«Í¼Æ¬£¬´óÐ¡Îª£º" + file.length() / 1024 + "k");
//
//		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//		builder.addBinaryBody("img", file);
//		builder.addTextBody("authcookie", HCConstants.AUTHCOOKIE);
//		builder.addTextBody("type", "1");
//		HttpEntity entity = builder.build();
//
//		HttpEntity res = HttpUtils.doPostUpload(HCConstants.SERVER_IMAGE, file, entity);
//		try {
//			imageUrl = res == null ? "" : EntityUtils.toString(res, HTTP.UTF_8);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return imageUrl;
//	}
//
//	public static String uploadAudio(String abUrl, String abPath) {
//		if (TextUtils.isEmpty(abPath)) {
//			return null;
//		}
//		String res = putFileByToken(abUrl, abPath);
//		return res;
//	}
//
//	public static String uploadLogInfo(String parentPath, String filename) {
//		if (TextUtils.isEmpty(parentPath) || TextUtils.isEmpty(filename)) {
//			return null;
//		}
//		String filePath = parentPath + File.separator + filename;
//		String uploadUrl = HCConstants.SERVER_CRASHLOG + filename;
//		String res = putFileByToken(uploadUrl, filePath);
//		return res;
//	}
//
//	public static String getWhiteList() {
//		String updateUrl = "http://" + HCConstants.DOMAIN_DL + "/reliaowhitelist";
//		HttpEntity entity = HttpUtils.doGetRequest(updateUrl);
//		if (entity == null)
//			return "";
//		try {
//			return EntityUtils.toString(entity, "utf-8");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//
//	public static String getUANetworkType(String url) {
//		L.t("[kun isp a0]");
//		HttpEntity httpEntity = HttpUtils.doGetRequest(url);
//		L.t("[kun isp a1] httpEntity = " + httpEntity);
//		String httpEntityStr = null;
//		if (httpEntity == null) {
//			L.e("[kun isp a1.1]");
//			return "";
//		}
//		try {
//			httpEntityStr = EntityUtils.toString(httpEntity, "utf-8");
//			/*
//			 * Because Dss server doesn't return the standard json, need
//			 * pre-handle it here
//			 */
//			httpEntityStr = httpEntityStr.substring(19);
//			L.t("\n[kun isp a1.11] httpEntityStr is " + httpEntityStr);
//		} catch (ParseException e) {
//			e.printStackTrace();
//			L.e("[kun isp a1.2]");
//			return "";
//		} catch (IOException e) {
//			e.printStackTrace();
//			L.e("[kun isp a1.3]");
//			return "";
//		}
//		if (null != httpEntityStr) {
//			JSONObject jobj;
//			try {
//				jobj = new JSONObject(httpEntityStr);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				L.e("[kun isp a1.4]");
//				return "";
//			}
//			L.t("\n[kun isp a2] data is " + jobj.optString("data"));
//			String dataStr = jobj.optString("data");
//			try {
//				jobj = new JSONObject(dataStr);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			L.t("\n[kun isp a3] isp is " + jobj.optString("isp"));
//			return jobj.optString("isp");
//		} else {
//			L.e("\nSomething wrong here! should not come here!");
//			return "";
//		}
//	}
//
//	public static class DssServerIPBundle {
//		public String dssGlobalIP;
//		public String dssProxyIp;
//	}
//
//	public static DssServerIPBundle getDssServerIpBundle(String url) {
//		DssServerIPBundle retDssServerIPBundle = new DssServerIPBundle();
//		HttpEntity httpEntity = HttpUtils.doGetRequest(url);
//		L.t("[kun isp c1] httpEntity = " + httpEntity);
//		String httpEntityStr = null;
//		if (httpEntity == null) {
//			L.e("[kun isp c1.1]");
//			return null;
//		}
//		try {
//			httpEntityStr = EntityUtils.toString(httpEntity, "utf-8");
//			L.t("[kun isp c2] httpEntityStr = " + httpEntityStr);
//		} catch (ParseException e) {
//			e.printStackTrace();
//			L.e("[kun isp c3]");
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//			L.e("[kun isp c4]");
//			return null;
//		}
//		// parse httpEntityStr and fill DssServerIPBundle
//		switch (url) {
//		case SIPManager.T2T_URL:
//		case SIPManager.U2U_URL:
//			retDssServerIPBundle.dssGlobalIP = httpEntityStr.substring(httpEntityStr.lastIndexOf("GlobalIP:") + 9);
//			L.t("[kun isp c5] retDssServerIPBundle.dssGlobalIP = " + retDssServerIPBundle.dssGlobalIP);
//			retDssServerIPBundle.dssGlobalIP = retDssServerIPBundle.dssGlobalIP.replaceAll("[\n\r]", "");
//			retDssServerIPBundle.dssProxyIp = "0.0.0.0";
//			L.t("[kun isp c5.1] retDssServerIPBundle.dssGlobalIP = " + retDssServerIPBundle.dssGlobalIP);
//			break;
//
//		case SIPManager.T2U_URL:
//			retDssServerIPBundle.dssGlobalIP = httpEntityStr.substring(httpEntityStr.lastIndexOf("GlobalIP:") + 9);
//			L.t("[kun isp c6] retDssServerIPBundle.dssGlobalIP = " + retDssServerIPBundle.dssGlobalIP);
//			retDssServerIPBundle.dssGlobalIP = retDssServerIPBundle.dssGlobalIP.replaceAll("[\n\r]", "");
//			L.t("[kun isp c6.1] retDssServerIPBundle.dssGlobalIP = " + retDssServerIPBundle.dssGlobalIP);
//			retDssServerIPBundle.dssProxyIp = httpEntityStr.substring(httpEntityStr.lastIndexOf("ProxyIP: ") + 9);
//			L.t("[kun isp c7] retDssServerIPBundle.dssProxyIp = " + retDssServerIPBundle.dssProxyIp);
//			retDssServerIPBundle.dssProxyIp = retDssServerIPBundle.dssProxyIp.replaceAll("[\n\r]", "");
//			L.t("[kun isp c7.1] retDssServerIPBundle.dssProxyIp = " + retDssServerIPBundle.dssProxyIp);
//			break;
//		}
//		return retDssServerIPBundle;
//	}
//
//	public static String checkLdapSize() {
//		HttpEntity entity = HttpUtils.doGetRequest(FileURLs.URL_LDAP_SIZE);
//		try {
//			return entity == null ? "" : EntityUtils.toString(entity);	
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//	
//	// add by ouyangbin
//	public static String requestLdapJsonString(Context context) {
//		Bundle bundle = new Bundle();
//		bundle.putString("uid", HCPrefUtils.getUid(context));
//		bundle.putString("date", String.valueOf(StandardTimeUtils.getStandardTime() / 1000 / 60));
//		bundle.putString("authcookie", HCPrefUtils.getAuthcookie(context));
//		String url = HttpUtils.appendParams(Apis.GET_LDAP, bundle);
//		String res = HttpUtils.doGetRequestForString(url);
//		return res;
//	}
//
//	public static boolean updateLocalLdapChNameList(Context context, String token) {
//		Bundle params = new Bundle();
//		params.putString("uid", HCPrefUtils.getUid(context));
//		params.putString("filename", "user_ch_name.json");
//		params.putString("passwd", token);
//		String url = HttpUtils.appendParams(Apis.GET_LADP_FILES, params);
//		return Downloader.downloadFile(url, SDKFiles.getInstance().getFile(SDKFiles.DIR_DATA, "user_ch_name.json").getAbsolutePath());
//	}
//	
//	public static boolean updateLdapAccountList(Context context, String token) {
//		Bundle params = new Bundle();
//		params.putString("uid", HCPrefUtils.getUid(context));
//		params.putString("filename", "ldapusersuid.txt");
//		params.putString("passwd", token);
//		String url = HttpUtils.appendParams(Apis.GET_LADP_FILES, params);
//		return Downloader.downloadFile(url, SDKFiles.getInstance().getFile(SDKFiles.DIR_DATA, "ldapusersuid.txt").getAbsolutePath());
//	}
//
//	public static boolean downloadAvatar(String url) {
//		if (TextUtils.isEmpty(url)) return false;
//		String filename = Uri.parse(url).getLastPathSegment();
//		String path = SDKFiles.getInstance().getFile(SDKFiles.DIR_AVATAR, filename).getAbsolutePath();
//		L.i("[c][http][action] downloadAvatar url: " + url);
//		return Downloader.downloadFile(url, path);
//	}
//
//	public static String parseHtmlTitle(String url) {
//		String charset = HTTP.UTF_8;
//		HttpEntity entity = HttpUtils.doGetRequest(url);
//		try {
//			if (entity == null) return null;
//			String res = EntityUtils.toString(entity, charset);
//
//			Matcher contentTypeMatcher = Pattern.compile("<meta(.+)charset=(.+)/>").matcher(res);
//			if (contentTypeMatcher.find()) {
//				charset = contentTypeMatcher.group(2);
//				charset = charset.contains("\"") ? charset.split("\"")[0] : HTTP.UTF_8;
//				L.i("<c><http> parseHtmlTitle contentTypeMatcher charset: " + charset);
//			}
//
//			if (charset.toUpperCase(Locale.getDefault()).startsWith("GB")) {
//				entity = HttpUtils.doGetRequest(url);
//				res = EntityUtils.toString(entity, charset);
//			}
//
//			Matcher titleMatcher = Pattern.compile("<title>(.+)</title>").matcher(res);
//			return titleMatcher.find() ? titleMatcher.group(1).trim() : "";
//		} catch (IllegalStateException | IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static NameValuePair createQijuTripartite(Context context) {
//		Bundle params = new Bundle();
//		params.putString("authcookie", HCPrefUtils.getAuthcookie(context));
//		String url = HttpUtils.appendParams(Apis.GET_QIJU_TRIPARTITE, params);
//		String res = HttpUtils.doGetRequestForString(url);
//		return JsonUtils.parseQijuTripartite(res);
//	}

}
