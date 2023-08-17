package com.example.notifactionlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private Handler handler = new Handler();
    private Runnable refreshRunnable = new Runnable() {
        @Override
        public void run() {
            refreshData();

            // Schedule the next refresh after a delay
            handler.postDelayed(this, 10000); // Refresh every 10 seconds
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isNotificationListenerServiceEnabled()) {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }

        startService(new Intent(this, YourNotificationListenerService.class));

        myDataBase db = new myDataBase(this);
        List<NotificationDetails> list = db.getData();
        Collections.reverse(list);
        listView = findViewById(R.id.listview);
        notificationAdapter adapter = new notificationAdapter(this, R.layout.custom_layout,list);
        listView.setAdapter(adapter);

        handler.postDelayed(refreshRunnable, 1000);
    }

    private boolean isNotificationListenerServiceEnabled() {
        String packageName = getPackageName();
        String flat = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");

        if (flat != null) {
            return flat.contains(packageName);
        }
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(refreshRunnable); // Stop refreshing
    }

    private void refreshData() {
        myDataBase db = new myDataBase(this);
        List<NotificationDetails> list = db.getData();
        Collections.reverse(list);
        notificationAdapter adapter = new notificationAdapter(this, R.layout.custom_layout, list);
        listView.setAdapter(adapter);
    }
}