package cn.edu.tju.thirdpartylogistics.listener;

public interface OnReloginListener {
	public void onReloginSuccess(int type);
	public void onReloginError(int code);
}
