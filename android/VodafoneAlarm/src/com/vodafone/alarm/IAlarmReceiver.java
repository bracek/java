package com.vodafone.alarm;

import android.content.Context;
import android.content.Intent;

public interface IAlarmReceiver {

	public abstract void onReceive(Context context, Intent intent);

	public abstract void setAlarm(Context context);

}