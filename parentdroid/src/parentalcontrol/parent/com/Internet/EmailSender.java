package parentalcontrol.parent.com.Internet;

/*import parentalcontroller.child.R;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;*/

public class EmailSender{

//	private Timer timer = new Timer();
//	private static final long UPDATE_INTERVAL = 5000;

/*	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onCreate");
		
		player = MediaPlayer.create(this, R.raw.braincandy);
		player.setLooping(false); // Set looping
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.
			Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
			return START_STICKY;
	}
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		player.start();
	}

	@Override
	public void onDestroy() {
		String[] to = {"ak47gc@gmail.com",""};
		String[] cc = {"gckarunarathne@gmail.com"};
		sendEmail(to, cc, "Hello", "Hello my friends!");
	
		try {
			//---simulate taking some time to download a file---
			Thread.sleep(100);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
		super.onDestroy();
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}

	// ---sends an SMS message to another device---
	private void sendEmail(String[] emailAddresses, String[] carbonCopies,String subject, String message) {
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		String[] to = emailAddresses;
		String[] cc = carbonCopies;
		emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
		emailIntent.putExtra(Intent.EXTRA_CC, cc);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT, message);
		emailIntent.setType("message/rfc822");
		//startActivity(Intent.createChooser(emailIntent, "Email"));
	}*/
}
