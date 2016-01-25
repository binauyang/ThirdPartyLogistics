package cn.edu.tju.thirdpartylogistics.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.edu.tju.thirdpartylogistics.handler.LoginHandler;
import cn.edu.tju.thirdpartylogistics.listener.OnReloginListener;
import cn.edu.tju.thirdpartylogistics.service.conn.ConnState;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsCallback;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsLogin;
import cn.edu.tju.thirtpartylogistics.R;

public class WelcomeActivity extends Activity implements OnReloginListener, LogisticsCallback.ReloginCallback {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		checkConn();
	}
	
	private void checkConn() {
		if (ConnState.getInstance().isInitState()) {
			this.startActivity(new Intent(this, LoginActivity.class));
			finish();
		} else {
			ConnState.getInstance().setConnState(ConnState.STATE_READY);
			LoginHandler.relogin(this, this);
		}
	}

	@Override
	public void onReloginSuccess(int roleType) {
		jumpToMainActivity(roleType);
		finish();
	}

	@Override
	public void onReloginError(int code) {
		switch (code) {
		case LogisticsLogin.RESULT_TIMEOUT:
//			startActivity(new Intent(this, HomeActivity.class));
			break;
		case LogisticsLogin.RESULT_STATE_ERROR:
		case LogisticsLogin.RESULT_AUTH_FAILED:
			startActivity(new Intent(this, LoginActivity.class));
			break;
		}
		finish();
	}
	
	private void jumpToMainActivity(int roleType) {
		Intent intent;
		if (roleType == LogisticsLogin.LOGIN_ROLE_SHIPPING_POINT) {
			intent = new Intent(this, ShippingPointMainActivity.class);
		} else {
			intent = new Intent(this, DriverMainActivity.class);
		}
		startActivity(intent);
	}

}
