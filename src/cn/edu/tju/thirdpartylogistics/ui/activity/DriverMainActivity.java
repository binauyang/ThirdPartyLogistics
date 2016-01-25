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


public class DriverMainActivity extends Activity implements OnClickListener{
	
	private int mScanCodeType =  0;
	
	private Button mInportButton;
	private Button mOutportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        initView();
    }

    private void initView() {
    	mInportButton = (Button) findViewById(R.id.btn_in_port_driver);
    	mOutportButton = (Button) findViewById(R.id.btn_out_port_driver);
    	mInportButton.setOnClickListener(this);
    	mOutportButton.setOnClickListener(this);
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
		case R.id.btn_in_port_driver:
			mScanCodeType = ScanCodeType.SCAN_TYPE_IN_PORT;
			break;
		case R.id.btn_out_port_driver:
			mScanCodeType = ScanCodeType.SCAN_TYPE_OUT_PORT;
			break;
		default:
			break;
		}
		jumpToScanCodeActivity();
	}
	
	private void jumpToScanCodeActivity() {
		Intent intent = new Intent(DriverMainActivity.this, CaptureActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(CaptureActivity.SCAN_CODE_TYPE, mScanCodeType);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
