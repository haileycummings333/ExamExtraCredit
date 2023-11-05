package com.example.examproject;

import android.app.Activity;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.content.IntentFilter;

//QUESTION 15 EXTRA CREDIT
public class MainActivity extends Activity {
    private TextView callInfoTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the text box from the xml
        callInfoTextView = findViewById(R.id.callInfoTextView);
        // make a broadcast receiver to listen for phone changes
        PhoneCallReceiver phoneCallReceiver = new PhoneCallReceiver();
        //set intent filter to listen for if the phone state changed
        IntentFilter intentFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        //register the phone receiver with the intent filter
        registerReceiver(phoneCallReceiver, intentFilter);
    }
    private class PhoneCallReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //get the call state from the intent passed to the receiver
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            //check if the state is "ringing"
            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                // if incoming call then get caller information
                String callerName = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                // update with caller information
                callInfoTextView.setText("Incoming call from: " + callerName);
            }
        }
    }
}
