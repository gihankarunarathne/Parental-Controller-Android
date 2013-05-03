package parentalcontrol.parent.test.GUI;
import parentalcontrol.parent.GUI.SMSReceiveActivity;
import android.test.SingleLaunchActivityTestCase;
import android.widget.TextView;


public class SMSReceiveActivityTest extends SingleLaunchActivityTestCase<SMSReceiveActivity>{

	private SMSReceiveActivity mActivity;
	private String resourceString;
	private TextView mView;

	public SMSReceiveActivityTest() {
		super("parentalcontrol.parent.GUI", SMSReceiveActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		mView = (TextView) mActivity.findViewById(parentalcontrol.parent.R.id.tvsmsreceive);
		resourceString = mActivity.getString(parentalcontrol.parent.R.string.smsreceivetxt);
	}

	public void testPreconditions() {
		assertNotNull(mView);
	}

	public void testText() {
		assertEquals(resourceString, (String) mView.getText());
	}
}
