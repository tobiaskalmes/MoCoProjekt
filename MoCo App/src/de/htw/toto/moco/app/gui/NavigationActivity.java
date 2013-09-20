package de.htw.toto.moco.app.gui;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import de.htw.toto.moco.app.R;
import de.htw.toto.moco.app.gui.views.CompassView;
import de.htw.toto.moco.app.tools.GPSLocation;
import de.htw.toto.moco.app.tools.IGPSLocationListener;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
public class NavigationActivity extends Activity implements SensorEventListener, IGPSLocationListener {
    private SensorManager sensorManager;
    private TextView      readingAzimuth, readingPitch, readingRoll, locationText;
    private CompassView myCompass;
    private Sensor      sensorAccelerometer;
    private Sensor      sensorMagneticField;
    private float[]     valuesAccelerometer;
    private float[]     valuesMagneticField;
    private float[]     matrixR;
    private float[]     matrixI;
    private float[]     matrixValues;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.compass);
        readingAzimuth = (TextView) findViewById(R.id.azimuth);
        readingPitch = (TextView) findViewById(R.id.pitch);
        readingRoll = (TextView) findViewById(R.id.roll);
        locationText = (TextView) findViewById(R.id.location);

        myCompass = (CompassView) findViewById(R.id.mycompass);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        valuesAccelerometer = new float[3];
        valuesMagneticField = new float[3];

        matrixR = new float[9];
        matrixI = new float[9];
        matrixValues = new float[3];

        GPSLocation.getInstance().addListener(this);
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

            double azimuth = Math.toDegrees(matrixValues[0]);
            double pitch = Math.toDegrees(matrixValues[1]);
            double roll = Math.toDegrees(matrixValues[2]);

            readingAzimuth.setText("Azimuth: " + String.valueOf(azimuth));
            readingPitch.setText("Pitch: " + String.valueOf(pitch));
            readingRoll.setText("Roll: " + String.valueOf(roll));

            myCompass.update(matrixValues[0]);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        locationText.setText(location.toString());
    }
}