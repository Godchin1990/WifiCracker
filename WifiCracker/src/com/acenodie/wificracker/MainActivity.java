package com.acenodie.wificracker;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ListView;

import com.acenodie.adapter.WifiListAdapter;

public class MainActivity extends Activity {

	// 组件信息
	private ListView lv_wifi_info;

	//这个对象用来管理WIFI设备
	private WifiManager mWifiManager;
	private BroadcastReceiver mReceiver;

	// 数据
	private WifiListAdapter adapter;
	private List<ScanResult> mScanResults;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		initData();

		initPreference();

	}

	@Override
	protected void onResume() {
		super.onResume();

		//手动注册广播接受者：用于接受查询到的wifi扫描结果
		IntentFilter filter = new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		registerReceiver(mReceiver, filter);
		
		//开始扫描
		mWifiManager.startScan();
	}

	@Override
	protected void onPause() {

		super.onPause();

		//销毁这个广播接受者，即不会接收到广播
		unregisterReceiver(mReceiver);
	}

	private void init() {

		lv_wifi_info = (ListView) findViewById(R.id.lv_wifi_info);

	}

	private void initData() {

		adapter = new WifiListAdapter(this, mScanResults);
		
		mWifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		
		WifiInfo mWifiInfo = mWifiManager.getConnectionInfo();

		mReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {

				final String action = intent.getAction();

				if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
					
					//接受到的WIFI数据
					mScanResults = mWifiManager.getScanResults();
					
					adapter.setData(mScanResults);
					
					
					mWifiManager.startScan();
				}

			}

		};
		
	}

	private void initPreference() {
		lv_wifi_info.setAdapter(adapter);
	}

}
