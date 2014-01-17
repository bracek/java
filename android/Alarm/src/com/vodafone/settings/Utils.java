/**
 * 
 */
package com.vodafone.settings;

import android.app.Activity;
import android.widget.Toast;

/**
 * @author katrami
 * 
 */
public class Utils {

	public static void showToast(final Activity activity, final String message) {
		Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
	}
}
