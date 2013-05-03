// AIDL file specifying interface used by clients to retrieve screenshots

package parentalcontroller.child.Logic;


// Interface for fetching screenshots
interface IScreenshotProvider {
	// Checks whether the native background application is running
	// (and thus whether the screenshots are available)
	boolean isAvailable();

	// Create a screen snapshot and returns path to file where it is written.
	String takeScreenshot();
}