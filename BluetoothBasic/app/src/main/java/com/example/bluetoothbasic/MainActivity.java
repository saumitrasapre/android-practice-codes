package com.example.bluetoothbasic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    ListView btDevList;
    public DeviceListAdapter mDeviceListAdapter;

   public ArrayList<BluetoothDevice>BTdevices=new ArrayList<>();
    static final int REQUEST_ENABLE_BT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnONOFF=findViewById(R.id.BTonoff);
        Button discover=findViewById(R.id.discoverBtn);
        Button discoverDevices=findViewById(R.id.Discover);
        Button advertise=findViewById(R.id.advertise);
        btDevList =findViewById(R.id.btDevList);


        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

        btnONOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableDisableBT();
            }
        });

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Making device discoverable for 300s", Toast.LENGTH_SHORT).show();
                Intent discIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
                startActivity(discIntent);

                IntentFilter discFiter=new IntentFilter(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
                registerReceiver(discoverReceiver,discFiter);
            }
        });

        advertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                BluetoothLeAdvertiser advertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
                AdvertiseSettings settings = new AdvertiseSettings.Builder()
                        .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED)
                        .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_MEDIUM)
                        .setConnectable(false)
                        .build();
                ParcelUuid pUuid = new ParcelUuid(UUID.fromString("cf2c82b6-6a06-403d-b7e6-13934e602664"));
                AdvertiseData data = new AdvertiseData.Builder()
                        //.setIncludeDeviceName(true)
                        .addServiceUuid(pUuid)
                        .addServiceData(pUuid, "123456".getBytes())
                        .build();
                AdvertiseCallback advertiseCallback = new AdvertiseCallback() {
                    @Override
                    public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                        Log.i("Advertise", "Advertising onStartSuccess");
                        super.onStartSuccess(settingsInEffect);
                    }

                    @Override
                    public void onStartFailure(int errorCode) {
                        Log.e("Advertise", "Advertising onStartFailure: " + errorCode);
                        super.onStartFailure(errorCode);
                    }
                };
                advertiser.startAdvertising(settings, data, advertiseCallback);



            }
        });

        discoverDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bluetoothAdapter.isDiscovering())
                {
                    bluetoothAdapter.cancelDiscovery();
                    Log.d("Bluetooth log:","Looking for unpaired devices...");
                    checkBTPermissions();
                    bluetoothAdapter.startDiscovery();
                    IntentFilter newDeviceFilter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(newDeviceReceiver,newDeviceFilter);


                }
                else if(!bluetoothAdapter.isDiscovering())
                {
                    bluetoothAdapter.startDiscovery();

                    IntentFilter newDeviceFilter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(newDeviceReceiver,newDeviceFilter);
                }
            }
        });


    }
    private void checkBTPermissions()
    {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP)
        {
            int permissionsCheck=this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionsCheck+=this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if(permissionsCheck!=0)
            {
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1101);
            }
            else
            {
                Log.d("Bluetooth log:","No need to check permissions. SDK version <Lollipop");
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQUEST_ENABLE_BT && resultCode==RESULT_OK)
        {
            Toast.makeText(getApplicationContext(),"Bluetooth Turned on",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Failed to turn on bluetooth", Toast.LENGTH_SHORT).show();
        }
    }

    public final BroadcastReceiver onReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();

            if(action.equals(BluetoothAdapter.ACTION_STATE_CHANGED))
            {
                final int state=intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);

                switch (state)
                {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d("Bluetooth log:","STATE OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d("Bluetooth log:","STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d("Bluetooth log:","STATE ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d("Bluetooth log:","STATE TURNING ON");
                        break;
                }
            }
        }
    };

    public final BroadcastReceiver discoverReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();

            if(action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED))
            {
                final int mode=intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE,BluetoothAdapter.ERROR);

                switch (mode)
                {
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        Log.d("Bluetooth log:","Device Discoverability enabled");
                        break;
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Log.d("Bluetooth log:","Discoverability disabled... Device can receive connections from paired devices");
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Log.d("Bluetooth log:","Discoverability disabled. Not able to receive connections");
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.d("Bluetooth log:","Device connecting...");
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.d("Bluetooth log:","Connected");
                        break;
                }
            }
        }
    };

    public final BroadcastReceiver newDeviceReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();

            if(action.equals(BluetoothDevice.ACTION_FOUND))
            {
               BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
               BTdevices.add(device);
                Log.d("Bluetooth log:", "onReceive: " + device.getName() + ": " + device.getAddress());
               mDeviceListAdapter=new DeviceListAdapter(getApplicationContext(),R.layout.device_adapter_view,BTdevices);
               btDevList.setAdapter(mDeviceListAdapter);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onReceiver);
    }

    public void enableDisableBT() {
        if(bluetoothAdapter==null)
        {
            Toast.makeText(getApplicationContext(),"Device does not support Bluetooth",Toast.LENGTH_SHORT).show();
        }
        else if(!bluetoothAdapter.isEnabled())
        {
            Intent enableBTIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //startActivityForResult(enableBTIntent,REQUEST_ENABLE_BT);
            startActivity(enableBTIntent);

            IntentFilter BTIntentFilter=new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(onReceiver, BTIntentFilter);
        }

        else if(bluetoothAdapter.isEnabled())
        {
            bluetoothAdapter.disable();
            IntentFilter BTIntentFilter=new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(onReceiver, BTIntentFilter);
        }
    }
}
