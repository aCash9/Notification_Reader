package com.example.notifactionlistener;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class YourNotificationListenerService extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String packageName = sbn.getPackageName();
        long postTime = sbn.getPostTime();
        Date date = new Date(postTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String fdate = sdf.format(date);
        Notification notification = sbn.getNotification();
        Bundle extras = notification.extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        CharSequence text = extras.getCharSequence(Notification.EXTRA_TEXT);

        myDataBase db = new myDataBase(this);
        NotificationDetails nd = new NotificationDetails(packageName, title, text.toString(), fdate);
        db.addItem(nd);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
