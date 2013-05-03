package parentalcontroller.child.GUI;

import parentalcontroller.child.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DeviceAdmin extends PreferenceActivity {
  //Debug
  private static boolean D=false;
  private static String TAG="DeviceAdmin";
  
	private static final int REQUEST_ENABLE = 1;

	DevicePolicyManager mDPM;
	ComponentName mAdminName;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.notificationpreferences);
        // Get the custom preference
        Preference customPref = (Preference) findPreference("customPref");
        customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                                public boolean onPreferenceClick(Preference preference) {
                                        Toast.makeText(getBaseContext(),
                                                        "The custom preference has been clicked",
                                                        Toast.LENGTH_LONG).show();
                                        SharedPreferences customSharedPreference = getSharedPreferences(
                                                        "myCustomSharedPrefs", Activity.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = customSharedPreference
                                                        .edit();
                                        editor.putString("myCustomPref","The preference has been clicked");
                                        editor.commit();
                                        return true;
                                }
                        });
		if(D) Log.d(TAG, "device admin created");
	}

	@SuppressLint("NewApi")
  public boolean onPreferenceChange(Preference preference, Object newValue) {
		boolean isAdmin = false;
		if (!mDPM.isAdminActive(mAdminName)) {
			// try to become active – must happen here in this activity, to get result
			Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
			intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
					"Administration isn't activated.");
			startActivityForResult(intent, REQUEST_ENABLE);
		} else {
			// Already is a device administrator, can do security operations now.
			mDPM.lockNow();
			isAdmin = true;
		}
		return isAdmin;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (REQUEST_ENABLE == requestCode) {
			if (resultCode == Activity.RESULT_OK) {
				// Has become the device administrator.

			} else {
				// Canceled or failed.

			}
		}
	}
	
	 @Override
	  protected void onDestroy() {
	    super.onDestroy();  
	  }

	  @SuppressLint("NewApi")
    @Override
	  protected void onStop() {
	    super.onStop();
	    if(D) Log.d(TAG, "on stop");
	  }

	public static class MyAdmin extends DeviceAdminReceiver {

		void showToast(Context context, String msg) {
			String status = context.getString(R.string.admin_receiver_status,msg);
			Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onEnabled(Context context, Intent intent) {
			showToast(context,context.getString(R.string.admin_receiver_status_enabled));
		}

		@Override
		public void onDisabled(Context context, Intent intent) {
			showToast(context,context.getString(R.string.admin_receiver_status_disabled));
		}
	}
}
