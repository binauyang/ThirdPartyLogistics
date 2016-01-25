package cn.edu.tju.thirdpartylogistics.ui.app;

import android.app.Application;
import cn.edu.tju.thirdpartylogistics.service.conn.ConnState;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsLogin;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsScanCode;

public class ThirdPartyLogisticsApplication extends Application {
	public static ThirdPartyLogisticsApplication instance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		init();
	}

	private void init() {
		ConnState.initConnState(this);
		LogisticsLogin.init(this);
		LogisticsScanCode.init(this);
	}
	
}
