package com.vodafone.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

public class AlarmReceiver extends AbstractAlarmReceiver implements
		IAlarmReceiver {
	private Vibrator vibrator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.exercise.AndroidAlarmService.IAlarmReceiver#onReceive(android.content
	 * .Context, android.content.Intent)
	 */
	@Override
	public void onReceive(final Context context, final Intent intent) {

		// Put here YOUR code.
		// Toast.makeText(context, "Alarm !!!!!!!!!!",
		// Toast.LENGTH_LONG).show(); // For
		// example
		vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(VIBRATE_SECOND);

		// recursion problem

		// final Intent startServiceIntent = new
		// Intent(context, MyAlarmService.class);
		// context.startService(startServiceIntent);

		// PowerManager pm = (PowerManager) context
		// .getSystemService(Context.POWER_SERVICE);
		// PowerManager.WakeLock wl = pm.newWakeLock(
		// PowerManager.PARTIAL_WAKE_LOCK, "");
		// wl.acquire();
		// wl.release();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.exercise.AndroidAlarmService.IAlarmReceiver#setAlarm(android.content
	 * .Context)
	 */
	@Override
	public void setAlarm(final Context context) {
		final AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		final Intent i = new Intent(context, AlarmReceiver.class);
		final PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ (ALARM_SECOND * 1000), pi);
		// Toast.makeText(this, "Alarm set in " + ALARM_SECOND + " seconds",
		// Toast.LENGTH_LONG).show();

		// repeting
		// am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
		// 1000 * 60 * 5, pi); // Millisec * Second * Minute

		// Notification.Builder builder = new Notification.Builder(context);
		// builder.setSound(Uri.fromFile(yourFile));
		// builder.setTicker("zobud sa kamo");
		// NotificationManager.notify(1, builder.getNotification());

	}

	public void cancelAlarm(final Context context) {
		final Intent intent = new Intent(context, AlarmReceiver.class);
		final PendingIntent sender = PendingIntent.getBroadcast(context, 0,
				intent, 0);
		final AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
		vibrator.cancel();

	}
}
