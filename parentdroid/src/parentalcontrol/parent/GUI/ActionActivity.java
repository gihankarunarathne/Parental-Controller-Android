package parentalcontrol.parent.GUI;

import parentalcontrol.parent.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActionActivity extends Activity {
  //Debug 
  private static boolean D = false;
  private static String TAG = "Parentdroid";
  
  TextView phonenum ;
  Button showlocation ;
  Button getphoto ;
  Button record ;
  Button runningapp ;
  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smssendlayout);

		this.phonenum = (TextView) findViewById(R.id.txphonenumber);
		this.showlocation =  (Button) findViewById(R.id.bgetchildlocation);
		this.getphoto =  (Button) findViewById(R.id.bgetphoto);
		this.record =  (Button) findViewById(R.id.brecord);
		this.runningapp =  (Button) findViewById(R.id.brunningapps);

		showlocation.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				showNotification("location");
				Toast.makeText(getBaseContext(),
						"location",
						Toast.LENGTH_LONG).show();
				//sendSMSParent(phonenum.getText().toString(), "parent:location");
				startActivity(new Intent(ActionActivity.this,MapViewActivity.class));
			}
		});
		getphoto.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Toast.makeText(getBaseContext(),
						"snapshot",
						Toast.LENGTH_LONG).show();
				showNotification("snapshot");
				sendSMSParent(phonenum.getText().toString(), "parent:photo");
			}
		});
		record.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Toast.makeText(getBaseContext(),
						"record",
						Toast.LENGTH_LONG).show();
				showNotification("record");
				sendSMSParent(phonenum.getText().toString(), "parent:record");
			}
		});
		runningapp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Toast.makeText(getBaseContext(),
						"running applications",
						Toast.LENGTH_LONG).show();
				showNotification("running applications");
				sendSMSParent(phonenum.getText().toString(), "parent:runningapps");
			}
		});
		
	}
	private void sendSMSParent(String smsNumberToSend, String smsTextToSend) {

		SmsManager smsManager = SmsManager.getDefault();
		try {
			smsTextToSend += smsTextToSend;
			smsManager.sendTextMessage(smsNumberToSend, null, smsTextToSend,
					null, null);
		} catch (IllegalArgumentException ix) {

		}
	}
	private void showNotification(String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you want to get "+message+" ?")
		       .setCancelable(false)
		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                
		           }
		       })
		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
	}
}
