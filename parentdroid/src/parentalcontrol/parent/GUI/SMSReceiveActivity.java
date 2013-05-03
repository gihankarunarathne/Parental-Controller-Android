package parentalcontrol.parent.GUI;

import parentalcontrol.parent.R;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;

public class SMSReceiveActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smsreceivelayout);

		TextView view = (TextView) findViewById(R.id.etsmsreceive);//to show msg body

		Uri uriSMSURI = Uri.parse("content://sms/sent");
		Cursor cur = getContentResolver().query(uriSMSURI, null, null, null,
				null);
		String sms = "";
		while (cur.moveToNext()) {
			sms += "From :" + cur.getString(2) + " : " + cur.getString(12)
					+ "\n";
		}

		view.setText(sms);//view th data
	}
}
