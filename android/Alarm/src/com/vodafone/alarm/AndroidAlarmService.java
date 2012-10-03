package com.vodafone.alarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public final class AndroidAlarmService extends Activity {

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

				final Intent myIntent = new Intent(AndroidAlarmService.this,
						MyAlarmService.class);
				pendingIntent = PendingIntent.getService(
						AndroidAlarmService.this, 0, myIntent, 0);

				final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

				final Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.SECOND, 1);
				alarmManager.set(AlarmManager.RTC_WAKEUP,
						calendar.getTimeInMillis(), pendingIntent);

				// Toast.makeText(AndroidAlarmService.this, "Start Alarm",
				// Toast.LENGTH_LONG).show();
			}
		});

		buttonCancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(final View arg0) {
				final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
				alarmManager.cancel(pendingIntent);

				// Tell the user about what we did.
				Toast.makeText(AndroidAlarmService.this, "Cancel!",
						Toast.LENGTH_LONG).show();

			}
		});

	}
}