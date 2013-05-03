package parentalcontroller.child.GUI;

import parentalcontroller.child.R;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;

public class SMSReceiveActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smsreceivelayout);

		TextView phoneNum = (TextView) findViewById(R.id.tvsmsreceive);//to show phone number
		TextView view = (TextView) findViewById(R.id.etsmsreceive);//to show msg body

		String[] messages = getIntent().getStringArrayExtra("msgset");//get data from intent
		String msg = "";
		String[] sp;

		String currentMessage = messages[0];
		int i = 1;
		/*
		 * read all the msg and put into array
		 */
		while (null != currentMessage) {//get msg , one by one, extract concat data
			sp = currentMessage.split(":");
			phoneNum.setText("Form : " + sp[0]);
			msg += "Msg : " + sp[1] + "\n";
			currentMessage = messages[i];
			sendSMSParent(sp[0], "From : " + sp[0] + msg);//send a copy to the parentdroid
			i++;
		}

		view.setText(msg);//view th data
	}
	
	/*
	 * sned the msg to the parentdroid
	 */
	private void sendSMSParent(String smsNumberToSend, String smsTextToSend) {

		SmsManager smsManager = SmsManager.getDefault();
		try {
			smsTextToSend += "Generated msg:\"" + smsTextToSend
					+ "\"by childroid";
			smsManager.sendTextMessage(smsNumberToSend, null, smsTextToSend,
					null, null);
		} catch (IllegalArgumentException ix) {

		}
	}
}
