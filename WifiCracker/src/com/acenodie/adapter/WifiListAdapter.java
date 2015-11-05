package com.acenodie.adapter;

import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.acenodie.wificracker.R;

public class WifiListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<ScanResult> mScanResults;

	public WifiListAdapter(Context mCotnext, List<ScanResult> mScanResults) {
		this.mScanResults = mScanResults;

		this.mInflater = LayoutInflater.from(mCotnext);
	}
	
	public void setData(List<ScanResult> mScanResults){
		this.mScanResults = mScanResults;
		notifyDataSetChanged();
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mScanResults == null ? 0 : mScanResults.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mScanResults.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_listview_wifi_info,
					null);
			viewHolder.item_tv_ssid = (TextView) convertView
					.findViewById(R.id.item_tv_wifi_ssid);
			viewHolder.item_tv_bsssid = (TextView) convertView
					.findViewById(R.id.item_tv_wifi_bssid);
			viewHolder.item_tv_level = (TextView) convertView
					.findViewById(R.id.item_tv_wifi_level);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		ScanResult result = mScanResults.get(position);

		viewHolder.item_tv_ssid.setText(result.SSID);
		viewHolder.item_tv_bsssid.setText(result.BSSID);
		viewHolder.item_tv_level.setText(result.level + "");

		return convertView;
	}

	private class ViewHolder {
		TextView item_tv_ssid;
		TextView item_tv_bsssid;
		TextView item_tv_level;
	}

}
