package parentalcontrol.parent.test.GUI;

import parentalcontrol.parent.GUI.MapViewActivity;
import android.test.SingleLaunchActivityTestCase;
import android.widget.TextView;

public class MapViewActivityTest extends SingleLaunchActivityTestCase<MapViewActivity>{

	private MapViewActivity mActivity;
	private String resourceString;
	private TextView mView;
	private TextView map;

	public MapViewActivityTest() {
		super("parentalcontrol.parent.GUI", MapViewActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		mView = (TextView) mActivity.findViewById(parentalcontrol.parent.R.id.mapView);
		map = (TextView) mActivity.findViewById(parentalcontrol.parent.R.id.txmapview);
		resourceString = mActivity.getString(parentalcontrol.parent.R.string.mapviewtopic);
	}

	public void testPreconditions() {
		assertNotNull(mView);
	}

	public void testText() {
		assertEquals(resourceString, (String) map.getText());
	}
}
