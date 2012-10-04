package com.vodafone.alarm.activities;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vodafone.alarm.MyAlarmService;
import com.vodafone.alarm.R;
import com.vodafone.settings.Utils;

public final class AlarmServiceActivity extends Activity {

	private PendingIntent pendingIntent;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button buttonStart = (Button) findViewById(R.id.startalarm);
		final Button buttonCancel = (Button) findViewById(R.id.cancelalarm);

		buttonStart.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(final View arg0) {

				final Intent myIntent = new Intent(AlarmServiceActivity.this,
						MyAlarmService.class);
				pendingIntent = PendingIntent.getService(
						AlarmServiceActivity.this, 0, myIntent, 0);

				final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				final Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				// calendar.set(Calendar.DAY_OF_WEEK, 3);
				// calendar.set(Calendar.HOUR_OF_DAY, 15);
				// calendar.set(Calendar.MINUTE, 45);
				calendar.add(Calendar.SECOND, 2);
				// calendar.set(Calendar.MILLISECOND, 0);
				am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
						pendingIntent);

				// set repeating
				// am.setRepeating(AlarmManager.RTC_WAKEUP,
				// calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
				// pendingIntent);
				Utils.showToast(AlarmServiceActivity.this, "Start Alarm");

			}

		});

		buttonCancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(final View arg0) {
				final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
				alarmManager.cancel(pendingIntent);

				// Tell the user about what we did.
				Utils.showToast(AlarmServiceActivity.this, "Cancel!");
			}
		});
	}

}