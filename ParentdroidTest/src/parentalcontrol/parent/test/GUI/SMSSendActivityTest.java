package parentalcontrol.parent.test.GUI;

import parentalcontrol.parent.GUI.ActionActivity;
import android.test.SingleLaunchActivityTestCase;
import android.widget.TextView;

public class SMSSendActivityTest extends
SingleLaunchActivityTestCase<ActionActivity> {

	private ActionActivity mActivity;
	private String resourceString;
	private TextView mView;

	public SMSSendActivityTest() {
		super("parentalcontrol.parent.GUI", ActionActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		mView = (TextView) mActivity
				.findViewById(parentalcontrol.parent.R.id.labelphonenumber);
		resourceString = mActivity
				.getString(parentalcontrol.parent.R.string.labelchildnum);
	}

	public void testPreconditions() {
		assertNotNull(mView);
	}

	public void testText() {
		assertEquals(resourceString, (String) mView.getText());
	}
}
