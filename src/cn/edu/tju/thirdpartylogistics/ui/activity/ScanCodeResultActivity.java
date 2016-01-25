package cn.edu.tju.thirdpartylogistics.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.tju.thirdpartylogistics.constants.ScanCodeType;
import cn.edu.tju.thirtpartylogistics.R;

public class ScanCodeResultActivity extends Activity {
	
	private TextView mTitleTextView;
	private TextView mScanCodeResultTextView;
	private ImageView mImageView;
	private Button mConfirmButton;
	private TextView mBackButton;
	
	private int mScanCodeType;
	private Boolean mScanCodeResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_code_result_success);
		getData();
		initView();
	}
	
	private void getData() {
		Bundle bundle = getIntent().getExtras();
		if (null != bundle) {
			mScanCodeType = bundle.getInt(ConfirmExpressIDActivity.BUNDLE_KEY_SCAN_CODE_TYPE);
			mScanCodeResult = bundle.getBoolean(ConfirmExpressIDActivity.BUNDLE_KEY_SCAN_CODE_RESULT);
		}
	}
	
	private void initView() {
		mTitleTextView = (TextView) findViewById(R.id.tv_title);
		mTitleTextView.setText(getString(R.string.scan_result));
		
		mScanCodeResultTextView = (TextView) findViewById(R.id.tv_scan_code_result);
		mImageView = (ImageView) findViewById(R.id.iv_scan_code_result);
		switch (mScanCodeType) {
		case ScanCodeType.SCAN_TYPE_IN_PORT:
			if (mScanCodeResult) {
				mImageView.setImageResource(R.drawable.scan_code_result_success);
				mScanCodeResultTextView.setText(R.string.in_port_scan_code_result_success);
			} else {
				mImageView.setImageResource(R.drawable.scan_code_result_failed);
				mScanCodeResultTextView.setText(R.string.in_port_scan_code_result_failed);
			}
			break;
		case ScanCodeType.SCAN_TYPE_OUT_PORT:
			if (mScanCodeResult) {
				mImageView.setImageResource(R.drawable.scan_code_result_success);
				mScanCodeResultTextView.setText(R.string.out_port_scan_code_result_success);
			} else {
				mImageView.setImageResource(R.drawable.scan_code_result_failed);
				mScanCodeResultTextView.setText(R.string.out_port_scan_code_result_failed);
			}
			break;
		case ScanCodeType.SCAN_TYPE_DELIVER:
			if (mScanCodeResult) {
				mImageView.setImageResource(R.drawable.scan_code_result_success);
				mScanCodeResultTextView.setText(R.string.deliver_scan_code_result_success);
			} else {
				mImageView.setImageResource(R.drawable.scan_code_result_failed);
				mScanCodeResultTextView.setText(R.string.deliver_scan_code_result_failed);
			}
			break;
		default:
			break;
		}
		
		mBackButton = (TextView) findViewById(R.id.tv_back);
		mBackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mConfirmButton = (Button) findViewById(R.id.btn_scan_code_result_confirm);
		mConfirmButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
