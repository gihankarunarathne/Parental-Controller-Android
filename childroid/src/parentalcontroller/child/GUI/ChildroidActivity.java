package parentalcontroller.child.GUI;

import parentalcontroller.child.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChildroidActivity extends Activity {
  //Debug
  private static boolean D = true;
  private static String TAG = "Childroid";
	private Button btmain;
	private Button btmsg;
	private Button btemail;
	private Button btmap;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*
		 * show the mail default botton
		 */
		btmain = (Button) findViewById(R.id.buttonMain);
		btmain.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				
				Toast.makeText(getBaseContext(),
						"The custom preference has been clicked",
						Toast.LENGTH_LONG).show();

				authenticate();
					if(D) Log.d(TAG, "show view ");								
			}
		});		

		/*
		 * show the map location of childroid
		 */
		this.btmap = (Button) findViewById(R.id.buttonShowMap);
		this.btmap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent l = new Intent(ChildroidActivity.this,
						MapViewActivity.class);
				startActivity(l);
				if(D) Log.d(TAG, "clicked map show");
			}
		});
	}

	private boolean authenticate() {
		final Context context = this;
		boolean isAdmin = false;

		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.loginlayout, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText username = (EditText) promptsView
				.findViewById(R.id.txusername);
		final EditText password = (EditText) promptsView
				.findViewById(R.id.txpassword);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// result.setText(userInput.getText());
						if(D) Log.d("authentication", username.getText().toString());
						if(D) Log.d("authentication", password.getText().toString());

						// get the password saved and compare with enterd password
						LoginInfo log = getPrefs();
						if(D) Log.d("checking", "is matched");
						if(D) Log.d("authentication", "->" + username.getText().toString());
						if(D) Log.d("authentication", "->" + password.getText().toString());
						if (log.username.equals(username.getText().toString())) {
							if(D) Log.d("checking", "username matched");
							if (log.password.equals(password.getText().toString())) {
								if(D) Log.d("checking", "passowrd matched");
								Intent settingsActivity = new Intent(getBaseContext(),
										DeviceAdmin.class);
								startActivity(settingsActivity);
							}
						}
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

		return isAdmin;
	}

	/*
	 * get the password from the xml file (previously saved) return: As the
	 * LoginInfo class : username and password
	 */
	private LoginInfo getPrefs() {

		// Get the xml/preferences.xml preferences
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		LoginInfo log = new LoginInfo(prefs.getString("userNamePref", "admin"),
				prefs.getString("passwordPref", "admin"));

		return log;
	}

	class LoginInfo {
		private String username;
		private String password;

		public LoginInfo(String name, String pass) {
			// TODO Auto-generated constructor stub
			this.username = name;
			this.password = pass;
		}

		protected String getUserName() {
			return this.username;
		}

		protected String getPassword() {
			return this.password;
		}
	}
}