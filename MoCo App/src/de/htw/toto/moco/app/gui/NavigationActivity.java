package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.gui.views.CompassView;
import de.htw.toto.moco.app.tools.gps.*;
import de.htw.toto.moco.server.navigation.POI;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
public class NavigationActivity extends Activity implements SensorEventListener, IGPSLocationListener,
                                                            INavigationListener {
    public static final String POI_KEY = "navigateToPOI";
    private SensorManager sensorManager;
    private TextView      readingLatitude, readingLongitude, readingDistance, readingBearing;
    private CompassView myCompass;
    private Sensor      sensorAccelerometer;
    private Sensor      sensorMagneticField;
    private float[]     valuesAccelerometer;
    private float[]     valuesMagneticField;
    private float[]     matrixR;
    private float[]     matrixI;
    private float[]     matrixValues;
    private boolean     isNavigating;
    private Float       bearing;
    private POI         poi;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.compass);

        isNavigating = false;
        bearing = null;

        readingLatitude = (TextView) findViewById(R.id.latitude);
        readingLongitude = (TextView) findViewById(R.id.longitude);
        readingDistance = (TextView) findViewById(R.id.distance);
        readingBearing = (TextView) findViewById(R.id.bearing);

        myCompass = (CompassView) findViewById(R.id.mycompass);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        valuesAccelerometer = new float[3];
        valuesMagneticField = new float[3];

        matrixR = new float[9];
        matrixI = new float[9];
        matrixValues = new float[3];

        GPSLocation.getInstance().addGPSListener(this);
        GPSLocation.getInstance().addNavigationListener(this);
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(POI_KEY)) {
            NavigationActivity.this.isNavigating = true;
            poi = (POI) getIntent().getSerializableExtra(POI_KEY);
        }
    }

    @Override
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (poi != null) {
            try {
                GPSLocation.getInstance().navigateToPOI(poi);
            }
            catch (GPSException e) {
                Log.e("GPS-Exception", "GPS failed", e);
            }
        }
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, sensorAccelerometer);
        sensorManager.unregisterListener(this, sensorMagneticField);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(this, sensorAccelerometer);
        sensorManager.unregisterListener(this, sensorMagneticField);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this, sensorAccelerometer);
        sensorManager.unregisterListener(this, sensorMagneticField);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                System.arraycopy(event.values, 0, valuesAccelerometer, 0, 3);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                System.arraycopy(event.values, 0, valuesMagneticField, 0, 3);
                break;
        }

        boolean success = SensorManager.getRotationMatrix(matrixR, matrixI, valuesAccelerometer, valuesMagneticField);

        if (success) {
            SensorManager.getOrientation(matrixR, matrixValues);
            if (!isNavigating || bearing == null) {
                myCompass.update(matrixValues[0]);
            } else {
                myCompass.update(matrixValues[0], bearing);
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        readingLatitude.setText("Latitude: " + location.getLatitude());
        readingLongitude.setText("Longitude: " + location.getLongitude());
    }

    @Override
    public void onLocationChanged(NavigationInfo navInfo) {
        readingBearing.setText("Bearing: " + navInfo.getBearing());
        readingDistance.setText("Distance: " + navInfo.getDistance() + "m");
        bearing = navInfo.getBearing();
    }
}