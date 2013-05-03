package parentalcontroller.child.Logic;

import java.util.ArrayList;
import java.util.List;

import parentalcontroller.child.GUI.MapViewActivity;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Log;

public class GetInstallApplicationService extends IntentService {
	String tag = "GetInstallApplicationService";
	private ActivityManager actManager;

	/**
	 * A constructor is required, and must call the super IntentService(String)
	 * constructor with a name for the worker thread.
	 */
	public GetInstallApplicationService() {
		super("GetInstallApplicationService");
	}

	/**
	 * The IntentService calls this method from the default worker thread with
	 * the intent that started the service. When this method returns,
	 * IntentService stops the service, as appropriate.
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		// Normally we would do some work here, like download a file.
		// For our sample, we just sleep for 5 seconds.
		Log.d("intentservice", "on handle started");
		ArrayList<PInfo> packages = getPackages();
		for (int i = 0; i < packages.size(); i++) {
			PInfo p = packages.get(i);
			Log.d("count", Integer.toString(i));
			if ("Calendar".equals(p.appname)) {
				Log.d("kill", "::Going to kill the process " + p.appname + " "
						+ p.pname);
				//actManager.killBackgroundProcesses(p.pname);
				break;
			}
		}

		long endTime = System.currentTimeMillis() + 5 * 100;
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		}
		Intent i = new Intent(this, MapViewActivity.class);
		//startActivity(i);
		
		//getPackages();
	}

	private ArrayList<PInfo> getPackages() {
		ArrayList<PInfo> apps = getInstalledApps(false);
		final int max = apps.size();
		for (int i = 0; i < max; i++) {
			apps.get(i).prettyPrint();
		}
		return apps;
	}

	private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
		ArrayList<PInfo> res = new ArrayList<PInfo>();
		List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
		for (int i = 0; i < packs.size(); i++) {
			PackageInfo p = packs.get(i);
			if ((!getSysPackages) && (p.versionName == null)) {
				continue;
			}
			PInfo newInfo = new PInfo();
			newInfo.appname = p.applicationInfo.loadLabel(getPackageManager())
					.toString();
			newInfo.pname = p.packageName;
			newInfo.versionName = p.versionName;
			newInfo.versionCode = p.versionCode;
			// newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
			res.add(newInfo);
		}
		return res;
	}

}

class PInfo {
	String appname = "";
	String pname = "";
	String versionName = "";//
	int versionCode = 0;

	// private Drawable icon;
	public void prettyPrint() {
		Log.d("tag:gihan", appname + "\t" + pname + "\t" + versionName + "\t"
				+ versionCode);
	}
}
