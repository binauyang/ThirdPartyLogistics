package cn.edu.tju.thirdpartylogistics.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.edu.tju.thirdpartylogistics.constants.ScanCodeType;
import cn.edu.tju.thirdpartylogistics.handler.ScanCodeHandler;
import cn.edu.tju.thirdpartylogistics.manager.NetManager;
import cn.edu.tju.thirdpartylogistics.ui.model.ConnectionDialog;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsCallback;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsLogin;
import cn.edu.tju.thirdpartylogistics.utils.LogisticsScanCode;
import cn.edu.tju.thirdpartylogistics.utils.T;
import cn.edu.tju.thirtpartylogistics.R;

public class ConfirmExpressIDActivity extends Activity implements OnClickListener {

	public static String BUNDLE_KEY_EXPRESS_ID = "expressId";
	public static String BUNDLE_KEY_SCAN_CODE_TYPE = "scanCodeType";
	public static String BUNDLE_KEY_SCAN_CODE_RESULT = "scanCodeResult";
	
	private String mExpressId = "";
	private int mScanCodeType = 0;
	private String mScanCodeTypeStr = "";

	private TextView mTitleTextView;
	private TextView mConfirmInfoTextView;
	private Button mConfirmButton;
	private Button mCancelButton;
	private TextView mBackButton;
	private ConnectionDialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getData();
		setContentView(R.layout.activity_confirm_express);
		initView();
	}

	private void getData() {
		Bundle bundle = getIntent().getExtras();
		if (null != bundle) {
			mExpressId = bundle.getString(BUNDLE_KEY_EXPRESS_ID);
			mScanCodeType = bundle.getInt(BUNDLE_KEY_SCAN_CODE_TYPE);
			if (ScanCodeType.SCAN_TYPE_IN_PORT == mScanCodeType) {
				mScanCodeTypeStr = getString(R.string.in_port_scan_code);
			} else if (ScanCodeType.SCAN_TYPE_OUT_PORT == mScanCodeType) {
				mScanCodeTypeStr = getString(R.string.out_port_scan_code);
			} else if (ScanCodeType.SCAN_TYPE_DELIVER == mScanCodeType) {
				mScanCodeTypeStr = getString(R.string.deliver_scan_code);
			}
		}
	}

	private void initView() {
		mTitleTextView = (TextView) findViewById(R.id.tv_title);
		mTitleTextView.setText(getString(R.string.confirm_express_id));
		mConfirmInfoTextView = (TextView) findViewById(R.id.tv_confirm_express_info);
		String content = genConfirmInfo();
		int start = content.indexOf("：") + 1;
		int end = start + mExpressId.length();
		SpannableStringBuilder style=new SpannableStringBuilder(content);
		style.setSpan(new ForegroundColorSpan(Color.RED), start, end,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
		mConfirmInfoTextView.setText(style);
		mConfirmButton = (Button) findViewById(R.id.btn_confirm_express);
		mConfirmButton.setOnClickListener(this);
		mCancelButton = (Button) findViewById(R.id.btn_cancel_express);
		mCancelButton.setOnClickListener(this);
		mBackButton = (TextView) findViewById(R.id.tv_back);
		mBackButton.setOnClickListener(this);
	}

	private String genConfirmInfo() {
		String info = "";
		info = getString(R.string.confirm_info_text_1) + mExpressId + getString(R.string.confirm_info_text_2)
				+ mScanCodeTypeStr + getString(R.string.confirm_info_text_3);
		return info;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_confirm_express:
			clickConfirm();
			break;
		case R.id.btn_cancel_express:
			finish();
			break;
		case R.id.tv_back:
			finish();
			break;
		default:
			break;
		}
	}
	
	private void clickConfirm() {
		boolean isNetWork = new NetManager(this).isConnected();
		if (!isNetWork) {
			T.s(this, getString(R.string.login_text_network_not_connected));
			return;
		}
		mDialog = ConnectionDialog.build(this);
		mDialog.connectShow(getString(R.string.scan_dialog_is_processing));
		ScanCodeHandler.scanCode(this, mExpressId, mScanCodeType, new LogisticsCallback.ScanCodeCallback() {
			@Override
			public void onScanCodeSuccess() {
				mDialog.dismiss();
				jumpToScanCodeResultActivity(true);
				finish();
			}

			@Override
			public void onScanCodeError(int code) {
				mDialog.dismiss();
				String reason = null;
				switch (code) {
				case LogisticsScanCode.RESULT_TIMEOUT:
//					 reason = getString(R.string.login_text_login_timeout);
					break;
				case LogisticsLogin.RESULT_STATE_ERROR:
					// reason =
					// getString(R.string.login_text_login_state_error);
					break;
				}
				jumpToScanCodeResultActivity(false);
				T.s(getApplicationContext(), getString(R.string.login_text_login_failed) + reason + "(" + code + ")");
			}
		});
	}
	
	private void jumpToScanCodeResultActivity(boolean success) {
		Intent intent = new Intent(ConfirmExpressIDActivity.this, ScanCodeResultActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(BUNDLE_KEY_SCAN_CODE_TYPE, mScanCodeType);
		bundle.putBoolean(BUNDLE_KEY_SCAN_CODE_RESULT, success);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
