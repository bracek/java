package com.vodafone.alarm.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.vodafone.alarm.R;
import com.vodafone.alarm.receivers.AlarmManagerBroadcastReceiver;

public final class AlarmManagerActivity extends Activity {

	private AlarmManagerBroadcastReceiver alarm;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_manager);
		alarm = new AlarmManagerBroadcastReceiver();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void startRepeatingTimer(final View view) {
		final Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.SetAlarm(context);
		} else {
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}

	public void cancelRepeatingTimer(final View view) {
		final Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.CancelAlarm(context);
		} else {
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}

	public void onetimeTimer(final View view) {
		final Context context = this.getApplicationContext();
		if (alarm != null) {
			alarm.setOnetimeTimer(context);
		} else {
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.activity_widget_alarm_manager, menu);
		return true;
	}

}
