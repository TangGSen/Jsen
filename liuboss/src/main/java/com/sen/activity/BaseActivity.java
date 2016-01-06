package com.sen.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

	/**
	 * 记录所有活动的Activity 由于经常要增删所以LinkedList效率比较高
	 */
	private static final List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
	private static BaseActivity mForegroundActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		synchronized (mActivities) {
			mActivities.add(this);
		}
		init();
		initView();
		initPersenter();
		initActionBar();
	}

	protected void initPersenter() {
	}

	/**
	 * 初始化ActionBar
	 */
	protected void initActionBar() {
	}

	/**
	 * 初始化界面
	 */
	public abstract void initView();

	/**
	 * 初始化
	 */
	protected void init() {

	}



	@Override
	protected void onResume() {
		super.onResume();
		mForegroundActivity = this;
	}

	@Override
	protected void onPause() {
		super.onPause();
		mForegroundActivity = null;
	}

	/**
	 * 获取当前处于前台的activity
	 */
	public static BaseActivity getForegroundActivity() {
		return mForegroundActivity;
	}

	@Override
	public void finish() {
		synchronized (mActivities) {
			mActivities.remove(this);
		}
		super.finish();

	}

	/**
	 * 退出程序
	 */
	public void exitApp() {
		// 结束所有的Activity
		killAll();
		// 杀死进程
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	//杀死所有Activity
	private void killAll() {
		List<BaseActivity> copy;
		synchronized (mActivities) {
			copy = new ArrayList<BaseActivity>(mActivities);
		}
		for (BaseActivity activity : copy) {
			activity.finish();
		}
	}
}
