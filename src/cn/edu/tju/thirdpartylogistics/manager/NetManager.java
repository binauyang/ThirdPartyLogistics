package cn.edu.tju.thirdpartylogistics.manager;

import cn.edu.tju.thirdpartylogistics.utils.L;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

public class NetManager {
	
	private static WifiManager mWManager;
	private static NetworkInfo mInfo;
	private static ConnectivityManager mCManager;
	private static Context mContext;
	private WifiLock mWifiLock;
	private WifiInfo mWifiInfo;
	
	public NetManager(Context context){
		mContext = context;
		mCManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		mInfo = mCManager.getActiveNetworkInfo();
		mWManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
		mWifiInfo = mWManager.getConnectionInfo();
	}
	
	/**
	 * 	ConnectivityManager.TYPE_MOBILE, 
	 * 	ConnectivityManager.TYPE_WIFI, 
	 * 	ConnectivityManager.TYPE_WIMAX, 
	 * 	ConnectivityManager.TYPE_ETHERNET, 
	 * 	ConnectivityManager.TYPE_BLUETOOTH, 
	 * 	or other types defined by ConnectivityManager.
	 */
	public int getNetType(){
		return mInfo.getType();
	}
	
	public boolean isConnected(){
		if(mInfo != null){
			L.t("netword typename:" + mInfo.getTypeName());
			L.t("netword extrainfo:" + mInfo.getExtraInfo());
			L.t("netword reason:" + mInfo.getReason());
			L.t("netword subtypeName:" + mInfo.getSubtypeName());
			L.t("netword state:" + mInfo.getState().name());
			return mInfo.isConnected();
		}
		return false;
	}
	
	public void reset(){
		if(mWManager != null){
			mWManager.setWifiEnabled(false);
			mWManager.setWifiEnabled(true);
		}
	}
	
	public void takeWifiLock() {
        if (mWifiLock == null) {
            mWifiLock = mWManager.createWifiLock("iQiyi");
            mWifiLock.setReferenceCounted(false);
        }
        mWifiLock.acquire();
    }
	
	public String getCurrentIp(){
		int i = mWifiInfo.getIpAddress();
		return (i & 0xFF)
				+ "." + ((i >> 8) & 0xFF)
				+ "." + ((i >> 16) & 0xFF)
				+ "." + ((i >> 24) & 0xFF);
	}
}
