package com.example.findyourlecturer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thanosfisherman.wifiutils.WifiUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class servicenovian extends Service {
    TextView mainText;
    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();
    private final Handler handler = new Handler();
    String[] wifis;
    FirebaseAuth fAuth;
    public int a;
    String tipeuser, tipe;
    user user;


    public servicenovian() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled",
                Toast.LENGTH_LONG).show();

        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiUtils.enableLog(true);
            wifiList = mainWifi.getScanResults();
            wifis = new String[wifiList.size()];
            for (int i = 0; i < wifiList.size(); i++) {
                wifis[i] = ((wifiList.get(i)).SSID);
                if (wifis != null) {

                    if (wifis[i].equals("Alfanshter")) { //WIFI

                        WifiConfiguration wifiConfig = new WifiConfiguration();
                        wifiConfig.SSID = String.format("\"%s\"", wifis[i]);
                        wifiConfig.preSharedKey = String.format("\"%s\"", ""); //PASSWORD

                        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                        //remember id
                        int netId = wifiManager.addNetwork(wifiConfig);
                        wifiManager.disconnect();
                        wifiManager.enableNetwork(netId, true);
                        wifiManager.reconnect();

                        //INPUT TO FIREBASE
                        fAuth = FirebaseAuth.getInstance();
                        String user = fAuth.getCurrentUser().getUid();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("user").child("Dosen").child(user + "/ssid");
                        myRef.setValue("RUmah ");

                        //MENAMPILKAN DATE&TIME
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy | hh:mm a");
                        String dateTime = simpleDateFormat.format(calendar.getTime());
                        myRef = database.getReference("user").child("Dosen").child(user + "/tgljam");
                        myRef.setValue(dateTime);

                    }
                    else if (wifis[i].equals("Gedung AH")) { //WIFI

                        WifiConfiguration wifiConfig = new WifiConfiguration();
                        wifiConfig.SSID = String.format("\"%s\"", wifis[i]);
                        wifiConfig.preSharedKey = String.format("\"%s\"", "polinema"); //PASSWORD

                        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                        //remember id
                        int netId = wifiManager.addNetwork(wifiConfig);
                        wifiManager.disconnect();
                        wifiManager.enableNetwork(netId, true);
                        wifiManager.reconnect();

                        //INPUT TO FIREBASE
                        fAuth = FirebaseAuth.getInstance();
                        String user = fAuth.getCurrentUser().getUid();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("user").child("Dosen").child(user + "/ssid");
                        myRef.setValue("Gedung AH Polinema");

                        //MENAMPILKAN DATE&TIME
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy | hh:mm a");
                        String dateTime = simpleDateFormat.format(calendar.getTime());
                        myRef = database.getReference("user").child("Dosen").child(user + "/tgljam");
                        myRef.setValue(dateTime);
                    }
                    else if (wifis[i].equals("Gedung AI")) { //WIFI

                        WifiConfiguration wifiConfig = new WifiConfiguration();
                        wifiConfig.SSID = String.format("\"%s\"", wifis[i]);
                        wifiConfig.preSharedKey = String.format("\"%s\"", "polinema"); //PASSWORD

                        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                        //remember id
                        int netId = wifiManager.addNetwork(wifiConfig);
                        wifiManager.disconnect();
                        wifiManager.enableNetwork(netId, true);
                        wifiManager.reconnect();

                        //INPUT TO FIREBASE
                        fAuth = FirebaseAuth.getInstance();
                        String user = fAuth.getCurrentUser().getUid();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("user").child("Dosen").child(user + "/ssid");
                        myRef.setValue("Gedung AI Polinema");

                        //MENAMPILKAN DATE&TIME
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy | hh:mm a");
                        String dateTime = simpleDateFormat.format(calendar.getTime());
                        myRef = database.getReference("user").child("Dosen").child(user + "/tgljam");
                        myRef.setValue(dateTime);
                    }
                }
            }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
