package com.vodafone.alarm;

import android.content.Context;
import android.content.Intent;

public interface IAlarmReceiver {

	public abstract void onReceive(final Context context,final  Intent intent);

	public abstract void setAlarm(final Context context);

}
