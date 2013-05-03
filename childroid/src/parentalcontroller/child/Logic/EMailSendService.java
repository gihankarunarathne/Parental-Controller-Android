package parentalcontroller.child.Logic;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;

public class EMailSendService extends IntentService {

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle bundle = intent.getExtras();
		sendEmail(bundle);

	}

	public EMailSendService() {
		super("EMailSendService");
	}

	// this will work with K9 app; this use to demonstrate the project
	/*
	 * Button send;
	 * 
	 * EditText address, subject, emailtext;
	 *//** Called when the activity is first created. */
	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState); setContentView(R.layout.main);
	 * 
	 * final Button send = (Button) this.findViewById(R.id.send);
	 * send.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub try { GMailSender sender = new GMailSender("childroid403@gmail.com",
	 * "childroid403"); sender.sendMail("This is Subject",
	 * "This is Body","childroid403@gmail.com","ak47gc@gmail.com"); } catch
	 * (Exception e) { Log.e("SendMail", e.getMessage(), e); } } }); }
	 */

	// this piece of code work with phones
	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * 
	 * 
	 * setContentView(R.layout.email);
	 * 
	 * send = (Button) findViewById(R.id.emailsendbutton);
	 * 
	 * address = (EditText) findViewById(R.id.emailaddresstv);
	 * 
	 * subject = (EditText) findViewById(R.id.emailsubjecttv);
	 * 
	 * emailtext = (EditText) findViewById(R.id.emailtext);
	 * 
	 * send.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override
	 * 
	 * public void onClick(View v) { // TODO Auto-generated method stub try {
	 * GMailSender sender = new GMailSender("childroid403@gmail.com",
	 * "childroid403"); sender.sendMail("This is Subject",
	 * "This is Body","childroid403@gmail.com","ak47gc@gmail.com"); } catch
	 * (Exception e) { Log.e("SendMail", e.getMessage(), e); }
	 * 
	 * // TODO Auto-generated method stub
	 * 
	 * final Intent emailIntent = new Intent(
	 * 
	 * android.content.Intent.ACTION_SEND);
	 * 
	 * emailIntent.setType("image/png");
	 * 
	 * emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
	 * 
	 * new String[] { address.getText().toString() });
	 * 
	 * emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
	 * 
	 * subject.getText());
	 * 
	 * emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
	 * 
	 * emailtext.getText());
	 * 
	 * emailIntent.putExtra(Intent.EXTRA_STREAM, Uri
	 * 
	 * .parse("android.resource://"
	 * 
	 * + getPackageName() + "/" + R.drawable.icon));
	 * 
	 * startActivity(Intent.createChooser(emailIntent,"Send mail...")); } });
	 * 
	 * }
	 */
	// -----------------------------------------------
	/*
	 * String aEmailList[] String aEmailCCList[] String aEmailBCCList[] String
	 * aEmailSubject String aEmailTEXT
	 */
	/*
	 * String aEmailList[] = { "ak47gc@gmail.com", "jayani2010@gmail.com" };
	 * String aEmailCCList[] = { "ak47gc@gmail.com", "jayani2010@gmail.com" };
	 * String aEmailBCCList[] = { "ak47gc@gmail.com", "jayani2010@gmail.com" };
	 */
	private void sendEmail(Bundle bundle) {
		/*
		 * send email to the parentdroid
		 */

		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

		String aEmailList[] = bundle
				.getStringArray(android.content.Intent.EXTRA_EMAIL);
		String aEmailCCList[] = bundle
				.getStringArray(android.content.Intent.EXTRA_CC);
		String aEmailBCCList[] = bundle
				.getStringArray(android.content.Intent.EXTRA_BCC);
		String aEmailSubject = bundle
				.getString(android.content.Intent.EXTRA_SUBJECT);
		String aEmailTEXT = bundle.getString(android.content.Intent.EXTRA_TEXT);

		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
		emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);
		emailIntent.putExtra(android.content.Intent.EXTRA_BCC, aEmailBCCList);

		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				aEmailSubject);

		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, aEmailTEXT);

		startActivity(emailIntent);

		/*
		 * final Intent emailIntent = new
		 * Intent(android.content.Intent.ACTION_SEND);
		 * 
		 * emailIntent.setType("plain/text");
		 * 
		 * emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new
		 * String[]{ "ak47gc@gmail.com","jayani2010@gmail.com"});
		 * 
		 * emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
		 * "Test msg");
		 * 
		 * emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
		 * "Hurray, its working....");
		 * 
		 * startActivity(Intent.createChooser(emailIntent, "Send mail..."));
		 */

	}
}
