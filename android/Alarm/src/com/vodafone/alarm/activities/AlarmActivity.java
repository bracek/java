/**
 * 
 */
package com.vodafone.alarm.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

import com.vodafone.alarm.R;
import com.vodafone.alarm.receivers.AlarmVodafoneBroadcastRecevier;
import com.vodafone.settings.Utils;

/**
 * @author katrami
 * 
 */
public final class AlarmActivity extends Activity {

	private AlarmVodafoneBroadcastRecevier alarmVodafoneBroadcastRecevier;
	private int minute = 26;
	private Button buttonCancel;
	private Button buttonStart;
	private Context context;

	@SuppressWarnings("unused")
	private void startAlarmActivity() {
		final Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
		i.putExtra(AlarmClock.EXTRA_HOUR, 14);
		i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
		i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
		i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);

		final int requestCode = 3;
		startActivityForResult(i, requestCode);
		// startActivity(i);
	}

	@Override
	protected void onActivityResult(final int requestCode,
			final int resultCode, final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == 3) {
			Utils.showToast(AlarmActivity.this, "trojecka sa vratila");
		}
	}

	private void initView() {
		buttonStart = (Button) findViewById(R.id.startalarm);
		buttonCancel = (Button) findViewById(R.id.cancelalarm);
		context = this.getApplicationContext();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

		alarmVodafoneBroadcastRecevier = new AlarmVodafoneBroadcastRecevier();

		buttonStart.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(final View view) {
				// TODO enable alarm in future
				startAlarmActivity();
				alarmVodafoneBroadcastRecevier.setAlarmInFuture(context);
				Utils.showToast(AlarmActivity.this, "Alarm started");
			}
		});

		buttonCancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(final View view) {
				alarmVodafoneBroadcastRecevier.cancelAlarm(context);
				Utils.showToast(AlarmActivity.this, "Cancel!");
			}
		});
	}
}
