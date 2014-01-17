package com.vodafone.alarm;

import android.content.BroadcastReceiver;

public abstract class AbstractAlarmReceiver extends BroadcastReceiver {

	protected static final int VIBRATE_SECOND = 5000;
	protected static final int ALARM_SECOND = 1;
	protected static final int ALARM_REPETING = 5;
}
