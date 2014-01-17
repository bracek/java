package com.vodafone.alarm;

import android.content.Context;
import android.content.Intent;

public interface IAlarmReceiver {

	 abstract void onReceive(final Context context,final  Intent intent);

	 abstract void setAlarm(final Context context);

}
