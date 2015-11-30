package cn.edu.tju.thirdpartylogistics.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.edu.tju.thirtpartylogistics.R;


public class DriverMainActivity extends Activity implements OnClickListener{
	
	private static final int SCAN_TYPE_IN_PORT = 1;
	private static final int SCAN_TYPE_OUT_PORT = 2;
	private static final String SCAN_TYPE_CODE = "SCAN_TYPE_CODE";
	
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
			jumpToScanCodeActivity(SCAN_TYPE_IN_PORT);
			break;
		case R.id.btn_out_port_driver:
			jumpToScanCodeActivity(SCAN_TYPE_OUT_PORT);
			break;
		default:
			break;
		}
	}
	
	private void jumpToScanCodeActivity(int code) {
		Intent intent = new Intent(DriverMainActivity.this, MipcaActivityCapture.class);
		Bundle bundle = new Bundle();
		bundle.putInt(SCAN_TYPE_CODE, code);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
