package cn.edu.tju.thirdpartylogistics.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.text.TextUtils;
import cn.edu.tju.thirdpartylogistics.constants.Apis;

public class HttpUtils {
	
	private static String COOKIE = "";

	public static HttpEntity doGetRequest(String url, String cookie) {
		try {
			HttpGet request = new HttpGet(url);
			HttpClient client = (HttpClient) new DefaultHttpClient();
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10 * 1000);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
//			setRequestCookies(request, cookie);
			if (null != cookie) {
				request.setHeader("Cookie", "PHPSESSID=" + cookie);
			}
			
			HttpResponse res = client.execute(request);
			if (url.contains(Apis.GET_LOGIN)) {
				storeCookies(client);
			}

			int code = res.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				return entity;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getCookie() {
		return COOKIE;
	}
	
	private static void storeCookies(HttpClient client) {
		List<Cookie> cookies = ((AbstractHttpClient) client).getCookieStore().getCookies();
		if (cookies.size() > 0) {
			Cookie cookie = cookies.get(0);
			String cookieValue = cookie.getValue();
			if (!TextUtils.isEmpty(cookieValue)) {
				COOKIE = cookieValue;
			}
		}
	}
	
	private static void setRequestCookies(HttpMessage reqMsg, String cookie) {
//		if (!TextUtils.isEmpty(COOKIE)) {
//			reqMsg.setHeader("Cookie", "PHPSESSID=" + COOKIE);
//		}
		reqMsg.setHeader("Cookie", cookie);
	}

	public static String doGetRequestForString(String url, String cookie) {
		HttpEntity entity = doGetRequest(url, cookie);
		if (entity == null) return null;
		try {
			return EntityUtils.toString(entity, HTTP.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//
//	public static String doPutUpload(String abUrl, String abPath, Bundle header) {
//		if (TextUtils.isEmpty(abUrl) || TextUtils.isEmpty(abPath)) {
//			return null;
//		}
//
//		try {
//			L.t("PUT request: " + abUrl);
//			HttpPut request = new HttpPut(abUrl);
//			for (String key : header.keySet()) {
//				request.addHeader(key, header.getString(key));
//			}
//
//			HttpClient client = (HttpClient) new DefaultHttpClient();
//			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10 * 1000);
//			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
//			File file = new File(abPath);
//			FileEntity entity = new FileEntity(file, MIME.ENC_BINARY);
//			request.setEntity(entity);
//
//			HttpResponse res = client.execute(request);
//			int code = res.getStatusLine().getStatusCode();
//			L.t("PUT result code: " + code);
//			L.e(EntityUtils.toString(res.getEntity()));
//			if (code == HttpStatus.SC_CREATED) {
//				return abUrl;
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public static HttpEntity doPostUpload(String url, File file, HttpEntity entity) {
//		HttpPost request = new HttpPost(url);
//		HttpClient client = new DefaultHttpClient();
//
//		request.setEntity(entity);
//		HttpResponse response;
//		try {
//			response = client.execute(request);
//			int code = response.getStatusLine().getStatusCode();
//			L.t("ï¿½Ï´ï¿½×´Ì¬ï¿½ë£º" + code);
//			return response.getEntity();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public static Bundle createHeader(String key, String value) {
		Bundle bundle = new Bundle();
		bundle.putString(key, value);
		return bundle;
	}

	public static String appendParams(String url, Bundle extras) {
		if (!extras.isEmpty() && !url.endsWith("?")) {
			url += "?";
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : extras.keySet()) {
			params.add(new BasicNameValuePair(key, extras.getString(key)));
		}
		return url + URLEncodedUtils.format(params, HTTP.UTF_8);
	}

	public static String appendUrl(String host, String[] keys, String[] values) {
		StringBuilder sb = new StringBuilder(host + "?");
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i]);
			sb.append("=");
			sb.append(values[i]);
			sb.append(i != keys.length - 1 ? "&" : "");
		}
		return sb.toString();
	}
}
