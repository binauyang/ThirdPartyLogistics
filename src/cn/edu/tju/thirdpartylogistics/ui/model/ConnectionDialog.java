package cn.edu.tju.thirdpartylogistics.ui.model;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import cn.edu.tju.thirdpartylogistics.utils.L;
import cn.edu.tju.thirtpartylogistics.R;

public class ConnectionDialog {

private static ConnectionDialog mInstance;
private Dialog mDialog;
private TextView mTextHint;

protected void onCreate(Context context) {
	View view = LayoutInflater.from(context).inflate(R.layout.dialog_connection, null);
	mTextHint = (TextView) view.findViewById(R.id.tv_load_image);
	
	mDialog = new Dialog(context, R.style.ConnDialog);
	mDialog.setCancelable(false);
	mDialog.setContentView(view);
}

public static ConnectionDialog build(Context context){
	mInstance = new ConnectionDialog();
	mInstance.onCreate(context);
	return mInstance;
}

public synchronized void connectShow(String text){
	
	mTextHint.setText(text);
	
	if(!mDialog.isShowing()){
		mDialog.show();
		L.i("dialog show");
	}
}

public void dismiss(){
	if(mDialog.isShowing()){
		mDialog.cancel();
		L.i("dialog cancel");
	}
}
}
