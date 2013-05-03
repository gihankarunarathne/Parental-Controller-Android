package parentalcontrol.parent.GUI;

import java.util.List;

import parentalcontrol.parent.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapViewActivity extends MapActivity {
  //Debug
  private static boolean D = false;
  private static String TAG = "ParentDroid";
  private MapView mapView;
  private MapController mapController;
  private GeoPoint geoPoint;
  private boolean isGPSEnabled = false;
  private boolean isNetworkEnabled = false;
  protected LocationManager locationManager;
  protected LocationListener locationListener;
  // The minimum distance to change Updates in meters
  private static final long DISTANCE = 1;
  // The minimum time between updates in milliseconds
  private static final long TIME_INTERVAL = 1000 * 1;

  class MapOverlay extends com.google.android.maps.Overlay {
    @Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
        long when) {
      super.draw(canvas, mapView, shadow);
      // translate the GeoPoint to screen pixels
      Point screenPts = new Point();
      mapView.getProjection().toPixels(geoPoint, screenPts);
      // add the marker
      Bitmap bmp = BitmapFactory.decodeResource(getResources(),
          R.drawable.marker);
      canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);
      return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MapView mapView) {
      // when user lifts his finger
      if (event.getAction() == 1) {
        GeoPoint p = mapView.getProjection().fromPixels((int) event.getX(),
            (int) event.getY());
        Toast.makeText(
            getBaseContext(),
            "Location: " + p.getLatitudeE6() / 1E6 + "," + p.getLongitudeE6()
                / 1E6, Toast.LENGTH_SHORT).show();
      }
      return false;
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.mapviewlayout);

    this.mapView = (MapView) findViewById(R.id.mapView);
    this.mapView.setBuiltInZoomControls(true);

    mapView.setSatellite(true);
    // mapView.setStreetView(true);
    // mapView.setTraffic(true);
    mapController = mapView.getController();
    // Acquire a reference to the system Location Manager
    locationManager = (LocationManager) this
        .getSystemService(Context.LOCATION_SERVICE);
    setLocationListener(locationManager);
  }

  /**
   * Show the location on Map
   */
  private void showLocationOnMap(Location location) {
    geoPoint = new GeoPoint((int) (location.getLatitude() * 1E6),
        (int) (location.getLongitude() * 1E6));
    mapController.animateTo(geoPoint);
    mapController.setZoom(12);
    // Add a location marker
    MapOverlay mapOverlay = new MapOverlay();
    List<Overlay> listOfOverlays = mapView.getOverlays();
    listOfOverlays.clear();
    listOfOverlays.add(mapOverlay);
    mapView.invalidate();
  }

  /**
   * Set a location listener
   */
  private void setLocationListener(LocationManager locationManager) {
    // getting GPS status
    isGPSEnabled = locationManager
        .isProviderEnabled(LocationManager.GPS_PROVIDER);
    // getting network status
    isNetworkEnabled = locationManager
        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    // Define a listener that responds to location updates
    locationListener = new LocationListener() {
      public void onLocationChanged(Location location) {
        // Called when a new location is found by the network location provider.
        showLocationOnMap(location);
      }

      public void onStatusChanged(String provider, int status, Bundle extras) {
      }

      public void onProviderEnabled(String provider) {
      }

      public void onProviderDisabled(String provider) {
      }
    };
    // Register the listener to receive location updates
    if (isNetworkEnabled) {
      showLocationOnMap(locationManager
          .getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
      locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
          TIME_INTERVAL, DISTANCE, locationListener);
      if(D) Log.d(TAG, "Network provider : "+isNetworkEnabled);
    } else if (isGPSEnabled) {
      showLocationOnMap(locationManager
          .getLastKnownLocation(LocationManager.GPS_PROVIDER));
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
          TIME_INTERVAL, DISTANCE, locationListener);
      if(D) Log.d(TAG, "GPS provider : "+isGPSEnabled);
    } else {
      Toast.makeText(getBaseContext(), "No Network Available.",
          Toast.LENGTH_SHORT).show();
      if(D) Log.d(TAG, "Network provider : "+isNetworkEnabled + " GPS provider : "+isGPSEnabled);
    }
  }

  /**
   * Get KEYCODE
   */
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    MapController mc = mapView.getController();
    switch (keyCode) {
    case KeyEvent.KEYCODE_3:
      mc.zoomIn();
      break;
    case KeyEvent.KEYCODE_1:
      mc.zoomOut();
      break;
    }
    return super.onKeyDown(keyCode, event);
  }

  @Override
  protected boolean isRouteDisplayed() {
    return false;
  }

  @Override
  protected void onDestroy() {
    // Remove the listener
    locationManager.removeUpdates(locationListener);
    super.onDestroy();
  }
}