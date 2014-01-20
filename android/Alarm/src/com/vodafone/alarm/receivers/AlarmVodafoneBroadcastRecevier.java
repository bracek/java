/**
 * 
 */
package com.vodafone.alarm.receivers;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

/**
 * @author katrami
 * 
 */
public final class AlarmVodafoneBroadcastRecevier extends BroadcastReceiver {

	final public static String ONE_TIME = "onetime";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(final Context context,
 final Intent intent) {

		final PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		final PowerManager.WakeLock wl = pm.newWakeLock(
				PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		// Acquire the lock
		wl.acquire();

		// You can do the processing here update the widget/remote views.
		final Bundle extras = intent.getExtras();
		final StringBuilder msgStr = new StringBuilder();

		if (extras != null && extras.getBoolean(ONE_TIME, Boolean.FALSE)) {
			msgStr.append("!!!! One time Timer : ");
		}
		final Format formatter = new SimpleDateFormat("hh:mm:ss a");
		msgStr.append(formatter.format(new Date()));

		Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();

		// Release the lock
		wl.release();

	}

	public void setAlarmInFuture(final Context context,
 final int hour,
			final int minute) {
		final AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		final Intent intent = new Intent(context,
				AlarmManagerBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.TRUE);

		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		// calendar.set(Calendar.HOUR_OF_DAY, 14);
		// calendar.set(Calendar.MINUTE, 46);
		final PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
				0);
		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
	}

	public void setAlarm(final Context context) {
		final AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		final Intent intent = new Intent(context,
				AlarmManagerBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.TRUE);
		final PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
				0);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
	}

	public void cancelAlarm(final Context context) {
		final Intent intent = new Intent(context,
				AlarmVodafoneBroadcastRecevier.class);
		final PendingIntent sender = PendingIntent.getBroadcast(context, 0,
				intent, 0);
		final AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
	}

	public void setRepetingAlarm(final Context context) {
		final AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		final Intent intent = new Intent(context,
				AlarmManagerBroadcastReceiver.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);
		final PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
				0);
		// After after 30 seconds
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
				1000 * 5, pi);

	}
}
