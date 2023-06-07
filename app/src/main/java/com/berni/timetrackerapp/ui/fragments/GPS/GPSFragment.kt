package com.berni.timetrackerapp.ui.fragments.GPS

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.berni.timetrackerapp.R

class GPSFragment : AppCompatActivity() {
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    private lateinit var btnStartWork: Button
    private lateinit var btnStopWork: Button
    private lateinit var txtLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gps)

        btnStartWork = findViewById(R.id.btnStartWork)
        btnStopWork = findViewById(R.id.btnStopWork)
        txtLocation = findViewById(R.id.txtLocation)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        btnStartWork.setOnClickListener {
            startLocationUpdates()
        }

        btnStopWork.setOnClickListener {
            stopLocationUpdates()
        }
    }

    private fun startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    // Update the UI with the current location
                    txtLocation.text =
                        "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        } else {
            // Request the necessary permission if it's not granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun stopLocationUpdates() {
        locationManager.removeUpdates(locationListener)
        txtLocation.text = ""
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
            }
        }
    }
}