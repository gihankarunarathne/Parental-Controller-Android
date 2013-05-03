package parentalcontrol.parent.test.GUI;

import parentalcontrol.parent.GUI.ParentdroidActivity;
import android.test.SingleLaunchActivityTestCase;
import android.widget.TextView;

public class ParentdroidActivityTest extends SingleLaunchActivityTestCase<ParentdroidActivity>{

	private ParentdroidActivity mActivity;
	private String resource1;
	private TextView mView1;
	private String resource2;
	private TextView mView2;

	public ParentdroidActivityTest() {
		super("parentalcontrol.parent.GUI", ParentdroidActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		mView1 = (TextView) mActivity.findViewById(parentalcontrol.parent.R.id.buttonMain);
		resource1 = mActivity.getString(parentalcontrol.parent.R.string.showmsgbutton);
		mView2 = (TextView) mActivity.findViewById(parentalcontrol.parent.R.id.showmsgbutton1);
		resource2 = mActivity.getString(parentalcontrol.parent.R.string.showsmsreceive);
	}

	public void testPreconditions() {
		assertNotNull(mView1);
		assertNotNull(mView2);
	}

	public void testText() {
		assertEquals(resource1, (String) mView1.getText());
		assertEquals(resource2, (String) mView2.getText());
	}
}
