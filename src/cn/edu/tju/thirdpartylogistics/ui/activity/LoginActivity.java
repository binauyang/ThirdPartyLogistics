package cn.edu.tju.thirdpartylogistics.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import cn.edu.tju.thirdpartylogistics.handler.LoginHandler;
import cn.edu.tju.thirdpartylogistics.manager.NetManager;
import cn.edu.tju.thirdpartylogistics.ui.model.ConnectionDialog;
import cn.edu.tju.thirdpartylogistics.utils.L;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsCallback;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsLogin;
import cn.edu.tju.thirdpartylogistics.utils.T;
import cn.edu.tju.thirtpartylogistics.R;

public class LoginActivity extends Activity implements OnClickListener {

	private EditText mEditUsername;
	private EditText mEditPassword;
	private Button mBtnLogin;
	private ImageButton mBtnClearAccount;
	private ImageButton mBtnClearPassword;
	private ConnectionDialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		initView();
	}

	private void initView() {
		mEditUsername = (EditText) this.findViewById(R.id.et_username);
		mEditPassword = (EditText) this.findViewById(R.id.et_password);
		mBtnLogin = (Button) this.findViewById(R.id.btn_login);
		mBtnClearAccount = (ImageButton) this.findViewById(R.id.ib_clear_username);
		mBtnClearPassword = (ImageButton) this.findViewById(R.id.ib_clear_password);
		mEditUsername.addTextChangedListener(mAccountWatcher);
		mEditPassword.addTextChangedListener(mPasswordWatcher);

		mBtnLogin.setOnClickListener(this);
		mBtnClearAccount.setOnClickListener(this);
		mBtnClearPassword.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private TextWatcher mAccountWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (s.length() == 0) {
				mBtnClearAccount.setVisibility(View.GONE);
			} else {
				mBtnClearAccount.setVisibility(View.VISIBLE);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};

	private TextWatcher mPasswordWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			mBtnClearPassword.setVisibility(start + count == 0 ? View.GONE : View.VISIBLE);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			clickLogin();
			break;
		case R.id.ib_clear_username:
			mEditUsername.setText("");
			break;
		case R.id.ib_clear_password:
			mEditPassword.setText("");
			break;
		default:
			break;
		}
	}

	private void clickLogin() {
		String account = mEditUsername.getText().toString().trim();
		final String password = mEditPassword.getText().toString().trim();

		if (TextUtils.isEmpty(password)) {
			T.l(this, getString(R.string.login_text_empty_password));
			return;
		}

		boolean isNetWork = new NetManager(this).isConnected();
		if (!isNetWork) {
			T.s(this, getString(R.string.login_text_network_not_connected));
			return;
		}

		final String username = account;
		L.t(getString(R.string.login_text_is_loginning) + username);
		mDialog = ConnectionDialog.build(this);
		mDialog.connectShow(getString(R.string.login_dialog_is_loginning));

		LoginHandler.login(this, username, password, new LogisticsCallback.LoginCallback() {
			@Override
			public void onLoginSuccess(int roleType) {
				mDialog.dismiss();
//				T.s(getApplicationContext(), getString(R.string.login_text_login_success));
				jumpToMainActivity(roleType);
				finish();
			}

			@Override
			public void onLoginError(int code) {
				mDialog.dismiss();
				String reason = null;
				switch (code) {
				case LogisticsLogin.RESULT_TIMEOUT:
					// reason = getString(R.string.login_text_login_timeout);
					break;
				case LogisticsLogin.RESULT_AUTH_FAILED:
					 reason = getString(R.string.login_text_login_auth_failed);
					break;
				case LogisticsLogin.RESULT_STATE_ERROR:
					// reason =
					// getString(R.string.login_text_login_state_error);
					break;
				}
				T.s(getApplicationContext(), getString(R.string.login_text_login_failed) + reason + "(" + code + ")");
			}
		});

	}

	// @Override
	// public void onCheckedChanged(RadioGroup group, int checkedId) {
	// if (checkedId == R.id.rb_role_shipping_point) {
	// mRoleType = LogisticsLogin.LOGIN_ROLE_SHIPPING_POINT;
	// } else {
	// mRoleType = LogisticsLogin.LOGIN_ROLE_DRIVER;
	// }
	// }

	private void jumpToMainActivity(int roleType) {
		Intent intent;
		if (roleType == LogisticsLogin.LOGIN_ROLE_SHIPPING_POINT) {
			intent = new Intent(LoginActivity.this, ShippingPointMainActivity.class);
		} else {
			intent = new Intent(LoginActivity.this, DriverMainActivity.class);
		}
		startActivity(intent);
	}
}
