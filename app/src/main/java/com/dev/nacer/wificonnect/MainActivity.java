package com.dev.nacer.wificonnect;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText ssid;
    EditText passwd;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ssid=(EditText)findViewById(R.id.editText);
        passwd=(EditText)findViewById(R.id.editText2);
        btn=(Button)findViewById(R.id.button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiConnection();
            }
        });
    }
    private void wifiConnection(){

        WifiConfiguration wifiConfiguration = new
                WifiConfiguration();
        wifiConfiguration.SSID = String.format("\"%s\"",
                ssid.getText().toString());
        wifiConfiguration.preSharedKey = String.format("\"%s\"",
                passwd.getText().toString());

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        int netId = wifiManager.addNetwork(wifiConfiguration);

        if (wifiManager.isWifiEnabled()) { //---wifi is turned on---
            //---disconnect it first---
            wifiManager.disconnect();
        } else { //---wifi is turned off---
            //---turn on wifi---
            wifiManager.setWifiEnabled(true);
        }
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
