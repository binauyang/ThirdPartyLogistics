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


public class ShippingPointMainActivity extends Activity implements OnClickListener{
	
	private final static int SCANNIN_GREQUEST_CODE = 1;
	
	private static final int SCAN_TYPE_IN_PORT = 1;
	private static final int SCAN_TYPE_OUT_PORT = 2;
	private static final int SCAN_TYPE_DELIVER = 3;
	private static final String SCAN_TYPE_CODE = "SCAN_TYPE_CODE";
	
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
			jumpToScanCodeActivity(SCAN_TYPE_IN_PORT);
			break;
		case R.id.btn_out_port_shipping_point:
			jumpToScanCodeActivity(SCAN_TYPE_OUT_PORT);
			break;
		case R.id.btn_deliver_shipping_point:
			jumpToScanCodeActivity(SCAN_TYPE_DELIVER);
			break;
		default:
			break;
		}
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
	
	private void jumpToScanCodeActivity(int code) {
		Intent intent = new Intent(ShippingPointMainActivity.this, MipcaActivityCapture.class);
		startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
	}
}
