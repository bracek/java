package com.vodafone.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public final class MyAlarmService extends Service {

	private IAlarmReceiver alarm = new AlarmReceiver();

	// private IAlarmReceiver alarm = new RepeatedAlarmReceiver();

	@Override
	public void onCreate() {
		Toast.makeText(this, "AlarmService.onCreate()", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public IBinder onBind(final Intent intent) {
		Toast.makeText(this, "AlarmService.onBind()", Toast.LENGTH_LONG).show();
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "AlarmService.onDestroy()", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onStart(final Intent intent, final int startId) {
		// super.onStart(intent, startId);
		alarm.setAlarm(MyAlarmService.this);
		Toast.makeText(this, "AlarmService.onStart()", Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public boolean onUnbind(final Intent intent) {
		Toast.makeText(this, "AlarmService.onUnbind()", Toast.LENGTH_LONG)
				.show();
		return super.onUnbind(intent);
	}
}