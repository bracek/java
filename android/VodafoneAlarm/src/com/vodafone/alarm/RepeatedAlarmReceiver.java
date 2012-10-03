package com.vodafone.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class RepeatedAlarmReceiver extends AalarmReceiver implements
		IAlarmReceiver {

	@Override
	public void setAlarm(final Context context) {
		final AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		final Intent i = new Intent(context, AlarmReceiver.class);
		final PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
		// repeting
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				1000 * 60 * ALARM_REPETING, pi); // Millisec * Second * Minute

	}

	@Override
	public void onReceive(final Context context, final Intent intent) {
		// Put here YOUR code.
		Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For
																				// example
		final Vibrator vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(VIBRATE_SECOND);

	}

}
