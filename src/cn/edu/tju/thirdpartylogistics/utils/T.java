package cn.edu.tju.thirdpartylogistics.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.tju.thirtpartylogistics.R;

public class T {
	private static Toast build(Context context, Object text) {
		Toast toast = new Toast(context);

		TextView view = new TextView(context);
		int wrap = ViewGroup.LayoutParams.WRAP_CONTENT;
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(wrap, wrap);
		view.setLayoutParams(params);
		view.setBackgroundResource(R.drawable.backgroud_toast);
		view.setGravity(Gravity.CENTER);
		view.setTextColor(Color.WHITE);
		view.setText(text.toString());
		view.setAlpha((float) 0.7);

		toast.setView(view);
		toast.setGravity(Gravity.CENTER, 0, 0);
		return toast;
	}

	public static void s(Context context, Object text) {
		Toast t = build(context, text);
		t.setDuration(Toast.LENGTH_SHORT);
		t.show();
	}

	public static void l(Context context, Object text) {
		Toast t = build(context, text);
		t.setDuration(Toast.LENGTH_LONG);
		t.show();
	}

	public static void offline(Context context) {
//		Toast t = build(context, context.getString(R.string.offline_toast));
//		t.setDuration(Toast.LENGTH_SHORT);
//		t.show();
	}
}