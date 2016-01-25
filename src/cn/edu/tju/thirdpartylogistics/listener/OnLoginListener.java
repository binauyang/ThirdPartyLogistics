package cn.edu.tju.thirdpartylogistics.listener;

public interface OnLoginListener {

	public void onLoginSuccess(int type);
	public void onLoginError(int code);

}