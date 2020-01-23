package com.example.indoortracker;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter.*;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.widget.TextView;

import com.nexenio.bleindoorpositioning.ble.beacon.*;
//import com.nexenio.bleindoorpositioning.ble.beacon.BeaconManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView deviceList=findViewById(R.id.DeviceList);

        BeaconManager.registerBeaconUpdateListener(new BeaconUpdateListener() {
            @Override
            public void onBeaconUpdated(Beacon beacon) {

                deviceList.setText(beacon.getRssi());

            }
        });
    }

    private Beacon processScanResult(ScanResult scanResult) {
        String macAddress = scanResult.getDevice().getAddress();
        byte[] advertisingData = scanResult.getScanRecord().getBytes();
        int rssi = scanResult.getRssi();
        Beacon beacon = BeaconManager.getBeacon(macAddress ,BeaconManager.processAdvertisingData(macAddress, advertisingData, rssi));

    }
}
