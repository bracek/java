/**
 * 
 */
package com.vodafone.alarm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

import com.vodafone.alarm.R;
import com.vodafone.settings.Utils;

/**
 * @author katrami
 * 
 */
public class AlarmActivity extends Activity {

	private void startAlarmActivity() {
		final Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
		i.putExtra(AlarmClock.EXTRA_HOUR, 14);
		i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
		i.putExtra(AlarmClock.EXTRA_MINUTES, 54);
		// i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
		startActivity(i);
	}

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
				startAlarmActivity();
			}
		});

		buttonCancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(final View arg0) {
				// final AlarmManager alarmManager = (AlarmManager)
				// getSystemService(ALARM_SERVICE);
				// alarmManager.cancel(i);

				Utils.showToast(AlarmActivity.this, "Cancel!");
			}
		});
	}
}
