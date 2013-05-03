package parentalcontrol.parent.Logic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/*
 * This message listen for system broadcast messages
 * when msg received. it call the activity SMSReceiveActivity and show the message*/
public class SMSReceiveService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("service", "create service");
	}
	
	public void onStart(){
		
	}

	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	
}
