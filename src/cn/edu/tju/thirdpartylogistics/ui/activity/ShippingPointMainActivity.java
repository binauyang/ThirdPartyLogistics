package cn.edu.tju.thirdpartylogistics.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.edu.tju.thirdpartylogistics.constants.ScanCodeType;
import cn.edu.tju.thirtpartylogistics.R;


public class ShippingPointMainActivity extends Activity implements OnClickListener{
	
	private final static int SCANNIN_GREQUEST_CODE = 1;
	
	private int mScanCodeType = 0;
	
	private Button mInportButton;
	private Button mOutportButton;
	private Button mDeliverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_point_main);
        initView();
    }

    private void initView() {
    	mInportButton = (Button) findViewById(R.id.btn_in_port_shipping_point);
    	mOutportButton = (Button) findViewById(R.id.btn_out_port_shipping_point);
    	mDeliverButton = (Button) findViewById(R.id.btn_deliver_shipping_point);
    	mInportButton.setOnClickListener(this);
    	mOutportButton.setOnClickListener(this);
    	mDeliverButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_in_port_shipping_point:
			mScanCodeType = ScanCodeType.SCAN_TYPE_IN_PORT;
			break;
		case R.id.btn_out_port_shipping_point:
			mScanCodeType = ScanCodeType.SCAN_TYPE_OUT_PORT;
			break;
		case R.id.btn_deliver_shipping_point:
			mScanCodeType = ScanCodeType.SCAN_TYPE_DELIVER;
			break;
		default:
			break;
		}
		jumpToScanCodeActivity();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == RESULT_OK) {
				
			}
			break;

		default:
			break;
		}
	}
	
	private void jumpToScanCodeActivity() {
		Intent intent = new Intent(ShippingPointMainActivity.this, CaptureActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(CaptureActivity.SCAN_CODE_TYPE, mScanCodeType);
		intent.putExtras(bundle);
		startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
	}
}
